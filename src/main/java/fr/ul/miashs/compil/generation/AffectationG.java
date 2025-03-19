package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;



public class AffectationG extends InstructionG{

    public StringBuilder generer_affectation(Affectation affectation) {
        StringBuilder affectation_string = new StringBuilder();

        Noeud filsDroit = affectation.getFilsDroit();

        if (filsDroit instanceof Const) {
            Const constante = (Const) filsDroit;
            affectation_string.append("\tLDR(").append(constante.getValeur()).append(", Rc);\n");
        } else if (filsDroit instanceof Idf) {
            Idf idf = (Idf) filsDroit;
            Symbole symbole = Generateur.tds.getSymbole(idf.getLabel());
            affectation_string.append("\tLDR(").append(symbole.getNom()).append(", R0);\n");
        } else {
            ExpressionG expressionGen = new ExpressionG();
            affectation_string.append(expressionGen.generer_expression(filsDroit,expressionGen.getId()));
        }

        //Noeud filsGauche = affectation.getFilsGauche();
        //Symbole symboleGauche = Generateur.tds.getSymbole(filsGauche.getLabel());
        //affectation_string.append("\tSTR(").append(symboleGauche.getNom()).append(", ACC);\n");

        return affectation_string;
    }
}