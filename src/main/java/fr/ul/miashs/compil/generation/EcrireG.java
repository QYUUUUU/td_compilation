package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Noeud;

public class EcrireG extends InstructionG {
    public StringBuilder generer_ecrire(Noeud ecrire) {
        StringBuilder res = new StringBuilder();
        ExpressionG expr = new ExpressionG();
        expr.generer_expression(ecrire.getFils().get(0), expr.getId()); // en generant l'exp (qui gère aussi le cas d'une constante ou d'un idf) on sait qu'il y a en haut de la pile le result à print
        res=res.append("\tPOP(R0)\n"); //WRINT() print automatiquement ce qui se trouve dans le registre R0
        res=res.append("\tWRINT()\n");
        return(res);

    }
}
