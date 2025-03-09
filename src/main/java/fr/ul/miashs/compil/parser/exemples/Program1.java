package fr.ul.miashs.compil.parser.exemples;

import generated.fr.ul.miashs.expression.Yylex;
import generated.fr.ul.miashs.expression.ParserCup;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.arbre.TxtAfficheur;
import fr.ul.miashs.compil.arbre.GuiAfficheur;

import java.io.FileReader;
import java.io.IOException;

public class Program1 {
    public static void main(String[] args) {
        String filePath = "/home/jeffrey/Documents/cours_S6_L3/Compilation/td_compilationGit/src/main/java/fr/ul/miashs/compil/parser/exemples/Program1.zenith";

        try {
            Yylex scanner = new Yylex(new FileReader(filePath));
            ParserCup parser = new ParserCup(scanner);
            Noeud arbre = (Noeud) parser.parse().value;
            System.out.println("Parsing completed successfully!");

            // Display the tree
            TxtAfficheur.afficher(arbre);
            GuiAfficheur.afficher(arbre);
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Syntax error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Finished!");
    }
}