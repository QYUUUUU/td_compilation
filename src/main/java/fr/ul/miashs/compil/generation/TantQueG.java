package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Bloc;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.TantQue;

public class TantQueG extends InstructionG {

    public StringBuilder exp = new StringBuilder();

    public StringBuilder genererTantQue(TantQue noeud) {
        this.exp.append("\tCMP(R1,R2)\n");

        Noeud condition = noeud.getCondition(); //Récupération de la condition
        Bloc instructions = noeud.getBloc(); //Récupération du bloc d'instructions

        //Génération de la condition en tant qu'expression
        ExpressionG expressionG = new ExpressionG();
        this.exp.append("TANT_QUE_" + expressionG.getId() + " :\n");
        this.exp.append(expressionG.generer_expression(condition, expressionG.getId()));
        //Génération du contenu du bloc d'instructions
        this.exp.append("INSTRUCTIONS_" + expressionG.getId() + " :\n");
        this.exp.append(generer_bloc(instructions));
        this.exp.append("FINTQ_\n");
        return this.exp;
    }
}