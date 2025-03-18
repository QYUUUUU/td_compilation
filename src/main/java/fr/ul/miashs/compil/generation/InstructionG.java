package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;

import java.util.List;

public class InstructionG { //TODO: voir si on peut la mettre en classe abstraite
    public StringBuilder generer_instruction(Noeud instruct) {
        StringBuilder res = new StringBuilder();
        //Noeud fils = instruct.getFils().get(0);
        if (instruct instanceof Si) { // TODO : essayer de trouver une autre structure de données, duplication de code
            Si filsSi = (Si) instruct;
            SiG newSi = new SiG();
            res.append(newSi.generer_si(filsSi)); // on ajoute au programme, le code du noeud relié à l'instruction
        } else if (instruct instanceof TantQue) {
            TantQue filsTQ = (TantQue) instruct;
            TantQueG newTQ = new TantQueG();
            res.append(newTQ.genererTantQue(filsTQ));
        } else if (instruct instanceof Affectation) {
            Affectation filsAff = (Affectation) instruct;
            AffectationG newAff = new AffectationG();
            res.append(newAff.generer_affectation(filsAff));
        } else if (instruct instanceof Retour) {
            Retour filsRet = (Retour) instruct;
            RetourG newRetour = new RetourG();
            res.append(newRetour.generer_retour());
        } else if (instruct instanceof Ecrire) {
            Ecrire filsEcr = (Ecrire) instruct;
            EcrireG newEcr = new EcrireG(); //TODO: faire la classe Ecrire
            res.append(newEcr.generer_ecrire(filsEcr));
        } else {
            System.out.println("Instruction non reconnue");
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
