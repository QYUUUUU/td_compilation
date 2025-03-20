package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import org.w3c.dom.traversal.NodeIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpressionG extends AffectationG {
    /**
     * @return StringBuilder
     * retourne le code assembleur du fils droit d'une affectation: une expression
     * porteur est un noeud + - * /... detecté dans une autre fonction et qui engendre l'appel de generer_fonction
     **/
    private String id;

    public ExpressionG() {
        id = this.FabId();
    }

    public StringBuilder generer_expression(Noeud porteur, String id, boolean si) {


        StringBuilder expr_string = new StringBuilder();
        expr_string.append("\tPUSH(BP);\n");//on place le marqueur du début de frame dans la pile // TODO Revoir si c'est bon
        expr_string.append("\tMOVE(SP, BP);\n");//on fait pointer SP sur la pile


        List<Noeud>noeudExpr = porteur.getFils();


        System.out.println("entrée expression");
        System.out.println(si);
        if(noeudExpr==null){
            //if ((porteur.getCat().equals(Noeud.Categories.CONST) | porteur.getCat().equals(Noeud.Categories.IDF))) {// on cherche les expressions terminales
                int val;
                System.out.println("expr=null"+porteur.getCat());
                if (porteur instanceof Const) { // pour pouvoir utiliser la fonction getValeur()
                    val = ((Const) porteur).getValeur();

                    expr_string.append("\tLDR(R0," + val + ")\n");
                    expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                    return(expr_string);

                } else if (porteur instanceof Idf) {
                    System.out.println("Soy yo");
                    //Object obj = ((Idf) porteur).getValeur(); // Idf peut être quel autre type de var que int?
                    //if (obj instanceof Integer) {
                        //val = (Integer) obj;
                    expr_string.append("\tLDR(R0," + ((Idf) porteur).getValeur() + ")\n");
                    expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                    return (expr_string);
                    //}

                }else{
                    System.out.println("Erreur");
                    System.exit(1);
                }
                return(expr_string);
        }else {
                for (int nbFils = 0; nbFils < noeudExpr.size(); nbFils++){
                        if (porteur.getCat().equals(Noeud.Categories.AFF)) { // dans le cas où on aurait (a=2+3)+4
                            AffectationG aff = new AffectationG();
                            expr_string.append(aff.generer_affectation((Affectation)porteur));
                        } else if (porteur.getCat().equals(Noeud.Categories.LIRE)) {
                            this.generer_lire(porteur);

                        } else {
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

                                case SUP:
                                    expr_string.append("\tCMP(R1,R2)\n");// on compare les deux et on jump vers la partie voulue

                                    if (si==true) {
                                        System.out.println("J'écris la cond");
                                        expr_string.append("\tJG " + "ALORS_"+id + "\n");
                                        expr_string.append("\tJLE "+"SINON_"+id+"\n");
                                    }
                                    else {
                                        expr_string.append("\tJG " + id + "\n");
                                    }
                                    break;

                                case INF:
                                    expr_string.append("\tCMP(R1,R2)\n");

                                    if (si) {
                                        expr_string.append("\tJL " + "ALORS_"+ id + "\n");
                                        expr_string.append("\tJGE " +"SINON_"+ id + "\n");
                                    }
                                    else {
                                        expr_string.append("\tJL " + id + "\n");
                                    }

                                    break;

                                case SUPE:
                                    expr_string.append("\tCMP(R1,R2)\n");

                                    if (si) {
                                        expr_string.append("\tJGE" + "ALORS_"+ id + "\n");
                                        expr_string.append("\tJL" +"SINON_"+ id + "\n");
                                    }
                                    else {
                                        expr_string.append("\tJGE " + id + "\n");
                                    }
                                    break;

                                case INFE:
                                    expr_string.append("\tCMP(R1,R2)\n");

                                    if (si) {
                                        expr_string.append("\tJLE" + "ALORS_"+ id + "\n");
                                        expr_string.append("\tJG" +"SINON_"+ id + "\n");
                                    }
                                    else {
                                        expr_string.append("\tJLE " + id + "\n");
                                    }
                                    break;

                                case EG:
                                    expr_string.append("\tCMP(R1,R2)\n");

                                    if (si) {
                                        expr_string.append("\tJE " + "ALORS_"+ id + "\n");
                                        expr_string.append("\tJNE " +"SINON_"+ id + "\n");
                                    }
                                    else {
                                        expr_string.append("\tJE " + id + "\n");
                                    }
                                    break;

                                case DIF:
                                    expr_string.append("\tCMP(R1,R2)\n");

                                    if (si) {
                                        expr_string.append("\tJNE " + "ALORS_"+ id + "\n");
                                        expr_string.append("\tJE " +"SINON_"+ id + "\n");
                                    }
                                    else {
                                        expr_string.append("\tJNE " + id + "\n");
                                    }
                                    break;

                            }
                            System.out.println(noeudExpr.get(nbFils).getCat());
                            expr_string.append(this.generer_expression(noeudExpr.get(nbFils), this.id,false));// On génère l'expression du noeud fils qui est entrain d'être vu



                        }



                    }
            return(expr_string);
            }
    }


    public StringBuilder generer_lire(Noeud lire) {
        //TODO Vérifier en groupe la véracité de l'assembleur
        StringBuilder expr_string = new StringBuilder();
        expr_string.append("section .bss\n" +
                "    input resb 100  \n" + // reserver 100 octets et permettre un tampon
                "    global _start\n" +//lanceur
                "\n" +
                "_start:\n" +
                "    MOV (rax, 0) \n" +//vers sys_read
                "    MOV (rdi, 0)  \n" +//vers stdin
                "    MOV (rsi, input) \n" +//pointeur vers tampon
                "    MOV (rdx, 100) \n" + // alloue le bon nb d'octet
                "    syscall\n " + // commande à l'origine linux mais appremment fonctionne sous Windows
                //normalement, l'input est dans rax
                "   MOV (R0,rax)\n" +
                "   PUSH (R0)\n");
        return (expr_string);
    }

    public String FabId() {
        int newID = 0;
        boolean done = true;
        while (done) {
            Random rnd = new Random();
            newID = rnd.nextInt(100);
            done = false;
            System.out.println("newID: " + newID);
            System.out.println("tableID: " + Generateur.tableID);
            if (Generateur.tableID.isEmpty()) {
                Generateur.tableID.add(newID);
                done = true;
            } else {
                if (!Generateur.tableID.contains(newID)) {
                    Generateur.tableID.add(newID);
                    done = true;
                }
            }
        }
        return (String.valueOf(newID));
    }

    public String getId() {
        return (this.id);
    }
}

