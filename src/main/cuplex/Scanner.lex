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
IDENTIFIANT = {LETTRE}({LETTRE}|{CHIFFRE})*

/*règles*/
%%
"🔡"            {return new Symbol(Sym.READ);}
"appel"         { return new Symbol(Sym.APPEL); }
"🚀"          { return new Symbol(Sym.FONCTION); }
"💫"          { return new Symbol(Sym.RETOUR); }
"☀"          { return new Symbol(Sym.SI); }
"🌧"          { return new Symbol(Sym.SINON); }
"♻"          { return new Symbol(Sym.TANT_QUE); }
"📢"          { return new Symbol(Sym.AFFICHER); }
"🌠".*          { /* ne rien faire */ }
","           { return new Symbol(Sym.VIRGULE); }
"+"           { return new Symbol(Sym.PLUS); }
"-"           { return new Symbol(Sym.MOINS); }
"*"           { return new Symbol(Sym.MULTIPLIER); }
"/"           { return new Symbol(Sym.DIVISER); }
"=="          { return new Symbol(Sym.EGAL); }
"!="          { return new Symbol(Sym.DIFFERENT); }
">"           { return new Symbol(Sym.SUPERIEUR); }
">="          { return new Symbol(Sym.SUPERIEUR_EGAL); }
"<"           { return new Symbol(Sym.INFERIEUR); }
"<="          { return new Symbol(Sym.INFERIEUR_EGAL); }
"="           { return new Symbol(Sym.ASSIGNER); }
";"           { return new Symbol(Sym.POINT_VIRGULE); }
"{"           { return new Symbol(Sym.ACCOLADE_OUVRANTE); }
"}"           { return new Symbol(Sym.ACCOLADE_FERMANTE); }
"("           { return new Symbol(Sym.PARENTHESE_OUVRANTE); }
")"           { return new Symbol(Sym.PARENTHESE_FERMANTE); }
{NOMBRE}      { return new Symbol(Sym.NOMBRE, Integer.parseInt(yytext())); }
{CHAINE}      { return new Symbol(Sym.CHAINE, yytext()); }
{IDENTIFIANT} { return new Symbol(Sym.IDENTIFIANT, yytext()); }
[ \t\n\r]+    { /* ignorer les espaces blancs */ }

.             { erreur(); }