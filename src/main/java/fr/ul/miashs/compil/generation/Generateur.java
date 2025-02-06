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

    public static void main(String[] args) {
        Prog prog = new Prog();
        Fonction principal = new Fonction("main");
        //on relie les noeuds
        prog.ajouterUnFils(principal);
        Const i = new Const(10);
        Const j = new Const(20);
        Const k = new Const(0);
        Const l = new Const(0);

        TDS tds = new TDS();

        tds.addSymbole(new Symbole("main", "void", "fonction", null, null));
        tds.addSymbole(new Symbole("j", "int", "global", null, null, 20));
        tds.addSymbole(new Symbole("i", "int", "global", null, null, 10));
        tds.addSymbole(new Symbole("k", "int", "global", null, null, null));
        tds.addSymbole(new Symbole("l", "int", "global", null, null, null));

        Generateur generateur = new Generateur(tds, prog);
        generateur.generer();
    }


    private StringBuilder generer_appel() {
        StringBuilder string = new StringBuilder();

        return string;
    }
}
