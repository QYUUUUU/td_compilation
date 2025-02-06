package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.Affectation;
import fr.ul.miashs.compil.tds.TDS;

public class GenererRetour extends Generateur {
    public GenererRetour(TDS tds, Affectation arbre) {
        super(tds, arbre);
    }

    private StringBuilder generer_retour() {
        StringBuilder retour_string = new StringBuilder();
        retour_string.append("\tMOVE(BP, SP);\n");
        retour_string.append("\tPOP(BP);\n");
        retour_string.append("\tRET;\n");
        return retour_string;
    }
}
