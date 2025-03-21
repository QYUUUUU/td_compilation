package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Si;

/**
 * SiGenerateur
 * Générateur de code pour les instructions Si
 */
public class SiGenerateur extends InstructionGenerateur {

    /**
     * Fonction genererSi
     * Génère le code pour une instruction Si
     *
     * @param noeudSi Noeud Si à générer
     * @return Code généré
     */
    public StringBuilder genererSi(Si noeudSi) {
        StringBuilder stringRes = new StringBuilder();
        // Générer une étiquette unique pour la sortie du bloc sinon
        String labelSi = "SI_";
        String labelSinon = "SINON_";
        String labelFin = "FINSI_";
        String labelAlors = "ALORS_";
        ExpressionGenerateur expressionGen = new ExpressionGenerateur();
        //bloc SI
        stringRes.append(labelSi + expressionGen.getId() + ":\n");
        stringRes.append(expressionGen.genererExpression(noeudSi.getCondition(), expressionGen.getId(), true));// va envoyer vers le label ALORS ou SINON et démarrer l'algo+":\n");
        // Générer le bloc "alors"
        stringRes.append(labelAlors + expressionGen.getId() + " :\n");
        stringRes.append(expressionGen.genererBloc(noeudSi.getBlocAlors()));
        stringRes.append("JMP " + labelSinon + expressionGen.getId() + "\n"); //o jump vers le sinon, même si il est vide
        // Générer le bloc "sinon"
        stringRes.append(labelSinon + expressionGen.getId() + " :\n");
        if (noeudSi.getBlocSinon() != null) {
            stringRes.append(genererBloc(noeudSi.getBlocSinon()));
        }
        stringRes.append("JMP " + labelFin + expressionGen.getId() + "\n"); // on jump vers la fin
        // Fin du bloc Si
        stringRes.append(labelFin + expressionGen.getId() + " :\n");
        return stringRes;
    }


}

