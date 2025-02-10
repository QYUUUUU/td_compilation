package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.Symbole;


import java.util.List;

public class Expression {
    /**
     * @return StringBuilder
     * retourne le code assembleur du fils droit d'une affectation: une expression
     * porteur est un noeud + - * /... detecté dans une autre fonction et qui engendre l'appel de generer_fonction
     **/

    public StringBuilder generer_expression(Noeud porteur) {
        StringBuilder expr_string = new StringBuilder();
        expr_string.append("\tPUSH(BP);\n");//on place le marqueur du début de frame dans la pile // TODO Revoir si c'est bon
        expr_string.append("\tMOVE(SP, BP);\n");//on fait pointer SP sur la pile

        List<Noeud> noeudExpr = porteur.getFils();
        for (int nbFils = 0; nbFils < 2; nbFils++) {


            if ((noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.CONST) | (noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.IDF)))) {// on cherche les expressions terminales
                int val;
                if(noeudExpr.get(nbFils) instanceof Const){ // pour pouvoir utiliser la fonction getValeur()
                    val=((Const) noeudExpr.get(nbFils)).getValeur();
                    expr_string.append("\tLDR("+val+",R0)\n");
                   expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire

                }
                else if(noeudExpr.get(nbFils) instanceof Idf){

                    Object obj=((Idf)noeudExpr.get(nbFils)).getValeur(); // Idf peut être quel autre type de var que int?
                    if(obj instanceof Integer){
                        val=(Integer)obj;
                        expr_string.append("\tLDR("+val+",R0)\n");
                        expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                    }

                }
                else{
                    if (noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.AFF)) { // dans le cas où on aurait (a=2+3)+4
                        Affect aff = new Affect(); //TODO faire la classe Affect avec la fonction generer_aff()
                        expr_string.append(generer_aff());
                    } else {
                        this.generer_expression(noeudExpr.get(nbFils));// On génère l'expression du noeud fils qui est entrain d'être vu
                    }

                }
            }
            expr_string.append("\tPOP(R1)\n");//On récupère nos variables conservées dans la mémoire (le fils le plus à droite est au dessus de la pile
            expr_string.append("\tPOP(R2)\n");
                switch (porteur.getCat()) {

                    case PLUS:
                        expr_string.append("\tADD(R1,R2,R0)\n");
                        expr_string.append("\tPUSH(R0)\n");
                        break;


                    case DIV:
                        expr_string.append("\tDIV(R1,R2,R0)\n");
                        expr_string.append("\tPUSH(R0)\n");
                        break;

                    case MUL:
                        expr_string.append("\tMUL(R1,R2,R0)\n");
                        expr_string.append("\tPUSH(R0)\n");
                        break;

                    case MOINS:

                        expr_string.append("\tSUB(R1,R2,R0)\n");
                        expr_string.append("\tPUSH(R0)\n");
                        break;

                        //TODO

                    case SUP:
                        expr_string.append("\tCMP(R1,R2)\n");// on compare les deux et on jump vers la partie "greater" (Voir comment et ou se créé cette partie (consitionnelles? )
                        expr_string.append("\tJG greater\n");
                        break;

                    case INF:
                        expr_string.append("\tCMP(R1,R2)\n");
                        expr_string.append("\tJL less\n");
                        break;

                    case SUPE:
                        expr_string.append("\tCMP(R1,R2)\n");
                        expr_string.append("\tJGE greaterEq\n");
                        break;

                    case INFE:
                        expr_string.append("\tCMP(R1,R2)\n");
                        expr_string.append("\tJLE lessEq\n");
                        break;

                    case EG:
                        expr_string.append("\tCMP(R1,R2)\n");
                        expr_string.append("\tJE equal\n");
                        break;

                    case DIF:
                        expr_string.append("\tCMP(R1,R2)\n");
                        expr_string.append("\tJNE notEqual\n");
                        break;

                }
            }
        return (expr_string); // on revoie la string de toute l'expression
    }

}

