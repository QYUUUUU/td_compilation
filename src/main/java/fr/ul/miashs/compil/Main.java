package fr.ul.miashs.compil;

import fr.ul.miashs.compil.yylex.Yylex;

import java.io.FileReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Usage : wc Parser");
            System.exit(1);
        }
        try{
            Reader r = new FileReader(args[0]);
            Yylex scanner = new Yylex(r);
            int n = scanner.yylex();
            System.out.println(n);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
