package fr.ul.miashs.compil.generation;

/**
 * RetourGénérateur
 * Générateur de code pour les instructions de retour
 */
public class RetourGenerateur extends InstructionGenerateur {

    /**
     * Fonction genererRetour
     * Génère le code pour une instruction de retour
     *
     * @return le code généré
     */
    public StringBuilder genererRetour() {
        StringBuilder stringRes = new StringBuilder();
        stringRes.append("\tMOVE(BP, SP)\n");
        stringRes.append("\tPOP(BP)\n");
        stringRes.append("\tRET\n");
        return stringRes;
    }
}
