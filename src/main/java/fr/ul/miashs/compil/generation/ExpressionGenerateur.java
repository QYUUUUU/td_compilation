package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;

import java.util.List;
import java.util.Random;

public class ExpressionGenerateur extends AffectationGenerateur {
    /**
     * @return StringBuilder
     * retourne le code assembleur du fils droit d'une affectation: une expression
     * porteur est un noeud + - * /... detecté dans une autre fonction et qui engendre l'appel de generer_fonction
     **/
    private String id;

    public ExpressionGenerateur() {
        id = this.FabId();
    }

    public StringBuilder genererExpression(Noeud porteur, String id, boolean si) {


        StringBuilder stringRes = new StringBuilder();
        stringRes.append("\tPUSH(BP);\n");//on place le marqueur du début de frame dans la pile // TODO Revoir si c'est bon
        stringRes.append("\tMOVE(SP, BP);\n");//on fait pointer SP sur la pile


        System.out.println(porteur.getCat());
        System.out.println(porteur.getFils());
        if (porteur.getFils() == null) {
            int val;
            System.out.println("expr=null" + porteur.getCat());
            if (porteur instanceof Const) { // pour pouvoir utiliser la fonction getValeur()
                val = ((Const) porteur).getValeur();
                stringRes.append("\tLDR(R0," + val + ")\n");
                stringRes.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                return (stringRes);
            } else if (porteur instanceof Idf) {
                System.out.println("Soy yo");
                stringRes.append("\tLDR(R0," + ((Idf) porteur).getValeur() + ")\n");
                stringRes.append("\tPUSH(R0)\n");// on envoie le fils en mémoire
                return (stringRes);
            } else if (porteur.getCat().equals(Noeud.Categories.AFF)) { // dans le cas où on aurait (a=2+3)+4
                AffectationGenerateur aff = new AffectationGenerateur();
                stringRes.append(aff.genererAffectation((Affectation) porteur));
                return (stringRes);
            } else if (porteur.getCat().equals(Noeud.Categories.LIRE)) {
                stringRes.append("JMP LIRE_" + id + "\n");//lanceur
                stringRes.append(this.generer_lire(this.getId()));
                return (stringRes);
            
            } else {
                System.out.println("Erreur");
                System.exit(1);
            }
            return (stringRes);
        } else {
            stringRes.append("\tPOP(R1)\n");//On récupère nos variables conservées dans la mémoire (le fils le plus à droite est au dessus de la pile
            stringRes.append("\tPOP(R2)\n");
            switch (porteur.getCat()) {
                case PLUS:
                    stringRes.append("\tADD(R1,R2,R0)\n");
                    stringRes.append("\tPUSH(R0)\n");
                    break;
                case DIV:
                    stringRes.append("\tDIV(R1,R2,R0)\n");
                    stringRes.append("\tPUSH(R0)\n");
                    break;
                case MUL:
                    stringRes.append("\tMUL(R1,R2,R0)\n");
                    stringRes.append("\tPUSH(R0)\n");
                    break;
                case MOINS:
                    stringRes.append("\tSUB(R1,R2,R0)\n");
                    stringRes.append("\tPUSH(R0)\n");
                    break;
                case SUP:
                    stringRes.append("\tCMP(R1,R2)\n");// on compare les deux et on jump vers la partie voulue
                    if (si) {
                        System.out.println("J'écris la cond");
                        stringRes.append("\tJG " + "ALORS_" + id + "\n");
                        stringRes.append("\tJLE " + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJG " + id + "\n");
                    }
                    break;
                case INF:
                    stringRes.append("\tCMP(R1,R2)\n");
                    if (si) {
                        stringRes.append("\tJL " + "ALORS_" + id + "\n");
                        stringRes.append("\tJGE " + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJL " + id + "\n");
                    }
                    break;
                case SUPE:
                    stringRes.append("\tCMP(R1,R2)\n");
                    if (si) {
                        stringRes.append("\tJGE" + "ALORS_" + id + "\n");
                        stringRes.append("\tJL" + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJGE " + id + "\n");
                    }
                    break;
                case INFE:
                    stringRes.append("\tCMP(R1,R2)\n");
                    if (si) {
                        stringRes.append("\tJLE" + "ALORS_" + id + "\n");
                        stringRes.append("\tJG" + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJLE " + id + "\n");
                    }
                    break;
                case EG:
                    stringRes.append("\tCMP(R1,R2)\n");
                    if (si) {
                        stringRes.append("\tJE " + "ALORS_" + id + "\n");
                        stringRes.append("\tJNE " + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJE " + id + "\n");
                    }
                    break;
                case DIF:
                    stringRes.append("\tCMP(R1,R2)\n");
                    if (si) {
                        stringRes.append("\tJNE " + "ALORS_" + id + "\n");
                        stringRes.append("\tJE " + "SINON_" + id + "\n");
                    } else {
                        stringRes.append("\tJNE " + id + "\n");
                    }
                    break;
            }
        }
        List<Noeud> noeudExpr = porteur.getFils();
        for (Noeud noeud : noeudExpr) {
            stringRes.append(this.genererExpression(noeud, this.id, false));// On génère l'expression du noeud fils qui est entrain d'être vu
        }
        return (stringRes);
    }

    public StringBuilder generer_lire(String id) {
        //TODO Vérifier en groupe la véracité de l'assembleur
        StringBuilder expr_string = new StringBuilder();
        expr_string.append(
                "\n" +
                        "LIRE_" + id + ":\n" +
                        "    input resb 100  \n" + // reserver 100 octets et permettre un tampon
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


