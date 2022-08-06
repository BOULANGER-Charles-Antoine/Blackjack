import java.util.Random;

public class Deck {
    public final static int NBCARTESDECK = 52;
    public final static int NBCOULEURSCARTES = 4;
    public final static int NBCARTESPARCOULEURS = 14;

    private Carte[] deck;
    private int cartesUtilisees;

    // Constructeur, initialise le deck de 52 cartes
    public Deck() {
        cartesUtilisees = 0;
        deck = new Carte[NBCARTESDECK];
        int nbCarte = 0;

        for(int i = 1; i < NBCARTESPARCOULEURS; i++) { //pour les couleurs
            for(int j = 0; j < NBCOULEURSCARTES; j++) {
                Carte carte = new Carte(i, j);
                deck[nbCarte] = carte;
                nbCarte++;
            }
        }
    }

    // Méthode effectuant le mélange du deck
    public void shuffle() {
        for(int i = 0; i < cartesRestantes(); i++) {
            Carte carteTemp;

            // génération d'un nombre entre 0 inclus et 52-cartesUtilisees
            Random r = new Random();
            int numCarte = r.nextInt(cartesRestantes());

            // Echange de la carte choisie au hasard et de la carte numéro i
            carteTemp = deck[i];
            deck[i] = deck[numCarte];
            deck[numCarte] = carteTemp;
        }
    }

    // Retourne une carte du deck et effectue la disparition de la carte du deck
    public Carte distribuerCarte() {
        Carte carteTemp;
        Random r = new Random();
        int numCarte = r.nextInt( cartesRestantes() );

        carteTemp = deck[numCarte];
        deck[numCarte] = null;

        if (cartesRestantes() - 1 >= numCarte)
            System.arraycopy(deck, numCarte + 1, deck, numCarte, cartesRestantes() - 1 - numCarte);

        deck[NBCARTESDECK-cartesUtilisees-1] = null;

        cartesUtilisees++;

        return carteTemp;
    }

    // retourne le nombre de carte restante dans le deck
    public int cartesRestantes() {
        int cpt = 0;
        for(int i = 0; i < NBCARTESDECK - cartesUtilisees; i++)
            if(deck[i] != null)
                cpt++;

        return cpt;
    }

    // Affiche le deck
    public String toString(){
        System.out.println("Liste des cartes du deck");
        String str = "";
        for(int i = 0; i < cartesRestantes(); i++) {
            str = str.concat(deck[i].toString() + "\n");
        }
        return str;
    }
}
