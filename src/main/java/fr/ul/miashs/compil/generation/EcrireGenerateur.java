package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Noeud;

/**
 * EcrireGenerateur
 * Génération de l'instruction Ecrire
 */
public class EcrireGenerateur extends InstructionGenerateur {

    /**
     * Fonction genererEcrire
     * @return StringBuilder
     * retourne le code assembleur de l'instruction Ecrire
     **/
    public StringBuilder genererEcrire(Noeud ecrire) {
        StringBuilder stringRes = new StringBuilder();
        ExpressionGenerateur expr = new ExpressionGenerateur();
        // On génère l'expression à écrire
        stringRes.append(expr.genererExpression(ecrire.getFils().get(0), expr.getId(), false));
        stringRes.append("\tPOP(R0)\n");
        stringRes.append("\tWRINT()\n");
        return (stringRes);
    }
}
