public class MainJoueur {
    private Carte[] mainJ;
    private int nbCartes;

    // Constructeur de la main du joueur, il peut avoir 6 cartes maximum et en a 0 au début de la partie
    public MainJoueur() {
        mainJ = new Carte[6];
        nbCartes = 0;
    }

    // Remets à 0 la main du joueur
    public void clear() {
        nbCartes = 0;
        for(int i = 0; i < 6; i++)
            mainJ[i] = null;
    }

    // Ajoute la carte c à la main du joueur
    public void ajouterCarte(Carte c) {
        boolean ajout = true;
        int i = 0;
        do {
            if(i >= 6) // Déjà 6 cartes en main
                ajout = false;
            else if(mainJ[i] == null) { // on atteint le premier slot vide de carte
                mainJ[i] = c;
                ajout = false;
            }
            i++;
        } while(ajout);
    }

    // Enleve la carte à la position pos dans la main
    public void enleverCarte(int pos) {
        //if(pos>=0 &&pos<6){
            mainJ[pos] = null;
            for(int i = pos; i < 6 - 1; i++) {
                if (mainJ[i + 1] != null)
                    mainJ[i] = mainJ[i + 1];
            }

            mainJ[6 - 1] = null;
       /* }
        else
            System.out.println("Position invalide");*/
    }

    // Retourne la carte à la position indiqué de la main du joueur
    public Carte getCarte(int position){
        return mainJ[position];
    }

    // Retourne le nombre de carte dans la main du joueur
    public int getnbCartes() {
        nbCartes = 0;
        for(int i = 0; i < 6; i++)
            if(mainJ[i] != null)
                nbCartes++;

        return nbCartes;
    }

    // Trie par valeur les cartes de la main du joueur
    public void trieParValeur() {
        Carte carteTemp;
        boolean echange;
        do {
            echange = false;
            for (int i = 0; i < getnbCartes() - 1; i++) {
                if (mainJ[i].getValeur() > mainJ[i+1].getValeur()) {
                    carteTemp = mainJ[i];
                    mainJ[i] = mainJ[i + 1];
                    mainJ[i + 1] = carteTemp;
                    echange = true;
                }
            }
        } while(echange);
    }

    // Affiche la main du joueur
    public String toString() {
        //System.out.println("Il y a " + getnbCartes() + " cartes :");
        String str = "";
        for(int i = 0; i < getnbCartes() ; i++)
            str = str.concat(mainJ[i].toString() + "\n");
        return str;
    }

    // Retourne la valeur de la main du joueur
    public int getBlackJackValeur() {
        int pts = 0;

        for (int i = 0; i < getnbCartes(); i++) {
            if(mainJ[i].getValeur() > 1 && mainJ[i].getValeur() < 10)
                pts += mainJ[i].getValeur();
            else if(mainJ[i].getValeur() > 9 && mainJ[i].getValeur() < 14)
                pts += 10;
            else if(mainJ[i].getValeur() == 1 && pts + 11 < 22)
                pts += 11;
            else
                pts++;
        }
        return pts;
    }
}
