package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.arbre.Idf;
import fr.ul.miashs.compil.tds.Symbole;

/**
 * AffectationGenerateur
 * Génération des affectations
 */
public class AffectationGenerateur extends InstructionGenerateur {

    /**
    * Fonction genererAffectation
    * @return StringBuilder
    * */
    public StringBuilder genererAffectation(Affectation affectation) {
        StringBuilder stringRes = new StringBuilder();
        ExpressionGenerateur expression = new ExpressionGenerateur();
        // On génère l'expression de la partie droite
        stringRes.append(expression.genererExpression(affectation.getFilsDroit(), expression.getId(), false));
        // On récupère le résultat de l'expression pour le stocker dans la variable de la partie gauche
        if (Generateur.tds.getSymbole((String) ((Idf) affectation.getFilsGauche()).getValeur()) != null) {
            Symbole symboleGauche = Generateur.tds.getSymbole((String) ((Idf) affectation.getFilsGauche()).getValeur());
            // Generateur.tds.setSymboles(symboleGauche,resultFilsDroit); // on doit changer la tds au cas ou on réutilise la variable plus tard
        }
        return stringRes;
    }
}