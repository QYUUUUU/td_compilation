package generated.fr.ul.miashs.expression;
import java_cup.runtime.Symbol;

%%
/*option */
%public
%cupsym Sym
%cup

%{
    void erreur(){
        System.out.println("Caractère inattendu :" + yytext());
    }
%}

/* Macros */
%{
    CHIFFRE = [0-9]
    LETTRE = [a-zA-Z_]
    NOMBRE = {CHIFFRE}+
    CHAINE = \"[^\"]*\" /*string*/
%}

/*règles*/
%%
"🌍"          { return new Symbol(sym.VARIABLE_PLANETE); }
"🌕"          { return new Symbol(sym.VARIABLE_LUNE); }
"🌑"          { return new Symbol(sym.VARIABLE_NOUVELLE_LUNE); }
"🚀"          { return new Symbol(sym.FONCTION); }
"☀️"          { return new Symbol(sym.SI); }
"🌧️"          { return new Symbol(sym.SINON); }
"♻️"          { return new Symbol(sym.TANT_QUE); }
"🔡"          { return new Symbol(sym.LIRE); }
"📢"          { return new Symbol(sym.AFFICHER); }
"🌠"          { yybegin(COMMENTAIRE); }
"+"           { return new Symbol(sym.PLUS); }
"-"           { return new Symbol(sym.MOINS); }
"*"           { return new Symbol(sym.MULTIPLIER); }
"/"           { return new Symbol(sym.DIVISER); }
"=="          { return new Symbol(sym.EGAL); }
"!="          { return new Symbol(sym.DIFFERENT); }
"="           { return new Symbol(sym.ASSIGNER); }
";"           { return new Symbol(sym.POINT_VIRGULE); }
","           { return new Symbol(sym.VIRGULE); }
"{"           { return new Symbol(sym.ACCOLADE_OUVRANTE); }
"}"           { return new Symbol(sym.ACCOLADE_FERMANTE); }
"("           { return new Symbol(sym.PARENTHESE_OUVRANTE); }
")"           { return new Symbol(sym.PARENTHESE_FERMANTE); }
{NOMBRE}      { return new Symbol(sym.NOMBRE, Integer.parseInt(yytext())); }
{CHAINE}      { return new Symbol(sym.CHAINE, yytext()); }
[ \t\n\r]+    { /* ignorer les espaces blancs */ }
<COMMENTAIRE>.* { /* ignorer les commentaires */ }
<COMMENTAIRE>\n { yybegin(YYINITIAL); }
.             { erreur(); }