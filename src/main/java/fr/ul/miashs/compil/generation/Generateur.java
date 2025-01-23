package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;
import fr.ul.miashs.compil.arbre.Noeud;

import java.util.List;

public class Generateur {
    private StringBuilder code;
    private TDS tds;

    public Generateur(TDS tds) {
        this.code = new StringBuilder();
        this.tds = tds;
    }

    // Méthode pour générer le programme entier
    public void generer_programme(Noeud a) {
        generer_data();
        List<Symbole> fonctions = tds.getAllFunctions();
        for (Symbole fonction : fonctions) {
            generer_fonction(fonction, a);
        }
    }

    // Méthode pour générer les données globales
    public void generer_data() {
        List<Symbole> globals = tds.getAllGlobalVariables();
        for (Symbole global : globals) {
            code.append(global.getType()).append(" ").append(global.getNom());
            Integer valeur = global.getValeur(); // Récupérer l'Integer
            if (valeur != null) { // Vérifier si la valeur n'est pas null
                code.append(" = ").append(valeur);
            }
            code.append(";\n");
        }
    }

    // Méthode pour générer le code d'une fonction
    private void generer_fonction(Symbole fonction, Noeud a) {
        code.append(fonction.getType()).append(" ").append(fonction.getNom()).append("() {\n");
        code.append("  PUSH(BP);\n");
        code.append("  PUSH(BP);\n");
        code.append("  MOVE(SP, BP);\n");
        int nombreVariables = fonction.getNbVar();
        code.append("  ALLOCATE(").append(nombreVariables).append(");\n");
        for (Noeud f : a.getFils()) {
            code.append("  ").append(generer_instruction(f)).append("\n");
        }
        code.append("}\n");
    }

    private String generer_instruction(Noeud f) {
        return "// Instruction pour " + f.getLabel();
    }

    // Getter
    public String getCode() {
        return code.toString();
    }
}