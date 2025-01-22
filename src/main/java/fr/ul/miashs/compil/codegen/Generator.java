package fr.ul.miashs.compil.codegen;

import fr.ul.miashs.compil.arbre.*;
import fr.ul.miashs.compil.tds.*;

import java.util.ArrayList;
import java.util.List;

public class Generator {


    public static void generate(TDS tds,Prog prog){
        //DO Some Stuff
        String head_string = ".include beta.uasm\n" +
                ".include intio.uasm\n" +
                ".options tty\n" +
                "\tCMOVE(pile,SP)\n" +
                "\tBR(debut)\n";

        String data_init_text = "data:\n";
        StringBuilder data = new StringBuilder();
        data.append(data_init_text);

        String debut = "debut:\n";

        String pile = "pile:\n";

        List<String> functions_list = new ArrayList<>(); //Liste des fonctions appelées dans le code


        List<Symbole> global_variables_list = tds.getAllGlobalVariables();

        for (Symbole variable : global_variables_list) {

            String nom = variable.getNom();
            String type = variable.getType();
            String valeur = variable.getValeur();

            data.append(nom)
                    .append(":\tLONG(")
                    .append(valeur != null ? valeur : 0)
                    .append(")\t| ")
                    .append(type)
                    .append(" ")
                    .append(nom)
                    .append(";\n");
        }

        StringBuilder final_string = new StringBuilder(head_string + data + debut);

        for (String function : functions_list) {
            final_string.append(function);
        }

        final_string.append(pile);

        System.out.println(final_string);
    }

    public static void main(String[] args){
        Prog prog = new Prog();
        Fonction principal = new Fonction("main");
        //on relie les noeuds
        prog.ajouterUnFils(principal);
        Const i = new Const(10);
        Const j = new Const(20);
        Const k = new Const(0);
        Const l = new Const(0);

        TDS tds = new TDS();

        tds.addSymbole(new Symbole( "main","void","fonction",null, null));
        tds.addSymbole(new Symbole( "j","int","global",null,null, 20));
        tds.addSymbole(new Symbole("i", "int", "global", null, null, 10));
        tds.addSymbole(new Symbole( "k","int","global",null,null, null));
        tds.addSymbole(new Symbole( "l","int","global",null,null, null));

        Generator generator = new Generator();
        generator.generate(tds, prog);
    }
}
