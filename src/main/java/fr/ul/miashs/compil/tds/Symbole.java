package fr.ul.miashs.compil.tds;

public class Symbole {
    private String nom;          // Nom de l'identifiant
    private String type;         // Type (int, void, etc.)
    private String categorie;    // Catégorie (fonction, global, local, paramètre, etc.)
    private String scope;        // Portée (nom de la fonction ou global)
    private Integer rang;        // Rang (pour les paramètres ou variables locales)
    private Integer valeur;      // Valeur initiale (pour les constantes ou variables globales)
    private Integer nbParam;     // Nombre de paramètres (pour les fonctions)
    private Integer nbVar;       // Nombre de variables locales (pour les fonctions)

    // Constructeur pour les variables ou paramètres
    public Symbole(String nom, String type, String categorie, String scope, Integer rang, Integer valeur) {
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.scope = scope;
        this.rang = rang;
        this.valeur = valeur;
        this.nbParam = null; // Pas applicable pour une variable ou un paramètre
        this.nbVar = null;   // Pas applicable pour une variable ou un paramètre
    }

    // Constructeur pour les fonctions
    public Symbole(String nom, String type, String categorie, Integer nbParam, Integer nbVar) {
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.scope = null;   // Pas applicable pour une fonction
        this.rang = null;    // Pas applicable pour une fonction
        this.valeur = null;  // Pas applicable pour une fonction
        this.nbParam = nbParam;
        this.nbVar = nbVar;
    }

    // Getter et Setter (générés automatiquement ou manuellement selon ton IDE)
    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getScope() {
        return scope;
    }

    public Integer getRang() {
        return rang;
    }

    public Integer getValeur() {
        return valeur;
    }

    public Integer getNbParam() {
        return nbParam;
    }

    public Integer getNbVar() {
        return nbVar;
    }

    @Override
    public String toString() {
        return "Symbole{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", categorie='" + categorie + '\'' +
                ", scope='" + scope + '\'' +
                ", rang=" + rang +
                ", valeur=" + valeur +
                ", nbParam=" + nbParam +
                ", nbVar=" + nbVar +
                '}';
    }
}
