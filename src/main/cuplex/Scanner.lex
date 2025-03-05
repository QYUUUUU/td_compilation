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

/* Lexical states */
%state COMMENTAIRE

/* Macros */
CHIFFRE = [0-9]
LETTRE = [a-zA-Z_]
NOMBRE = {CHIFFRE}+
CHAINE = \"[^\"]*\" /*string*/

/*règles*/
%%
"🌍"          { return new Symbol(Sym.VARIABLE_PLANETE); }
"🌕"          { return new Symbol(Sym.VARIABLE_LUNE); }
"🌑"          { return new Symbol(Sym.VARIABLE_NOUVELLE_LUNE); }
"🚀"          { return new Symbol(Sym.FONCTION); }
"☀️"          { return new Symbol(Sym.SI); }
"🌧️"          { return new Symbol(Sym.SINON); }
"♻️"          { return new Symbol(Sym.TANT_QUE); }
"🔡"          { return new Symbol(Sym.LIRE); }
"📢"          { return new Symbol(Sym.AFFICHER); }
"🌠"          { yybegin(COMMENTAIRE); }
"+"           { return new Symbol(Sym.PLUS); }
"-"           { return new Symbol(Sym.MOINS); }
"*"           { return new Symbol(Sym.MULTIPLIER); }
"/"           { return new Symbol(Sym.DIVISER); }
"=="          { return new Symbol(Sym.EGAL); }
"!="          { return new Symbol(Sym.DIFFERENT); }
"="           { return new Symbol(Sym.ASSIGNER); }
";"           { return new Symbol(Sym.POINT_VIRGULE); }
","           { return new Symbol(Sym.VIRGULE); }
"{"           { return new Symbol(Sym.ACCOLADE_OUVRANTE); }
"}"           { return new Symbol(Sym.ACCOLADE_FERMANTE); }
"("           { return new Symbol(Sym.PARENTHESE_OUVRANTE); }
")"           { return new Symbol(Sym.PARENTHESE_FERMANTE); }
{NOMBRE}      { return new Symbol(Sym.NOMBRE, Integer.parseInt(yytext())); }
{CHAINE}      { return new Symbol(Sym.CHAINE, yytext()); }
[ \t\n\r]+    { /* ignorer les espaces blancs */ }
<COMMENTAIRE>.* { /* ignorer les commentaires */ }
<COMMENTAIRE>\n { yybegin(YYINITIAL); }
.             { erreur(); }