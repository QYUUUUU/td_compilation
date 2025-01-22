package fr.ul.miashs.compil.tds;


import java.util.ArrayList;
import java.util.List;

public class TDS {
    private List<Symbole> symboles;

    // Constructor
    public TDS() {
        this.symboles = new ArrayList<>();
    }

    // Add a symbol to the table
    public void addSymbole(Symbole symbole) {
        this.symboles.add(symbole);
    }

    // Get all symbols (optional if you need to inspect the list)
    public List<Symbole> getAllSymboles() {
        return new ArrayList<>(this.symboles);
    }

    // Get all functions
    public List<Symbole> getAllFunctions() {
        List<Symbole> functions = new ArrayList<>();
        for (Symbole s : symboles) {
            if ("fonction".equals(s.getCategorie())) {
                functions.add(s);
            }
        }
        return functions;
    }

    // Get all global variables
    public List<Symbole> getAllGlobalVariables() {
        List<Symbole> globals = new ArrayList<>();
        for (Symbole s : symboles) {
            if ("global".equals(s.getCategorie())) {
                globals.add(s);
            }
        }
        return globals;
    }

    // Get local variables of a specific function
    public List<Symbole> getLocalVariablesOfFunction(String functionName) {
        List<Symbole> locals = new ArrayList<>();
        for (Symbole s : symboles) {
            if (functionName.equals(s.getScope()) && "local".equals(s.getCategorie())) {
                locals.add(s);
            }
        }
        return locals;
    }

    // Helper to display symbols (for debugging purposes)
    public void displaySymbols() {
        for (Symbole s : symboles) {
            System.out.println(s);
        }
    }
}