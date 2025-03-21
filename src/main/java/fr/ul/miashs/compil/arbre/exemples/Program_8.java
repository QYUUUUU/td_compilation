/*
 *  License and Copyright:
 *
 *  This file is part of arbre  project.
 *
 * MIT License:
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * Copyright 2025 by IDMC, Université de Lorraine (azim)
 * All right reserved
 *
 */

package fr.ul.miashs.compil.arbre.exemples;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.generation.Generateur;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Exemple #8 récursivité
 */

public class Program_8 {
    public static void main(String[] args) {
        //on crée les noeuds
        Prog prog = new Prog();

        Fonction f = new Fonction('f');

        Si si = new Si();
        InferieurEgal inferieurEgal = new InferieurEgal();
        Idf a = new Idf("a");
        Const c1 = new Const(0);
        Bloc bl1 = new Bloc();
        Const c2 = new Const(0);
        Retour retour1 = new Retour(c2);
        Const c3 = new Const(0);

        Retour retour2 = new Retour(c3);
        Plus plus = new Plus();
        Appel appel1 = new Appel("f");
        Moins moins = new Moins();
        Const c4 = new Const(1);

        Fonction main = new Fonction("main");
        Ecrire ecrire = new Ecrire();
        Appel appel2 = new Appel("f");
        Const c5 = new Const(6);

        //on relie les noeuds
        prog.ajouterUnFils(f);

        f.ajouterUnFils(si);
        si.setCondition(inferieurEgal);
        inferieurEgal.setFilsGauche(a);
        inferieurEgal.setFilsDroit(c1);
        si.setBlocAlors(bl1);
        bl1.ajouterUnFils(retour1);
        retour1.setLeFils(c2);

        f.ajouterUnFils(retour2);
        retour2.setLeFils(plus);
        plus.setFilsGauche(a);
        plus.setFilsDroit(appel1);
        appel1.ajouterUnFils(moins);
        moins.setFilsGauche(a);
        moins.setFilsDroit(c4);

        prog.ajouterUnFils(main);
        main.ajouterUnFils(ecrire);
        ecrire.ajouterUnFils(appel2);
        appel1.ajouterUnFils(c5);

        TDS tds = new TDS();
        // Add symbols to TDS
        tds.addSymbole(new Symbole("main", "void", "fonction", null, null));
        tds.addSymbole(new Symbole("f", "int", "fonction", 1, null));
        tds.addSymbole(new Symbole("a", "int", "param","f",0,null));

        //afficher
        TxtAfficheur.afficher(prog);
        GuiAfficheur.afficher(prog);

        //Generer le code assembleur
        Generateur gen = new Generateur(prog, tds);
        String stringReturn= gen.generer().toString();
        try {
            FileWriter fw = new FileWriter("src\\main\\java\\fr\\ul\\miashs\\compil\\generation\\tests\\assembleurGenere\\Programme8Assembleur.txt");
            fw.write(stringReturn);
            fw.close();
            System.out.println("Le texte a été écrit avec succès");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite");

        }

    }
}
