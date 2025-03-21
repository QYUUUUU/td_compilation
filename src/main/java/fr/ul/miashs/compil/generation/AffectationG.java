package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.arbre.Const;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.arbre.Noeud;
import fr.ul.miashs.compil.tds.Symbole;

import java.util.List;


public class AffectationG extends InstructionG{

    public StringBuilder generer_affectation(Affectation affectation) {
        StringBuilder affectation_string = new StringBuilder();

        ExpressionG expression=new ExpressionG();
        System.out.println("c'est la bonne"+affectation.getFilsDroit().getCat());
        affectation_string.append(expression.generer_expression(affectation.getFilsDroit(),expression.getId(),false));

        System.out.println((String)((Idf)affectation.getFilsGauche()).getValeur());
        if(Generateur.tds.getSymbole((String)((Idf)affectation.getFilsGauche()).getValeur())!=null){
            Symbole symboleGauche=Generateur.tds.getSymbole((String)((Idf)affectation.getFilsGauche()).getValeur());
           // Generateur.tds.setSymboles(symboleGauche,resultFilsDroit); // on doit changer la tds au cas ou on réutilise la variable plus tard

        }


        return affectation_string;
    }
}