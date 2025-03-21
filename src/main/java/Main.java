import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.generation.Generateur;
import fr.ul.miashs.compil.tds.TDS;
import generated.expression.ParserCup;
import generated.expression.Yylex;
import java_cup.runtime.Symbol;

import fr.ul.miashs.compil.tds.GlobalTDS;


import static fr.ul.miashs.compil.generation.Generateur.tds;

public class Main{
    public static void main(String[] args) {
        System.out.println("Début de l'exécution");

        File resultsDir = new File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdir();
        }

        for (int i = 1; i <= 9; i++) {
            String filePath = "src/main/java/fr/ul/miashs/compil/parser/exemples/Program" + i + ".zenith";
            File file = new File(filePath);

            System.out.println("\n - Traitement de : " + filePath);

            try {
                //Reset TDS
                GlobalTDS.setTds();

                // Générer l'arbre
                Yylex scanner = new Yylex(new FileReader(file));
                ParserCup parser = new ParserCup(scanner);
                Symbol parseResult = parser.parse();
                Noeud prog = (Noeud) parseResult.value;

                // Récupérer la table des symboles
                TDS tds = GlobalTDS.getTds();

                System.out.println("- Arbre généré avec succès !");

                // Générer le code assembleur
                Generateur gen = new Generateur(prog, tds);
                String stringReturn = gen.generer().toString();
                String outputFilePath = "results/Code_Program" + i + ".txt";
                FileWriter fw = new FileWriter(outputFilePath);
                fw.write(stringReturn);
                fw.close();

                System.out.println("Code assembleur généré : " + outputFilePath);
            } catch (Exception e) {
                System.err.println("Échec du traitement de " + filePath);
                e.printStackTrace();
            }
        }

        System.out.println("Les fichiers générés sont disponibles dans : " + resultsDir.getAbsolutePath());
    }
}
