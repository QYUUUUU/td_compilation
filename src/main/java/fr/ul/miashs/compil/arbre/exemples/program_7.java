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
 * Exemple #7
 */
public class program_7 {
    public static void main(String[] args) {
        //on crée les noeuds
        Prog prog = new Prog();
        Fonction main = new Fonction("main");
        Affectation aff1 = new Affectation();
        Affectation aff2 = new Affectation();
        TantQue tq = new TantQue();
        Inferieur inf = new Inferieur();
        Bloc bl1 = new Bloc();
        Ecrire ecrire = new Ecrire();
        Plus plus = new Plus();
        Idf i = new Idf("i");
        Const c1 = new Const(0);
        Const c2 = new Const(6);
        Const c3 = new Const(21);

        //on relie les noeuds
        prog.ajouterUnFils(main);
        main.ajouterUnFils(aff1);
        aff1.setFilsGauche(i);
        aff1.setFilsDroit(c1);
        main.ajouterUnFils(tq);
        tq.setCondition(inf);
        inf.setFilsGauche(i);
        inf.setFilsDroit(c2);
        tq.setBloc(bl1);
        bl1.ajouterUnFils(ecrire);
        ecrire.setLeFils(i);
        bl1.ajouterUnFils(aff2);
        aff2.setFilsGauche(i);
        aff2.setFilsDroit(plus);
        plus.setFilsGauche(i);
        plus.setFilsDroit(c3);

        List<Symbole> tableDesSymboles = new ArrayList<>();
        tableDesSymboles.add(new Symbole("main", "void", "fonction", null, null));
        tableDesSymboles.add(new Symbole("i", "int", "global", null, null, null));

        //afficher
        TxtAfficheur.afficher(prog);
        GuiAfficheur.afficher(prog);

    }
}
