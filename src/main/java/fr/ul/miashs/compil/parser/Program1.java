package fr.ul.miashs.compil.parser;

import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.TxtAfficheur;
import fr.ul.miashs.compil.arbre.GuiAfficheur;
import generated.fr.ul.miashs.expression.ParserCup;
import generated.fr.ul.miashs.expression.Sym;
import generated.fr.ul.miashs.expression.Yylex;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java_cup.runtime.Symbol;

public class Program1 {
    public static void main(String[] args) {
        String filePath = "src/main/java/fr/ul/miashs/compil/parser/exemples/Program1.zenith";
        File file = new File(filePath);
        System.out.println("Absolute path: " + file.getAbsolutePath());

        try {
            Yylex scanner = new Yylex(new FileReader(file.getAbsolutePath()));
            Symbol sym;

            System.out.println("--- Token Debug Start ---");
            while ((sym = scanner.next_token()).sym != Sym.EOF) {
                System.out.println("Token: " + sym.sym + " (" + sym.value + ")");
            }
            System.out.println("--- Token Debug End ---");

            // Reset scanner for parsing
            scanner = new Yylex(new FileReader(file.getAbsolutePath()));
            ParserCup parser = new ParserCup(scanner);
            Noeud arbre = (Noeud) parser.parse().value;
            System.out.println("Parsing completed successfully!");

            // Display the tree
            //TxtAfficheur.afficher(arbre);
            //GuiAfficheur.afficher(arbre);
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Syntax error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Finished!");
    }
}