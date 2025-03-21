import java.io.FileReader;
import java.io.FileWriter;

import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.generation.Generateur;
import generated.expression.ParserCup;
import generated.expression.Yylex;
import java_cup.runtime.Symbol;

import static fr.ul.miashs.compil.generation.Generateur.tds;

public class Main{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Il faut un <nom de fichier>");
            System.exit(1);
        }
        try {
            //Generer l'arbre
            Yylex scanner = new Yylex(new FileReader(args[0]));
            ParserCup parser = new ParserCup(scanner);
            Symbol parseResult = parser.parse();
            Noeud prog = (Noeud) parseResult.value;
            System.out.println("Arbre généré avec succès !");
            //Generer le code assembleur
            Generateur gen = new Generateur(prog, tds);
            String stringReturn= gen.generer().toString();
            FileWriter fw = new FileWriter("VotreCode.txt");
            fw.write(stringReturn);
            fw.close();
            System.out.println("Code assembleur généré avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur de syntaxe");
        }
        System.out.println("Terminé !");
    }
}
