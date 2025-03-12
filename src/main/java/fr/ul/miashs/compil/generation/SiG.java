package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;

public class SiG extends InstructionG{

    public StringBuilder generer_si(Si noeudSi) {
        StringBuilder si_string = new StringBuilder();

        // Générer une étiquette unique pour la sortie du bloc sinon
        String labelSinon = "SINON_" ;
        String labelFin = "FINSI_" ;
        String labelAlors="ALORS_";

        ExpressionG expressionGen = new ExpressionG();

        // Générer le bloc "alors"
        si_string.append(labelAlors+expressionGen.getId()+" :\n");
        si_string.append(expressionGen.generer_bloc(noeudSi.getBlocAlors()));
        si_string.append("JMP "+labelSinon+expressionGen.getId()+"\n"); //o jump vers le sinon, même si il est vide

        // Générer le bloc "sinon"
        si_string.append(labelSinon + expressionGen.getId()+" :\n");
        if (noeudSi.getBlocSinon()!=null){si_string.append(generer_bloc(noeudSi.getBlocSinon()));}
        si_string.append("JMP "+labelFin+"\n"); // on jump vers la fin

        // Générer le code pour la condition

        si_string.append(expressionGen.generer_expression(noeudSi.getCondition(),expressionGen.getId()));// va envoyer vers le label ALORS ou SINON et démarrer l'algo


        // Fin du bloc Si
        si_string.append(labelFin + " :\n");

        return si_string;
    }


}

