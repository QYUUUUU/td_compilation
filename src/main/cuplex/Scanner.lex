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

/*macros*/
SEP = [ \t\n\r]+
NUM = [0-9]+
ADD = "+"
MULT = "*"
PO = "("

/*règles*/
"+" { return new Symbol(Sym.ADD); }
"*" { return new Symbol(Sym.MULT); }
"(" { return new Symbol(Sym.PO); }
