package fr.ul.miashs.compil.generation.tests;

import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Fonction;
import fr.ul.miashs.compil.arbre.Prog;
import fr.ul.miashs.compil.generation.Generateur;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGenerateur {

    @Test
    public void testGenerer() {

        Prog prog = new Prog();
        Fonction principal = new Fonction("main");
        prog.ajouterUnFils(principal);
        Const i = new Const(10);
        Const j = new Const(20);
        Const k = new Const(0);
        Const l = new Const(0);


        TDS tds = new TDS();
        tds.addSymbole(new Symbole("main", "void", "fonction", null, null));
        tds.addSymbole(new Symbole("j", "int", "global", null, null, 20));
        tds.addSymbole(new Symbole("i", "int", "global", null, null, 10));
        tds.addSymbole(new Symbole("k", "int", "global", null, null, null));
        tds.addSymbole(new Symbole("l", "int", "global", null, null, null));


        Generateur generateur = new Generateur(prog,tds);


        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream() {
            public synchronized void write(byte[] b, int off, int len) {
                output.append(new String(b, off, len));
            }
        }));

        generateur.generer();

        String generatedCode = output.toString();
        assertTrue(generatedCode.contains(".include beta.uasm"));
        assertTrue(generatedCode.contains(".include intio.uasm"));
        assertTrue(generatedCode.contains(".options tty"));
        assertTrue(generatedCode.contains("CMOVE(pile,SP)"));
        assertTrue(generatedCode.contains("BR(main)"));
        assertTrue(generatedCode.contains("pile:"));
        assertTrue(generatedCode.contains("data:"));
        assertTrue(generatedCode.contains("main:"));
        assertTrue(generatedCode.contains("PUSH(BP)"));
        assertTrue(generatedCode.contains("MOVE(SP, BP)"));
        //assertTrue(generatedCode.contains("ALLOCATE(0)"));
    }
}