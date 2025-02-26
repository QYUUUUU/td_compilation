package fr.ul.miashs.compil.analyse;

public class ParserCup {
    private Yylex scanner;

    public ParserCup(Yylex scanner){
        this.scanner = scanner;
    }

    public void parse(){
        System.out.println("OK");
    }
}

