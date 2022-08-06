/*
Nom du Programme : Blackjack
But : Jouer au jeu de Blackjack
Auteur : Boulanger Charles Antoine
Date : 04/05/2020
Version : V2.0
*/

import java.util.Scanner;

public class Black_Jack {
    private static final Scanner pause = new Scanner(System.in);
    private static final int gain_init = 1000;

    // Méthode pause
    public static void pause() {
        System.out.println("Appuyer sur entrer pour continuer...");
        pause.nextLine();
    }

    public static void main(String[] args) {
        char rep_menu;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("----------BlackJack----------\n\nQue voulez-vous faire ?");
            System.out.println("\t 1 - Lancer une partie\n\t 2 - Lire les regles\n\t 3 - Quitter le jeu");

            // Demande de choix du joueur
            rep_menu = sc.nextLine().charAt(0);
            while (rep_menu != '1' && rep_menu != '2' && rep_menu != '3') {
                System.out.println("Veuillez entrer un chiffre entre 1 et 3 : ");
                rep_menu = sc.nextLine().charAt(0);
            }

            switch (rep_menu) {
                case '1':
                    Partie();
                    pause();
                    System.out.println("\n\n\n\n\n");

                    break;
                case '2':
                    Regles.afficher();
                    pause();
                    System.out.println("\n\n\n\n\n");

                    break;
                default:
                    break;
            }
        } while (rep_menu != '3');

        System.out.println("Merci d'avoir joué, au revoir !");
    }

    public static void Partie() {
        Scanner sc = new Scanner(System.in);
        MainJoueur mj = new MainJoueur(), // Main joueur
                   mc = new MainJoueur(); // Main croupier
        MainJoueur mj2 = new MainJoueur(); // Seconde main pour le split

        int mise = 0;
        char rep_j;
        Gain gj = new Gain(gain_init, mise), // Gain joueur
             gc = new Gain(gain_init, mise); // Gain croupier

        do {
            boolean split = false;
            boolean abandon = false;
            boolean finTourJoueur = false;
            Deck deck = new Deck(); // Nouveau deck complet
            deck.shuffle();
            mc.clear();
            mj.clear();
            mj2.clear();

            System.out.println("\nVotre solde est de " + gj.getGain() + " jetons.");
            System.out.println("Combien misez-vous ?");
            gj.setMise(sc.nextInt());
            sc.nextLine();
            while (gj.getMise() < 1 || gj.getMise() > gj.getGain()) {
                System.out.println("Mise trop élevée ou trop faible, veuillez réessayer : ");
                gj.setMise(sc.nextInt());
                sc.nextLine();
            }

            Carte cc1 = deck.distribuerCarte();
            Carte cc2 = deck.distribuerCarte();
            Carte cj1 = deck.distribuerCarte();
            Carte cj2 = deck.distribuerCarte();

            mc.ajouterCarte(cc1);
            mc.ajouterCarte(cc2);
            mj.ajouterCarte(cj1);
            mj.ajouterCarte(cj2);
            mj.trieParValeur();
            // Affichage des cartes du joueur et la carte visible du croupier
            System.out.println("\n\nLe croupier a un " + cc1 + "\n"); //on affiche la carte visible du croupier
            System.out.println("Vous avez \n" + mj + "donc vous avez " + mj.getBlackJackValeur() + " points\n"); //on affiche la main du joueur et le score associé

            // Joueur a fait Blackjack
            if (mj.getBlackJackValeur() == 21) {
                gj.setGain(gj.getGain() + (int)(gj.getMise() * 1.5));
                gc.setGain(gc.getGain() - (int)(gj.getMise() * 1.5));
                System.out.println("Vous avez fait un Blackjack, vous remportez " + gj.getMise() * 1.5 + " jetons, votre solde est désormais de " + gj.getGain());
                mc.trieParValeur();
                System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
            } // Joueur a plus de 21 points
            else if (mj.getBlackJackValeur() > 21) {
                gj.setGain(gj.getGain() - gj.getMise());
                gc.setGain(gc.getGain() + gj.getMise());
                System.out.println("Vous avez eu plus de 21 points, vous perdez " + gj.getMise() + " jetons, votre solde est désormais de " + gj.getGain());
                mc.trieParValeur();
                System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
            } // Joueur a le choix pour continuer à jouer
            else { // Le joueur peut avoir le choix pour jouer
                if (cc1.getValeurCommeChaine().equals("AS")) { // Si la première carte du croupier est un as, il peut demander une assurance
                    System.out.println("Voulez-vous une assurance ? ");
                    rep_j = sc.nextLine().charAt(0);
                    while (rep_j != 'O' && rep_j != 'o' && rep_j != 'N' && rep_j != 'n') {
                        System.out.println("Veuillez entrer O ou N : ");
                        rep_j = sc.nextLine().charAt(0);
                    }

                    if (rep_j == 'O' || rep_j == 'o') {
                        int mise_assurance = gj.getMise() / 2;
                        System.out.println("\nVous avez pris une assurance.");
                        mc.trieParValeur();

                        // Croupier a fait Blackjack
                        if (cc2.getValeur() == 10) {
                            System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                            System.out.println("Le croupier a fait un BlackJack, vous ne remportez et ne perdez aucun jetons, votre solde est de " + gj.getGain());
                        } else {
                            gj.setGain(gj.getGain() - mise_assurance);
                            gc.setGain(gc.getGain() + mise_assurance);
                            System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                            System.out.println("Le croupier n'a pas fait un BlackJack, vous perdez " + mise_assurance + " jetons, votre solde est désormais de " + gj.getGain());
                        }
                    }
                }
                else {
                    do { // Tour du joueur
                        System.out.println("Que voulez vous faire ? ");
                        System.out.println("\t 1 - Tirer une carte\n\t 2 - Rester\n\t 3 - Doubler\n\t 4 - Abandonner");
                        if (cj1.getValeur() == cj2.getValeur())
                            System.out.println("\t 5 - Partager");

                        rep_j = sc.nextLine().charAt(0);
                        while (rep_j != '1' && rep_j != '2' && rep_j != '3' && rep_j != '4' && rep_j != '5') {
                            System.out.println("Veuillez entrer un chiffre entre 1 et 5 : ");
                            rep_j = sc.nextLine().charAt(0);
                        }

                        System.out.println("\n\n\n\n\n");

                        switch (rep_j) {
                            case '1':
                                Carte c = deck.distribuerCarte();
                                System.out.println("Vous tirez la carte " + c);
                                mj.ajouterCarte(c);
                                mj.trieParValeur();
                                System.out.println("\nVous avez \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");

                                // Point supérieur à 21
                                if (mj.getBlackJackValeur() > 21) {
                                    gj.setGain(gj.getGain() - gj.getMise());
                                    gc.setGain(gc.getGain() + gj.getMise());
                                    System.out.println("Vous avez plus de 21 points, vous perdez " + gj.getMise() + " jetons, votre solde est désormais de " + gj.getGain());
                                    mc.trieParValeur();
                                    System.out.println("\nLe croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                                } else {
                                    System.out.println("Le croupier a un " + cc1 + "\n");
                                }

                                break;
                            case '2':
                                finTourJoueur = true;

                                break;
                            case '3':
                                finTourJoueur = true;
                                c = deck.distribuerCarte();
                                System.out.println("Vous tirez la carte " + c);
                                mj.ajouterCarte(c);
                                mj.trieParValeur();
                                System.out.println("Vous avez \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");
                                if (mj.getBlackJackValeur() > 21) {
                                    gj.setGain(gj.getGain() - 2 * gj.getMise());
                                    gc.setGain(gc.getGain() + 2 * gj.getMise());
                                    System.out.println("Vous avez perdu, vous perdez " + 2 * gj.getMise() + " jetons, votre solde est désormais de " + gj.getGain());
                                    mc.trieParValeur();
                                    System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                                }

                                break;
                            case '4':
                                finTourJoueur = true;
                                gj.setGain(gj.getGain() - gj.getMise() / 2);
                                gc.setGain(gc.getGain() + gj.getMise() / 2);
                                System.out.println("Vous perdez " + gj.getMise() / 2 + " jetons, votre solde est désormais de " + gj.getGain());
                                mc.trieParValeur();
                                System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");

                                break;
                            case '5':
                                split = true;
                                mj2.ajouterCarte(cj2);
                                mj.enleverCarte(1);
                                System.out.println("Vous jouez avec la première main \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");
                                split(mj, deck);
                                System.out.println("Vous jouez avec la deuxième main \n" + mj2 + "donc " + mj2.getBlackJackValeur() + " points\n");
                                split(mj2, deck);
                                mc.trieParValeur();
                                System.out.println("Le croupier avait \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");

                                break;
                            default:
                                break;
                        }
                    } while(mj.getBlackJackValeur() < 21 && !finTourJoueur && !split && !abandon);

                    // Tour du croupier
                    if(!abandon && mj.getBlackJackValeur() <= 21 || (split && (mj.getBlackJackValeur() <= 21 || mj2.getBlackJackValeur() <= 21))) {
                        System.out.println("Le croupier a \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                        // Tirage de scartes du croupier
                        while (mc.getBlackJackValeur() < 16) {
                            pause(); // Le croupier tire au fur et à mesure
                            Carte c = deck.distribuerCarte();
                            mc.ajouterCarte(c);
                            System.out.println("Le croupier tire la carte " + c);
                            mc.trieParValeur();
                            System.out.println("Le croupier a \n" + mc + "donc " + mc.getBlackJackValeur() + " points\n");
                        }

                        if(split)
                            System.out.println("Vous avez dans votre première main \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");
                        else
                            System.out.println("Vous avez \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");

                        // Calcul score dans première main
                        int[] gain = verifScore(mj, mc, gj.getMise(), gj.getGain(), gc.getGain(), rep_j);
                        gj.setGain(gain[0]);
                        gc.setGain(gain[1]);

                        if(split) {
                            System.out.println("\nVous avez dans votre deuxième main \n" + mj2 + "donc " + mj2.getBlackJackValeur() + " points\n");
                            // Calcul score dans deuxième main
                            gain = verifScore(mj2, mc, gj.getMise(), gj.getGain(), gc.getGain(), rep_j);
                            gj.setGain(gain[0]);
                            gc.setGain(gain[1]);
                        }
                    }
                }
            }

            pause();
            System.out.println("\n\n\n\n\n");
        } while(gj.getGain() > 0 && gc.getGain() > 0);

        // Gagnant ou perdant
        if(gc.getGain() < 1)
            System.out.println("Félicitations vous avez gagné, votre solde est de " + gj.getGain() + ", la banque vous doit " + Math.abs(gc.getGain()));
        else if(gj.getGain() < 1)
            System.out.println("Malheureusement vous avez perdu, votre solde est de " + gj.getGain() + ", vous devez à la banque " + Math.abs(gj.getGain()) + "\n");
    }

    // Effectue le tour du joueur s'il a choisi de split sa main
    public static void split(MainJoueur mj, Deck deck) {
        Scanner sc = new Scanner(System.in);
        char reponse;
        do {
            System.out.println("Que voulez vous faire ? ");
            System.out.println("\t 1 - Tirer une carte\n\t 2 - Rester");
            reponse = sc.nextLine().charAt(0);
            while (reponse != '1' && reponse != '2') {
                System.out.println("Veuillez entrer un chiffre entre 1 et 2 : ");
                reponse = sc.nextLine().charAt(0);
            }

            switch (reponse) {
                case '1':
                    Carte c = deck.distribuerCarte();
                    System.out.println("Vous tirez la carte " + c);
                    mj.ajouterCarte(c);
                    mj.trieParValeur();
                    System.out.println("Vous avez \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");
                    if (mj.getBlackJackValeur() > 21) {
                        System.out.println("Vous perdez cette main");
                    }
                    break;
                case '2':
                    System.out.println("Vous avez \n" + mj + "donc " + mj.getBlackJackValeur() + " points\n");
                    break;
                default:
                    break;
            }
        } while (mj.getBlackJackValeur() < 21 && reponse != '2');
    }

    // Vérifie le score du croupier et du joueur avant de renvoyer les gains de ceux-ci
    public static int[] verifScore (MainJoueur mj, MainJoueur mc, int mise, int gainj, int gainc, char reponse) {
        int[] tabgain = new int[2];
        if(mj.getBlackJackValeur() <= 21) {
            if(mc.getBlackJackValeur() > 21 && mj.getBlackJackValeur() <= 21) { // Croupier a plus de 21 points
                if(reponse == '3') {
                    gainj += 2 * mise;
                    gainc -= 2 * mise;
                    System.out.println("Vous gagnez " + 2 * mise + " jetons, votre solde est désormais de " + gainj);
                }
                else {
                    gainj += mise;
                    gainc -= mise;
                    System.out.println("Vous gagnez " + mise + " jetons, votre solde est désormais de " + gainj);
                }
            }
            else if(mc.getBlackJackValeur() == mj.getBlackJackValeur()) { // Égalité
                System.out.println("Il y a egalite donc vous ne gagnez et ne perdez aucun jeton, votre solde est désormais de " + gainj);
            }
            else if(mc.getBlackJackValeur() < mj.getBlackJackValeur()) { // Le croupier a moins de points que le joueur
                if(reponse == '3') {
                    gainj += 2 * mise;
                    gainc -= 2 * mise;
                    System.out.println("Vous gagnez " + 2 * mise + " jetons, votre solde est désormais de " + gainj);
                }
                else {
                    gainj += mise;
                    gainc -= mise;
                    System.out.println("Vous gagnez " + mise + " jetons, votre solde est désormais de " + gainj);
                }
            }
            else { // Soit le croupier a plus de point que le joueur
                if(reponse == '3') {
                    gainj -= 2 * mise;
                    gainc += 2 * mise;
                    System.out.println("Vous perdez " + 2 * mise + " jetons, votre solde est désormais de " + gainj);
                }
                else {
                    gainj -= mise;
                    gainc += mise;
                    System.out.println("Vous perdez " + mise + " jetons, votre solde est désormais de " + gainj);
                }
            }
        }
        tabgain[0] = gainj;
        tabgain[1] = gainc;

        return tabgain;
    }
}