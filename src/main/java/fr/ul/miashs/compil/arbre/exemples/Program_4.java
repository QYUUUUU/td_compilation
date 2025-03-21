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
 * Exemple #4 Ecriture
 */
public class Program_4 {
    public static void main(String[] args) {
        //on crée les noeuds
        Prog prog = new Prog();
        Fonction principal = new Fonction("main");
        Affectation aff = new Affectation();
        Idf x = new Idf("x");
        Plus plus = new Plus();
        Multiplication mul = new Multiplication();
        Lire lire_first = new Lire();
        Const c2 = new Const(2);
        Division div = new Division();
        Moins moins = new Moins();
        Lire lire_second = new Lire();
        Const c5 = new Const(5);
        Const c3 = new Const(3);
        
        Ecrire ecrire = new Ecrire();
        //on relie les noeuds
        prog.ajouterUnFils(principal);
        principal.ajouterUnFils(aff);
        principal.ajouterUnFils(ecrire);
        ecrire.ajouterUnFils(x);
        aff.setFilsGauche(x);
        aff.setFilsDroit(plus);
        plus.setFilsGauche(mul);
        plus.setFilsDroit(div);
        mul.setFilsGauche(lire_first);
        mul.setFilsDroit(c2);
        div.setFilsGauche(moins);
        div.setFilsDroit(c3);
        moins.setFilsGauche(lire_second);
        moins.setFilsDroit(c5);


        TDS tds = new TDS();
        // Add symbols to TDS
        tds.addSymbole(new Symbole( "main","void","fonction",null, null));
        tds.addSymbole(new Symbole("res", "int", "global", null, null, null));
        //afficher
        TxtAfficheur.afficher(prog);
        GuiAfficheur.afficher(prog);

        //Generer le code assembleur
        Generateur gen = new Generateur(prog, tds);
        String stringReturn= gen.generer().toString();
        try {
            FileWriter fw = new FileWriter("src\\main\\java\\fr\\ul\\miashs\\compil\\generation\\tests\\assembleurGenere\\Programme4Assembleur.txt");
            fw.write(stringReturn);
            fw.close();
            System.out.println("Le texte a été écrit avec succès");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite");

        }
    }
}
