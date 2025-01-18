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
import fr.ul.miashs.compil.tds.Symbole;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemple #6
 */

public class program_6 {
    public static void main(String[] args) {
        //on crée les noeuds
        Prog prog = new Prog();
        Fonction main = new Fonction("main");
        Si si = new Si();
        Superieur sup = new Superieur();
        Bloc bl1 = new Bloc();
        Bloc bl2 = new Bloc();
        Idf a = new Idf("a");
        Idf b = new Idf("b");
        Idf x = new Idf("x");
        Const c1 = new Const(1000);
        Const c2 = new Const(2000);
        Affectation aff1 = new Affectation();
        Affectation aff2 = new Affectation();

        //on relie les noeuds
        prog.ajouterUnFils(main);
        main.ajouterUnFils(si);
        si.setCondition(sup);
        si.setBlocAlors(bl1);
        si.setBlocSinon(bl2);
        sup.setFilsGauche(a);
        sup.setFilsDroit(b);
        bl1.ajouterUnFils(aff1);
        bl2.ajouterUnFils(aff2);
        aff1.setFilsGauche(x);
        aff1.setFilsDroit(c1);
        aff2.setFilsGauche(x);
        aff2.setFilsDroit(c2);

        List<Symbole> tableDesSymboles = new ArrayList<>();
        tableDesSymboles.add(new Symbole("main", "void", "fonction", null, null));
        tableDesSymboles.add(new Symbole("a", "int", "global", null, null, 1));
        tableDesSymboles.add(new Symbole("b", "int", "global", null, null, 2));
        tableDesSymboles.add(new Symbole("x", "int", "global", null, null, null));

        TxtAfficheur.afficher(prog);
        GuiAfficheur.afficher(prog);
    }
}
