package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.TDS;

import java.util.ArrayList;
import java.util.List;

public class InstructionG { //TODO: voir si on peut la mettre en classe abstraite
    public StringBuilder generer_instruction(Noeud instruct) {
        StringBuilder res = new StringBuilder();
        Noeud fils = instruct.getFils().get(0);
        if (fils instanceof Si) { // TODO : essayer de trouver une autre structure de données, duplication de code
            Si filsSi = (Si) fils;
            SiG newSi = new SiG();
            res = res.append(newSi.generer_si(filsSi)); // on ajoute au programme, le code du noeud relié à l'instruction
        } else if (fils instanceof TantQue) {
            TantQue filsTQ = (TantQue) fils;
            TantQueG newTQ = new TantQueG();
            res = res.append(newTQ.genererTantQue(filsTQ));
        } else if (fils instanceof Affectation) {
            Affectation filsAff = (Affectation) fils;
            AffectationG newAff = new AffectationG();
            res = res.append(newAff.generer_affectation(filsAff));
        } else if (fils instanceof Retour) {
            Retour filsRet = (Retour) fils;
            RetourG newRetour = new RetourG();
            res = res.append(newRetour.generer_retour());
        } else if (fils instanceof Ecrire) {
            Ecrire filsEcr = (Ecrire) fils;
            EcrireG newEcr = new EcrireG(); //TODO: faire la classe Ecrire
            res = res.append(newEcr.generer_ecrire(filsEcr));

        }
        return (res);

    }

    public StringBuilder generer_bloc(Bloc bloc) {
        StringBuilder bloc_string = new StringBuilder();
        List<Noeud> instructions = bloc.getFils();

        for (Noeud instruction : instructions) {
            InstructionG instructionGen = new InstructionG();
            bloc_string.append(instructionGen.generer_instruction(instruction));
        }

        return bloc_string;
    }
}
