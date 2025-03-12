package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Bloc;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.TantQue;

public class TantQueG extends InstructionG {

    public StringBuilder exp = new StringBuilder();

    public StringBuilder genererTantQue(TantQue noeud) {
        this.exp.append("\tCMP(R1,R2)\n");

        Noeud condition = noeud.getCondition();//Récupération de la condition
        Bloc blocInstructions = noeud.getBloc();//Récupération du bloc d'instructions à faire
        ExpressionG expressionG = new ExpressionG();
        expressionG.generer_expression(condition, condition.getLabel());//Génération de la condition
        InstructionG instructionG = new InstructionG();
        instructionG.generer_instruction(blocInstructions);//Génération du bloc d'instructions
        return this.exp;
    }
}