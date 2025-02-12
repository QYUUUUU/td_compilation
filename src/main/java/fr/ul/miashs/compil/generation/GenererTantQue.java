package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;

public class GenererTantQue {

    public final TantQue noeud;
    public StringBuilder exp = new StringBuilder();

    public GenererTantQue(TantQue tq) {
        this.noeud = tq;
    }

    public StringBuilder genererTantQue(TantQue noeud) {
        Noeud cond = this.noeud.getCondition();
        this.exp.append("\tCMP(R1,R2)\n");

        switch (cond.getCat()) {
            case INF:
                Inferieur inf = (Inferieur) cond;
                this.exp.append("\tJL less\n");
                while (getValue(inf.getFilsGauche()) < getValue(inf.getFilsDroit())) {

                }
                break;
            case SUP:
                Superieur sup = (Superieur) cond;
                this.exp.append("\tJG greater\n");
                while (getValue(sup.getFilsGauche()) > getValue(sup.getFilsDroit())) {

                }
                break;
            case INFE:
                InferieurEgal infe = (InferieurEgal) cond;
                this.exp.append("\tJLE lessEq\n");
                while (getValue(infe.getFilsGauche()) <= getValue(infe.getFilsDroit())) {

                }
                break;
            case SUPE:
                SuperieurEgal supe = (SuperieurEgal) cond;
                this.exp.append("\tJGE greaterEq\n");
                while (getValue(supe.getFilsGauche()) >= getValue(supe.getFilsDroit())) {

                }
                break;
            case EG:
                Egal eg = (Egal) cond;
                this.exp.append("\tJE equal\n");
                while (getValue(eg.getFilsGauche()) == getValue(eg.getFilsDroit())) {

                }
                break;
            case DIF:
                Different dif = (Different) cond;
                this.exp.append("\tJNE notEqual\n");
                while (getValue(dif.getFilsGauche()) != getValue(dif.getFilsDroit())) {

                }
                break;
        }

        return this.exp;
    }

    public int getValue(Noeud noeudVar) {
        if ((noeudVar.getCat().equals(Noeud.Categories.CONST) | (noeudVar.getCat().equals(Noeud.Categories.IDF)))) {
            int val;
            if (noeudVar instanceof Const) {
                return ((Const) noeudVar).getValeur();
            } else if (noeudVar instanceof Idf) {
                Object obj = ((Idf) noeudVar).getValeur(); // Idf peut être quel autre type de var que int?
                if (obj instanceof Integer) {
                    return (Integer) obj;
                }

            } else {
                if (noeudVar.getCat().equals(Noeud.Categories.AFF)) { // dans le cas où on aurait (a=2+3)+4
                    Affectation aff = new Affectation(); //TODO faire la classe Affect avec la fonction generer_aff()
                    this.exp.append(generer_affac());
                } else {
                    this.noeud.getBloc().generer_expression(noeudExpr.get(nbFils));// On génère l'expression du noeud fils qui est entrain d'être vu
                }

            }
        }
        this.exp.append("\tPOP(R1)\n");//On récupère nos variables conservées dans la mémoire (le fils le plus à droite est au dessus de la pile
        this.exp.append("\tPOP(R2)\n");
    }
}