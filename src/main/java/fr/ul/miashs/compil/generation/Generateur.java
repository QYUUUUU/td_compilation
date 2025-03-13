package fr.ul.miashs.compil.generation;
import fr.ul.miashs.compil.arbre.Fonction;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;

public class Generateur {

    public static TDS tds;
    public static List<Integer> tableID; // table de toutes les id utilisées pour faires des appels de parties dans l'assembleur
    public static Noeud arbre;


    public  Generateur(Noeud arbre, TDS tds) {

        this.arbre = arbre;
        this.tds=tds;
    }
    public Noeud getArbre() {
        return this.arbre;
    }

    public StringBuilder generer() {
        //Former le fichier template d'assembleur
        String head_string = ".include beta.uasm\n" +
                ".include intio.uasm\n" +
                ".options tty\n" +
                "\tCMOVE(pile,SP)\n" +
                "\tBR(main)\n";

        String pile = "pile :\n";

        String data_init_text = "data:\n";
        StringBuilder data = new StringBuilder();
        data.append(data_init_text);
        data = this.generer_data(data);

        StringBuilder final_string = new StringBuilder(head_string + data);

        List<Symbole> symList= tds.getAllSymboles();
        for (Symbole sym: symList) {
            if (sym.getCategorie().equals("fonction")){
                final_string.append(generer_fonction(sym));
            }
        }
        final_string.append(pile);

       return(final_string);
    }


    private StringBuilder generer_data(StringBuilder data) {
        List<Symbole> global_variables_list = tds.getAllGlobalVariables();

        for (Symbole variable : global_variables_list) {

            String nom = variable.getNom();
            String type = variable.getType();
            String valeur = String.valueOf(variable.getValeur());

            data.append(nom)
                    .append(":\tLONG(")
                    .append(valeur != null ? valeur : 0)
                    .append(")\t| ")
                    .append(type)
                    .append(" ")
                    .append(nom)
                    .append(";\n");
        }
        return data;
    }


    // Méthode pour générer le code d'une fonction
    private StringBuilder generer_fonction(Symbole fonction) {
        StringBuilder fonction_string = new StringBuilder();
        fonction_string.append(fonction.getNom()).append(":\n");
        fonction_string.append("\tPUSH(BP);\n");
        fonction_string.append("\tPUSH(BP);\n"); //pk deux PUSH BP?
        fonction_string.append("\tMOVE(SP, BP);\n"); // Plutot l'inverse? on enregistre l'ancienne BP et on la remplace par celle de la fonction qu'on va traiter$

        // pour enregister les paramètres=> étant donné qu'il y en a moins que 4 on peut utiliser de R0 à R3
        List<Symbole> symList= tds.getAllSymboles();
        int compt=0;
        for (Symbole sym: symList) {
            if (sym.getCategorie().equals("param")){
               Object valeurparam=sym.getValeur();
               fonction_string.append("\tMOVE(R"+compt+","+valeurparam+");\n");
            }
            compt++;
        }

        int nombreVariables = fonction.getNbVar() != null ? fonction.getNbVar() : 0; // si le nb de var est null on le met directement à 0
        if (nombreVariables > 0) {
            fonction_string.append("  ALLOCATE(").append(nombreVariables).append(");\n");
        }


        Fonction fonctionArbre = null;
        List<Noeud> noeudsArbre = this.arbre.getFils();
        for (Noeud noeud : noeudsArbre) {
            if (noeud instanceof Fonction){
                Fonction fonc=(Fonction)noeud;
                if(fonc.getValeur() == fonction.getNom()) {
                    fonctionArbre = fonc;
                }
            }
        }

        if (fonctionArbre == null) {//cas erreur
            System.out.println("Fonction non trouvée");
            return null;
        }
        else{
            List<Noeud> instructions = fonctionArbre.getFils();
            for (Noeud instruction : instructions) {
                InstructionG instruct=new InstructionG();
                    instruct.generer_instruction(instruction);
            }
        }
        if(!(fonctionArbre.getValeur().equals("main"))) { // seulement si ce n'est pas le main
            fonction_string.append("\t BX LR;\n");// revenir à l'endroit avant l'appel de la fonction
        }
        fonction_string.append("}\n");
        return fonction_string;
    }



    private StringBuilder generer_appel(Symbole fonction) {
        StringBuilder retString = new StringBuilder();
        retString.append("\t BL "+fonction.getNom()+"\n"); // on place dans LR l'adresse de retour
        return retString;
    }


}


