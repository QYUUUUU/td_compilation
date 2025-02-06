package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;
import fr.ul.miashs.compil.tds.TDS;

public class GenererAffectation extends Generateur {
    private TDS tds;

    public GenererAffectation(TDS tds) {
        super(tds, null);
        this.tds = tds;
    }

    public StringBuilder generer_affectation(Affectation affectation) {
        StringBuilder affectation_string = new StringBuilder();

        Noeud filsDroit = affectation.getFilsDroit();

        if (filsDroit instanceof Const) {
            Const constante = (Const) filsDroit;
            affectation_string.append("\tLOADI(").append(constante.getValeur()).append(", ACC);\n");
        } else if (filsDroit instanceof Idf) {
            Idf idf = (Idf) filsDroit;
            Symbole symbole = tds.getSymbole(idf.getLabel());
            affectation_string.append("\tLOAD(").append(symbole.getNom()).append(", ACC);\n");
        } else {
            affectation_string.append(generer_expression(filsDroit));
        }

        Noeud filsGauche = affectation.getFilsGauche();
        Symbole symboleGauche = tds.getSymbole(filsGauche.getLabel());
        affectation_string.append("\tSTORE(").append(symboleGauche.getNom()).append(", ACC);\n");

        return affectation_string;
    }
}