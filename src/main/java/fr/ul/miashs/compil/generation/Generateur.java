package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Fonction;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;

/**
 * Generateur
 * Génération du code assembleur
 */
public class Generateur {

    //Attribut de la table des symboles
    public static TDS tds;
    //Attribut de la table des id utilisées pour faires des appels de parties dans l'assembleur
    public static List<Integer> tableID;
    //Attribut de l'arbre
    public static Noeud arbre;

    public Generateur(Noeud arbre, TDS tds) {
        this.arbre = arbre;
        this.tds = tds;
        this.tableID = new java.util.ArrayList<>();
    }

    /**
     * Fonction generer
     *
     * @return StringBuilder
     * retourne le code assembleur du programme
     **/
    public StringBuilder generer() {
        //Former le fichier template d'assembleur
        String head = ".include beta.uasm\n" +
                ".include intio.uasm\n" +
                ".options tty\n" +
                "\tCMOVE(pile,SP)\n" +
                "\tBR(main)\n";
        String pile = "pile :\n";
        String init = "data:\n";
        StringBuilder stringRes = new StringBuilder();
        stringRes.append(init);
        stringRes.append(head);
        stringRes.append(this.genererData());
        List<Symbole> symList = tds.getAllSymboles();
        for (Symbole sym : symList) {
            if (sym.getCategorie().equals("fonction")) {
                stringRes.append(genererFonction(sym));
            }
        }
        stringRes.append(pile);
        return stringRes;
    }


    /**
     * Fonction genererData
     *
     * @return StringBuilder
     * retourne le code assembleur d'un appel de fonction
     **/
    private StringBuilder genererData() {
        //Récupérer les variables globales et les ajoute dans le code assembleur
        StringBuilder stringRes= new StringBuilder();
        List<Symbole> global_variables_list = tds.getAllGlobalVariables();
        for (Symbole variable : global_variables_list) {
            String nom = variable.getNom();
            String type = variable.getType();
            String valeur = String.valueOf(variable.getValeur());
            if (valeur.equals("null")){
                valeur="0";
            }
            stringRes.append(nom)
                    .append(":\tLONG(")
                    .append(valeur)
                    .append(")\t| ")
                    .append(type)
                    .append(" ")
                    .append(nom)
                    .append(";\n");
        }
        return stringRes;
    }

    /**
     * Fonction genererFonction
     *
     * @param fonction fonction à générer
     * @return StringBuilder
     * retourne le code assembleur d'une fonction
     **/
    private StringBuilder genererFonction(Symbole fonction) {
        StringBuilder stringRes = new StringBuilder();
        stringRes.append(fonction.getNom()).append(":\n");
        stringRes.append("\tMOVE(R0,LP)\n");
        stringRes.append("\tPUSH(R0)\n");
        stringRes.append("\tMOVE(R0,BP)\n");
        stringRes.append("\tPUSH(R0)\n");
        stringRes.append("\tMOVE(SP, BP)\n");
        // pour enregister les paramètres=> étant donné qu'il y en a moins que 4 on peut utiliser de R0 à R3
        List<Symbole> symList = tds.getAllSymboles();
        int compt = 0;
        for (Symbole sym : symList) {
            if (sym.getCategorie().equals("param")) {
                Object valeurParam = sym.getValeur();
                stringRes.append("\tMOVE(R" + compt + "," + valeurParam + ");\n");
            }
            compt++;
        }
        //Allocation de la mémoire pour les variables locales
        int nombreVariables = fonction.getNbVar() != null ? fonction.getNbVar() : 0; // si le nb de var est null on le met directement à 0
        if (nombreVariables > 0) {
            stringRes.append("  ALLOCATE(").append(nombreVariables).append(");\n");
        }
        //Récupération de toutes les fonctions
        Fonction fonctionArbre = null;
        List<Noeud> noeudsArbre = this.arbre.getFils();
        for (Noeud noeud : noeudsArbre) {
            if (noeud instanceof Fonction) {
                Fonction fonc = (Fonction) noeud;
                if (fonc.getValeur().toString().equals(fonction.getNom())) {
                    fonctionArbre = fonc;
                }
            }
        }
        if (fonctionArbre == null) {//cas erreur
            System.out.println("Fonction non trouvée");
            return null;
        } else {
            List<Noeud> instructions = fonctionArbre.getFils();
            for (Noeud instruction : instructions) {
                InstructionGenerateur instruct = new InstructionGenerateur();
                stringRes.append(instruct.genererInstruction(instruction));
            }
        }
        if (!(fonctionArbre.getValeur().toString().equals("main"))) { // seulement si ce n'est pas le main
            stringRes.append("\t BX LR;\n");// revenir à l'endroit avant l'appel de la fonction
        }
        return stringRes;
    }

    public static StringBuilder genererAppel(Symbole fonction) {
        StringBuilder stringRes = new StringBuilder();
        stringRes.append("\tMOVE(R0,LP)\n");// on conserve l'adresse mémoire de la fonction précédente
        stringRes.append("\tPUSH(LP)\n");
        stringRes.append("\tMOVE(R0,BP)\n");
        stringRes.append("\tPUSH(BP)\n");
        stringRes.append("\tMOVE(SP, BP)\n");
        stringRes.append("\tJMP " + fonction.getNom() + "\n"); //
        return stringRes;
    }
}


