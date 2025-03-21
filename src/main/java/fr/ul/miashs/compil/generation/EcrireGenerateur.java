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
        Noeud.Categories catFilsPorteur=ecrire.getFils().get(0).getCat();
        Noeud.Categories[] listCat={Noeud.Categories.PROG, Noeud.Categories.FONCTION, Noeud.Categories.BLOC, Noeud.Categories.AFF, Noeud.Categories.SI, Noeud.Categories.TQ, Noeud.Categories.ECR, Noeud.Categories.RET, Noeud.Categories.LIRE, Noeud.Categories.APPEL};

        boolean contains=false;

        for(int cat=0; cat<listCat.length; cat++) {
            if(catFilsPorteur.equals(listCat[cat])) {
                contains = true;
            }
        }
        if(contains) {
            System.out.println(ecrire.getFils().get(0).getLabel());
            InstructionGenerateur instruct=new InstructionGenerateur();
            stringRes.append(instruct.genererInstruction(ecrire.getFils().get(0)));

        }

        stringRes.append(expr.genererExpression(ecrire.getFils().get(0), expr.getId(), false));
        stringRes.append("\tPOP(R0)\n");
        stringRes.append("\tWRINT()\n");
        return (stringRes);
    }
}
