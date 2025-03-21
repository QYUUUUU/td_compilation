package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Bloc;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.TantQue;

/**
 * TantQueGenerateur
 * Générateur de code pour les instructions TantQue
 */
public class TantQueGenerateur extends InstructionGenerateur {

    /**
     * Fonction genererTantQue
     *
     * @param noeud Noeud TantQue à générer
     **/
    public StringBuilder genererTantQue(TantQue noeud) {
        StringBuilder stringRes = new StringBuilder();
        stringRes.append("\tCMP(R1,R2)\n");
        Noeud condition = noeud.getCondition(); //Récupération de la condition
        Bloc instructions = noeud.getBloc(); //Récupération du bloc d'instructions
        //Génération de la condition en tant qu'expression
        ExpressionGenerateur expressionG = new ExpressionGenerateur();
        stringRes.append("TANT_QUE_" + expressionG.getId() + " :\n");
        stringRes.append(expressionG.genererExpression(condition, "INSTRUCTIONS_" + expressionG.getId(), false));
        //Génération du contenu du bloc d'instructions
        stringRes.append("INSTRUCTIONS_" + expressionG.getId() + " :\n");
        stringRes.append(genererBloc(instructions));
        stringRes.append("FINTQ_\n");
        return stringRes;
    }
}