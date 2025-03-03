package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;

public class SiG {
    private static int compteurLabel = 0;

    public StringBuilder generer_si(fr.ul.miashs.compil.arbre.Si noeudSi, TDS tds) {
        StringBuilder si_string = new StringBuilder();

        // Générer une étiquette unique pour la sortie du bloc sinon
        int idEtiquette = compteurLabel++;
        String labelSinon = "SINON_" + idEtiquette;
        String labelFin = "FINSI_" + idEtiquette;

        // Générer le code pour la condition
        ExpressionG expressionGen = new ExpressionG();
        si_string.append(expressionGen.generer_expression(noeudSi.getCondition(),tds));

        // Récupérer le résultat de la condition
        si_string.append("\tPOP(R0)\n");
        si_string.append("\tCMP(R0, #0)\n");
        si_string.append("\tJE " + labelSinon + "\n");

        // Générer le bloc "alors"
        si_string.append(generer_bloc(noeudSi.getBlocAlors(),tds));
        si_string.append("\tJMP " + labelFin + "\n");

        // Générer le bloc "sinon"
        si_string.append(labelSinon + ":\n");
        si_string.append(generer_bloc(noeudSi.getBlocSinon(),tds));

        // Fin du bloc Si
        si_string.append(labelFin + ":\n");

        return si_string;
    }

    private StringBuilder generer_bloc(Bloc bloc,TDS tds) {
        StringBuilder bloc_string = new StringBuilder();
        List<Noeud> instructions = bloc.getFils();
        ExpressionG expressionGen = new ExpressionG();

        for (Noeud instruction : instructions) {
            if (instruction.getCat().equals(Noeud.Categories.PLUS) ||
                    instruction.getCat().equals(Noeud.Categories.MOINS) ||
                    instruction.getCat().equals(Noeud.Categories.MUL) ||
                    instruction.getCat().equals(Noeud.Categories.DIV) ||
                    instruction.getCat().equals(Noeud.Categories.SUP) ||
                    instruction.getCat().equals(Noeud.Categories.INF) ||
                    instruction.getCat().equals(Noeud.Categories.SUPE) ||
                    instruction.getCat().equals(Noeud.Categories.INFE) ||
                    instruction.getCat().equals(Noeud.Categories.EG) ||
                    instruction.getCat().equals(Noeud.Categories.DIF) ||
                    instruction.getCat().equals(Noeud.Categories.CONST) ||
                    instruction.getCat().equals(Noeud.Categories.IDF)) {
                bloc_string.append(expressionGen.generer_expression(instruction,tds));
            } else if (instruction instanceof fr.ul.miashs.compil.arbre.Si) {
                bloc_string.append(generer_si((fr.ul.miashs.compil.arbre.Si) instruction,tds));
            } //TODO rajouter les autres types de noeuds qui existent et les prendre en charge.
        }

        return bloc_string;
    }
}

