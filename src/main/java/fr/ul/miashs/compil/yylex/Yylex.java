package fr.ul.miashs.compil.yylex;

import java.io.InputStream;
import java.io.Reader;

public interface Yylex {
    int yyline();
    int yycolumn();
    String yytext();
    int yylength();
    int yylex();

    // Factory methods to create instances
    static Yylex create(Reader in) {
        return new YylexImpl(in);
    }

    static Yylex create(InputStream in) {
        return new YylexImpl(in);
    }
}