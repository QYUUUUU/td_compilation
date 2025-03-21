package fr.ul.miashs.compil.tds;

import fr.ul.miashs.compil.tds.TDS;

public class GlobalTDS {
    public static TDS tds = new TDS();

    public static TDS getTds() {
        return tds;
    }

    public static void setTds(TDS tds) {
        GlobalTDS.tds = tds;
    }
}

