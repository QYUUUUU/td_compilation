package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;


public class AffectationG {
    private TDS tds;

    public AffectationG(TDS tds) {
        this.tds = tds;
    }

    public StringBuilder generer_affectation(fr.ul.miashs.compil.arbre.Affectation affectation) {
        StringBuilder affectation_string = new StringBuilder();

        Noeud filsDroit = affectation.getFilsDroit();

        if (filsDroit instanceof Const) {
            Const constante = (Const) filsDroit;
            affectation_string.append("\tLDR(").append(constante.getValeur()).append(", Rc);\n");
        } else if (filsDroit instanceof Idf) {
            Idf idf = (Idf) filsDroit;
            Symbole symbole = tds.getSymbole(idf.getLabel());
            affectation_string.append("\tLDR(").append(symbole.getNom()).append(", R0);\n");
        } else {
            ExpressionG expressionGen = new ExpressionG();
            affectation_string.append(expressionGen.generer_expression(filsDroit, tds));
        }

        Noeud filsGauche = affectation.getFilsGauche();
        Symbole symboleGauche = tds.getSymbole(filsGauche.getLabel());
        affectation_string.append("\tSTR(").append(symboleGauche.getNom()).append(", ACC);\n");

        return affectation_string;
    }
}