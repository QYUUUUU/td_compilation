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
import fr.ul.miashs.compil.tds.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Exemple #1 programme minimal
 */
public class Program_1 {
    public static void main(String[] args) {
        //on crée les noeuds
        Prog prog = new Prog();
        Fonction principal = new Fonction("main");
        //on relie les noeuds
        prog.ajouterUnFils(principal);

        TDS tds = new TDS();
        // Add symbols to TDS
        tds.addSymbole(new Symbole( "main","void","fonction",null, null));

        //afficher
        TxtAfficheur.afficher(prog);
        GuiAfficheur.afficher(prog);

        //Generer le code assembleur
        Generateur gen = new Generateur(prog, tds);
        String stringReturn= gen.generer().toString();
        try {
            FileWriter fw = new FileWriter("src\\main\\java\\fr\\ul\\miashs\\compil\\generation\\tests\\assembleurGenere\\Programme1Assembleur.txt");
            fw.write(stringReturn);
            fw.close();
            System.out.println("Le texte a été écrit avec succès");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite");

        }
    }
}
