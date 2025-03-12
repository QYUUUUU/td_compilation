package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.Si;
import fr.ul.miashs.compil.tds.TDS;

import java.util.List;
import java.util.Random;

public class ExpressionG extends AffectationG{
    /**
     * @return StringBuilder
     * retourne le code assembleur du fils droit d'une affectation: une expression
     * porteur est un noeud + - * /... detecté dans une autre fonction et qui engendre l'appel de generer_fonction
     **/
    private String id;
    public ExpressionG(){
        id=this.FabId();
    }
    public StringBuilder generer_expression(Noeud porteur, String id) {



        StringBuilder expr_string = new StringBuilder();
        expr_string.append("\tPUSH(BP);\n");//on place le marqueur du début de frame dans la pile // TODO Revoir si c'est bon
        expr_string.append("\tMOVE(SP, BP);\n");//on fait pointer SP sur la pile

        List<Noeud> noeudExpr = porteur.getFils();
        for (int nbFils = 0; nbFils < 2; nbFils++) {


            if ((noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.CONST) | (noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.IDF)))) {// on cherche les expressions terminales
                int val;
                if (noeudExpr.get(nbFils) instanceof Const) { // pour pouvoir utiliser la fonction getValeur()
                    val = ((Const) noeudExpr.get(nbFils)).getValeur();

                    expr_string.append("\tLDR(R0," + val + ")\n");
                    expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire

                } else if (noeudExpr.get(nbFils) instanceof Idf) {

                    Object obj = ((Idf) noeudExpr.get(nbFils)).getValeur(); // Idf peut être quel autre type de var que int?
                    if (obj instanceof Integer) {
                        val = (Integer) obj;
                        expr_string.append("\tLDR(R0," + val + ")\n");
                        expr_string.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                    }

                } else {
                    if (noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.AFF)) { // dans le cas où on aurait (a=2+3)+4
                        AffectationG aff = new AffectationG();
                        expr_string.append(aff.generer_affectation((fr.ul.miashs.compil.arbre.Affectation) noeudExpr.get(nbFils)));
                    } else if (noeudExpr.get(nbFils).getCat().equals(Noeud.Categories.LIRE)) {
                        this.generer_lire(noeudExpr.get(nbFils));

                    } else {
                        this.generer_expression(noeudExpr.get(nbFils),this.id);// On génère l'expression du noeud fils qui est entrain d'être vu
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

                case SUP:
                    expr_string.append("\tCMP(R1,R2)\n");// on compare les deux et on jump vers la partie "greater" (Voir comment et ou se créé cette partie (consitionnelles? )

                    if(porteur instanceof Si){
                        expr_string.append("\tJG ALORS_"+id+"\n");
                        expr_string.append("\tJLE SINON_+\n");
                    }
                    expr_string.append("\tJG "+id+"\n");
                    break;

                case INF:
                    expr_string.append("\tCMP(R1,R2)\n");

                    if(porteur instanceof Si){
                        expr_string.append("\tJL ALORS_"+id+"\n");
                        expr_string.append("\tJGE SINON_"+id+"\n");
                    }

                    expr_string.append("\tJL "+id+"\n");
                    break;

                case SUPE:
                    expr_string.append("\tCMP(R1,R2)\n");

                    if(porteur instanceof Si){
                        expr_string.append("\tJGE ALORS_"+id+"\n");
                        expr_string.append("\tJL SINON_"+id+"\n");
                    }

                    expr_string.append("\tJGE "+id+"\n");
                    break;

                case INFE:
                    expr_string.append("\tCMP(R1,R2)\n");

                    if(porteur instanceof Si){
                        expr_string.append("\tJLE ALORS_"+id+"\n");
                        expr_string.append("\tJG SINON_"+id+"\n");
                    }

                    expr_string.append("\tJLE "+id+"\n");
                    break;

                case EG:
                    expr_string.append("\tCMP(R1,R2)\n");

                    if(porteur instanceof Si){
                        expr_string.append("\tJE ALORS_"+id+"\n");
                        expr_string.append("\tJNE SINON_"+id+"\n");
                    }

                    expr_string.append("\tJE "+id+"\n");
                    break;

                case DIF:
                    expr_string.append("\tCMP(R1,R2)\n");

                    if(porteur instanceof Si){
                        expr_string.append("\tJNE ALORS_"+id+"\n");
                        expr_string.append("\tJE SINON_"+id+"\n");
                    }

                    expr_string.append("\tJNE "+id+"\n");
                    break;

            }

        }
        return (expr_string); // on renvoie la string de toute l'expression
    }


        public StringBuilder generer_lire (Noeud lire){
        //TODO Vérifier en groupe la véracité de l'assembleur
            StringBuilder expr_string = new StringBuilder();
            expr_string.append("section .bss\n" +
                    "    input resb 100  \n" + // reserver 100 octets et permettre un tampon
                    "    global _start\n" +//lanceur
                    "\n" +
                    "_start:\n" +
                    "    MOV (rax, 0) \n"+//vers sys_read
                    "    MOV (rdi, 0)  \n"+//vers stdin
                    "    MOV (rsi, input) \n"+//pointeur vers tampon
                    "    MOV (rdx, 100) \n"+ // alloue le bon nb d'octet
                    "    syscall\n "+ // commande à l'origine linux mais appremment fonctionne sous Windows
                    //normalement, l'input est dans rax
                    "   MOV (R0,rax)\n"+
                    "   PUSH (R0)\n");
            return(expr_string);
        }
        public String FabId(){
            int newID=0;
            boolean same=true;
            while(same==true) {
                Random rnd = new Random();
                newID=rnd.nextInt(100);
                same = false;
                for (int id = 0; id < Generateur.tableID.size(); id++) {
                    if (id == newID) {
                        same = true;
                        break;
                    }
                }
            }
            return(String.valueOf(newID));
        }
        public String getId(){
            return(this.id);
        }
    }

