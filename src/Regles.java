abstract class Regles {
    public static void afficher() {
        String rules = "";
        rules += "\n\n\n\n\n----------BlackJack----------\n\n";
        rules += "But : Battre le croupier sans dépasser un score de 21, le croupier est battu s'il n'a plus de jetons. Les valeurs des cartes sont les suivantes :\n";
        rules += "\t- De 2 à 9 : valeur nominale de la carte\n\t- du 10 au Roi : 10 points\n\t- As : 11 si le score ne dépasse pas 21, 1 sinon.\n\n";

        rules += "-----Début de Partie-----\n\nLe joueur et le croupier commencent avec 1000 jetons. Le joueur choisit une mise puis le croupier distribue 2 cartes au joueur et 2 à lui-même, les 2 cartes du joueur et la première du croupier sont visible.\n";
        rules += "Un Blackjack est la situation où le joueur a 21 points directement avec ces deux cartes. Ainsi le joueur n'a pas de choix à faire et attend les résultats.\n\n";

        rules += "-----Choix du joueur-----\n\n Selon la situation, le joueur peut faire plusieurs actions :\n";
        rules += "\t- Tirer une carte : Tant qu'il ne dépasse pas un score de 21, il peut demander à tirer une carte.\n";
        rules += "\t- Rester : Il peut arrêter de tirer des cartes, et le service du croupier commence.\n";
        rules += "\t- Doubler : Il double sa mise initiale et reçoit une dernière carte.\n";
        rules += "\t- Abandonner : Il perd la moitié de sa mise et une nouvelle manche commence.\n";
        rules += "\t- Partager : Si le joueur a ses 2 cartes de la même valeur, il peut diviser sa main en deux, ce qui fait qu'il aura deux mains. Le joueur jouera\n\t chaque main de la même façon qu'avec une seule ensuite.\n";
        rules += "\t- Prendre une assurance : Si la première carte du croupier est un As, le joueur peut demander une assurance. Il rajoute alors la moitié de sa mise initiale.\n\t Si le croupier fait Blackjack, le joueur ne perd et ne gagne aucun jeton. Sinon, il gagne l'assurance, soit la moitié de sa mise.\n\n";

        rules += "-----Service du croupier-----\n\n";
        rules += "Une fois que le joueur a fini de jouer, le croupier va dévoiler sa seconde carte, puis va tirer des cartes jusqu'à ce que son score dépasse 16.\n\n";

        rules += "-----Résultats-----\n\n";
        rules += "Une fois le tour du croupier fini, si le joueur a moins de 21 points et le croupier plus de 21, le joueur gagne sa mise initiale\nS'il y a égalité avec le croupier, personne ne gagne ou ne perd de jeton.\nSi le croupier a moins de 21 points et a plus de point que le joueur, le joueur perd sa mise.\n";
        rules += "Si le joueur a fait Blackjack, celui-ci récupère sa mise plus la moitié.\n\n";

        rules += "Bonne chance !";
        System.out.println(rules);
    }
}
