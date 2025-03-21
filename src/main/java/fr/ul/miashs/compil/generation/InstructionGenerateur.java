package fr.ul.miashs.compil.generation;

import fr.ul.miashs.compil.arbre.*;

import java.util.List;

public class InstructionGenerateur {
    public StringBuilder genererInstruction(Noeud instruct) {
        StringBuilder res = new StringBuilder();
        if (instruct instanceof Si) {
            Si filsSi = (Si) instruct;
            SiGenerateur newSi = new SiGenerateur();
            res.append(newSi.genererSi(filsSi)); // on ajoute au programme, le code du noeud relié à l'instruction
        } else if (instruct instanceof TantQue) {
            TantQue filsTQ = (TantQue) instruct;
            TantQueGenerateur newTQ = new TantQueGenerateur();
            res.append(newTQ.genererTantQue(filsTQ));
        } else if (instruct instanceof Affectation) {
            Affectation filsAff = (Affectation) instruct;
            AffectationGenerateur newAff = new AffectationGenerateur();
            res.append(newAff.genererAffectation(filsAff));
        } else if (instruct instanceof Retour) {
            Retour filsRet = (Retour) instruct;
            RetourGenerateur newRetour = new RetourGenerateur();
            res.append(newRetour.genererRetour());
        } else if (instruct instanceof Ecrire) {
            Ecrire filsEcr = (Ecrire) instruct;
            EcrireGenerateur newEcr = new EcrireGenerateur();
            res.append(newEcr.genererEcrire(filsEcr));
        } else {
            System.out.println("Instruction non reconnue");
        }
        return (res);
    }

    public StringBuilder genererBloc(Bloc bloc) {
        StringBuilder stringRes = new StringBuilder();
        List<Noeud> instructions = bloc.getFils();
        for (Noeud instruction : instructions) {
            InstructionGenerateur instructionGen = new InstructionGenerateur();
            stringRes.append(instructionGen.genererInstruction(instruction));
        }
        return stringRes;
    }
}
