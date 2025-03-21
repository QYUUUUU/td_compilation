package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Noeud;

public class EcrireGenerateur extends InstructionGenerateur {
    public StringBuilder genererEcrire(Noeud ecrire) {
        StringBuilder stringRes = new StringBuilder();
        ExpressionGenerateur expr = new ExpressionGenerateur();
        stringRes.append(expr.genererExpression(ecrire.getFils().get(0), expr.getId(), false));
        stringRes.append("\tPOP(R0)\n");
        stringRes.append("\tWRINT()\n");
        return (stringRes);
    }
}
