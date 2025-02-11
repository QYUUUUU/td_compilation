package fr.ul.miashs.compil.yylex;

import java.io.InputStream;
import java.io.Reader;

public class YylexImpl implements Yylex {
    private Reader reader;
    private InputStream inputStream;

    public YylexImpl(Reader in) {
        this.reader = in;
    }

    public YylexImpl(InputStream in) {
        this.inputStream = in;
    }

    @Override
    public int yylex() {
        // Implémentation de la méthode principale d'analyse lexicale
        return 0;
    }

    @Override
    public String yytext() {
        // Retourne le texte du token courant
        return "";
    }

    @Override
    public int yylength() {
        // Retourne la longueur du token courant
        return 0;
    }

    @Override
    public int yyline() {
        // Retourne le numéro de la ligne courante
        return 0;
    }

    @Override
    public int yycolumn() {
        // Retourne le numéro de la colonne courante
        return 0;
    }
}