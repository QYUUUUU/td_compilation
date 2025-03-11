package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.TDS;

public class TantQueG {

    public StringBuilder exp = new StringBuilder();

    public StringBuilder genererTantQue(TantQue noeud) {
        this.exp.append("\tCMP(R1,R2)\n");

        Noeud condition = noeud.getCondition();
        Bloc instructions = noeud.getBloc();
        ExpressionG expressionG= new ExpressionG();
        expressionG.generer_expression(condition, condition.getLabel());
        InstructionG instructionG = new InstructionG();
        instructionG.generer_instruction(instructions);


        return this.exp;
    }
}