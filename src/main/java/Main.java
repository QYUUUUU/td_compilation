import generated.fr.ul.miashs.expression.Yylex;
import generated.fr.ul.miashs.expression.ParserCup;

import java.io.FileReader;

public class Main{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: expr <nom de fichier>");
            System.exit(1);
        }
        try {
            Yylex scanner = new Yylex(new FileReader(args[0]));
            ParserCup parser = new ParserCup(scanner);
            parser.parse();
            System.out.println("OK");
        } catch (Exception e) {
            System.err.println("Erreur de syntaxe");
        }
        System.out.println("Terminé !");
    }
}