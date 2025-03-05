package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.TDS;

public class TantQueG {

    public StringBuilder exp = new StringBuilder();

    public StringBuilder genererTantQue(Noeud noeud) {
        this.exp.append("\tCMP(R1,R2)\n");

        switch (noeud.getCat()) {
            case INF:
                Inferieur inf = (Inferieur) noeud;
                this.exp.append("\tJL less\n");
                while (getValue(inf.getFilsGauche()) < getValue(inf.getFilsDroit())) {

                }
                break;
            case SUP:
                Superieur sup = (Superieur) noeud;
                this.exp.append("\tJG greater\n");
                while (getValue(sup.getFilsGauche()) > getValue(sup.getFilsDroit())) {

                }
                break;
            case INFE:
                InferieurEgal infe = (InferieurEgal) noeud;
                this.exp.append("\tJLE lessEq\n");
                while (getValue(infe.getFilsGauche()) <= getValue(infe.getFilsDroit())) {

                }
                break;
            case SUPE:
                SuperieurEgal supe = (SuperieurEgal) noeud;
                this.exp.append("\tJGE greaterEq\n");
                while (getValue(supe.getFilsGauche()) >= getValue(supe.getFilsDroit())) {

                }
                break;
            case EG:
                Egal eg = (Egal) noeud;
                this.exp.append("\tJE equal\n");
                while (getValue(eg.getFilsGauche()) == getValue(eg.getFilsDroit())) {

                }
                break;
            case DIF:
                Different dif = (Different) noeud;
                this.exp.append("\tJNE notEqual\n");
                while (getValue(dif.getFilsGauche()) != getValue(dif.getFilsDroit())) {

                }
                break;
        }

        return this.exp;
    }

    public int getValue(Noeud noeudVar) {
        if ((noeudVar.getCat().equals(Noeud.Categories.CONST) | (noeudVar.getCat().equals(Noeud.Categories.IDF)))) {
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
                    AffectationG genetAff=new AffectationG();
                    this.exp.append(genetAff.generer_affectation(aff));
                }
            }
        }
        this.exp.append("\tPOP(R1)\n");//On récupère nos variables conservées dans la mémoire (le fils le plus à droite est au dessus de la pile
        this.exp.append("\tPOP(R2)\n");
    }
}