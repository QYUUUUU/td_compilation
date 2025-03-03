package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Fonction;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.Prog;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;

public class Generateur {

    private TDS tds;
    private Noeud arbre;

    public Generateur(TDS tds, Noeud arbre) {
        this.tds = tds;
        this.arbre = arbre;
    }
    public TDS getTds(){
        return tds;
    }
    public Noeud getArbre() {
        return this.arbre;
    }

    public void generer() {
        //Former le fichier template d'assembleur
        String head_string = ".include beta.uasm\n" +
                ".include intio.uasm\n" +
                ".options tty\n" +
                "\tCMOVE(pile,SP)\n" +
                "\tBR(main)\n";

        String pile = "pile:\n";

        String data_init_text = "data:\n";
        StringBuilder data = new StringBuilder();
        data.append(data_init_text);
        data = this.generer_data(data);

        StringBuilder final_string = new StringBuilder(head_string + data);

        StringBuilder fonctions = generer_fonctions();

        final_string.append(fonctions);

        final_string.append(pile);

        System.out.println(final_string);
    }

    private StringBuilder generer_fonctions() {
        StringBuilder fonctions = new StringBuilder();
        List<Symbole> liste_des_fonctions = this.tds.getAllFunctions(); //Liste des fonctions appelées dans le code
        for (Symbole fonction : liste_des_fonctions) {
            fonctions.append(generer_fonction(fonction));
        }
        return fonctions;
    }

    private StringBuilder generer_data(StringBuilder data) {
        List<Symbole> global_variables_list = this.tds.getAllGlobalVariables();

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
        fonction_string.append("\tPUSH(BP);\n");
        fonction_string.append("\tMOVE(SP, BP);\n");

        int nombreVariables = fonction.getNbVar() != null ? fonction.getNbVar() : 0;
        if (nombreVariables > 0) {
            fonction_string.append("  ALLOCATE(").append(nombreVariables).append(");\n");
        }

        List<Noeud> noeudsArbre = this.arbre.getFils();
        Fonction fonctionArbre = null;
        for (Noeud noeud : noeudsArbre) {
            if (noeud instanceof Fonction f && f.getValeur() == fonction.getNom()) {
                fonctionArbre = f;
            }
        }
        if (fonctionArbre == null) {
            System.out.println("Fonction non trouvée");
            //TODO: fonction non trouvée
            return null;
        }

        List<Noeud> instructions = fonctionArbre.getFils();
        for (Noeud instruction : instructions) {
            //TODO Utiliser le noeud de la fonction pour explorer son contenu (ses enfants)
        }


        //TODO Ecrire les methodes generate pour chacuns des types d'enfants possibles

//        for (Noeud f : a.getFils()) {
//            fonction_string.append("  ").append(generer_instruction(f)).append("\n");
//        }
        fonction_string.append("}\n");
        return fonction_string;
    }



    private StringBuilder generer_appel() {
        StringBuilder string = new StringBuilder();

        return string;
    }


}
