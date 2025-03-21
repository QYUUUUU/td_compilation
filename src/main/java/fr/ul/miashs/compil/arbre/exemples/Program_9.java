package fr.ul.miashs.compil.arbre.exemples;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.generation.Generateur;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Exemple #5 Variables locales et paramètres
 */

public class Program_9 {
    public static void main(String[] args) {
        //création des noeuds

        Prog prog = new Prog();
        Fonction f = new Fonction("f");
        Retour retour = new Retour(f);
        Affectation affectF = new Affectation();
        Idf res = new Idf("res");
        Plus plusF = new Plus();
        Multiplication mulF = new Multiplication();
        Division divF = new Division();
        Moins moinsF = new Moins();
        Const const2 = new Const(2);
        Const const3 = new Const(3);
        Const const5 = new Const(5);
        Idf idfParam1 = new Idf("a");//pourquoi choisir le meme id pour a et pas pour b? qui devrait d'appeler c dans la logique
        Idf idfParam2 = new Idf("b");


        Fonction main = new Fonction("main");
        Ecrire ecMain = new Ecrire();
        Appel apMain = new Appel("f");//la fonction main appelle la fonction f avec les paramètres a et c (constantes globales)
        Idf const1Main = new Idf("a");// valeurs à changer en cas d'execution
        Idf const2Main = new Idf("c");// est ce que on remplace avec le valeure définie par l'exercice pour la var globale?


        //on relie les noeuds

        prog.ajouterUnFils(f);

        f.ajouterUnFils(affectF); //fonction f partie "gauche" (impossible de def filsG et filsD pour une fonction => le rajouter?
        affectF.setFilsGauche(res);
        affectF.setFilsDroit(plusF);
        plusF.setFilsDroit(mulF);
        mulF.setFilsGauche(idfParam1);
        mulF.setFilsDroit(const2);
        plusF.setFilsGauche(divF);
        divF.setFilsGauche(moinsF);
        moinsF.setFilsGauche(idfParam2);
        moinsF.setFilsDroit(const5);
        divF.setFilsDroit(const3);

        f.ajouterUnFils(retour); //fonction f partie "droite"
        retour.ajouterUnFils(res);

        prog.ajouterUnFils(main); //fonction main
        main.ajouterUnFils(ecMain);
        ecMain.ajouterUnFils(apMain);
        apMain.ajouterUnFils(const1Main);
        apMain.ajouterUnFils(const2Main);


        TDS tds = new TDS();


        tds.addSymbole(new Symbole("main", "void", "fonction", null, null));
        tds.addSymbole(new Symbole("f", "int", "fonction", 2, 1));
        tds.addSymbole(new Symbole("a", "int", "global", null, null, 100));
        tds.addSymbole(new Symbole("c", "int", "global", null, null, 170));
        tds.addSymbole(new Symbole("a", "int", "param", "f", 0, null));
        tds.addSymbole(new Symbole("b", "int", "param", "f", 1, null));
        tds.addSymbole(new Symbole("res", "int", "local", "f", 0, null));

        //afficher
        //TxtAfficheur.afficher(prog);
        //GuiAfficheur.afficher(prog);

        //Generer le code assembleur
        Generateur gen = new Generateur(prog, tds);
        String stringReturn= gen.generer().toString();
        try {
            FileWriter fw = new FileWriter("src\\main\\java\\fr\\ul\\miashs\\compil\\generation\\tests\\assembleurGenere\\Programme9Assembleur.txt");
            fw.write(stringReturn);
            fw.close();
            System.out.println("Le texte a été écrit avec succès");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite");

        }
    }
}