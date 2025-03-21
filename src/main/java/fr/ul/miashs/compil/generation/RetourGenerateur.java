package fr.ul.miashs.compil.generation;

public class RetourGenerateur extends InstructionGenerateur {

    public StringBuilder genererRetour() {
        StringBuilder stringRes = new StringBuilder();
        stringRes.append("\tMOVE(BP, SP);\n");
        stringRes.append("\tPOP(BP);\n");
        stringRes.append("\tRET;\n");
        return stringRes;
    }
}
