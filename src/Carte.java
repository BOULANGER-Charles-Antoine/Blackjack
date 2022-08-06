public class Carte {
    // Code pour les 4 couleurs
    public final static int PIQUES = 0,
                            COEURS = 1,
                            CARREAUX = 2,
                            TREFLES = 3;
    // Code pour les honneurs
    public final static int AS = 1,
                            VALET = 11,
                            DAME = 12,
                            ROI = 13;

    private int valeur;
    private int couleur;

    // Constructeur
    public Carte(int valeur, int couleur){
        this.valeur = valeur;
        this.couleur = couleur;
    }

    // Accesseurs
    public int getCouleur(){
        return couleur;
    }

    public int getValeur(){
        return valeur;
    }

    // Accesseur convertissant la couleur de la carte en chaine de caractere
    public String getCouleurCommeChaine(){
        switch(couleur){
            case 0:
                return "Piques";
            case 1:
                return "Coeurs";
            case 2:
                return "Carreaux";
            case 3:
                return "Trefles";
            default:
                return "??";
        }
    }

    // Accesseur convertissant la valeur de la carte en chaine de caractere
    public String getValeurCommeChaine(){
        switch(valeur){
            case 1:
                return"AS";
            case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                return "" + valeur;
            case 11:
                return "Valet";
            case 12:
                return "Dame";
            case 13:
                return "Roi";
            default:
                return "??";
        }
    }

    // Affichage de la carte
    public String toString(){ //modifie l'affichage, override=passer outre, affiche la carte
        return getValeurCommeChaine() + " de " + getCouleurCommeChaine();
    }
}
