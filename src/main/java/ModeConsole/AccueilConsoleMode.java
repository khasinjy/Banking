package ModeConsole;

import objects.FichierCompte;
import objects.ListeCompte;

import java.util.Scanner;

public class AccueilConsoleMode {

    public AccueilConsoleMode() {
        ListeCompte c = new ListeCompte();
        FichierCompte f = new FichierCompte();
        if(f.lire() != null){
            c.restoreListeCpte(f.lire());
        }
        int choix = 0;
        do {
            menuPrincipal();
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();
            switch (choix) {
                case 1 -> {
                    int typeCpte;
                    do {
                        System.out.println("Quel type de compte créer : Epargne (1) ou Simple (2) ?");
                        typeCpte = sc.nextInt();
                    }while(typeCpte != 1 && typeCpte !=2);
                    switch (typeCpte){
                        case 1 -> c.ajouteUnCompte("E");
                        case 2 -> c.ajouteUnCompte("A");
                    }
                }
                case 2 -> c.afficheLesComptes();
                case 3 -> {
                    System.out.println("Saisissez le numero du compte auquel vous souhaitez ajouter une ligne.");
                    String numero = sc.next();
                    c.ajouteUneLigne(numero);
                }
                case 4 -> sortir();
                case 5 -> {
                    System.out.println("Saisissez le numero du compte que vous souhaitez supprimer.");
                    String numero = sc.next();
                    c.supprimeUnCompte(numero);
                }
                case 6 -> f.ecrire(c);
                case 7 -> alAide();
                default -> System.out.println("Choix incorrect");
            }
        }while(choix != 4);

    }

    private void sortir() {
        System.out.println("Au revoir!");
        System.exit(0);
    }

    private void alAide() {
        System.out.println("Bienvenue sur l'application Banking. " +
                "Saisir 1 pour créer un compte, 2 pour consulter et 4 pour quitter.");
    }

    private void menuPrincipal() {
        System.out.println("1. Creer un compte\n2. Afficher les comptes" +
                "\n3. Creer une ligne comptable\n4. Sortir\n5. Supprimer un compte" +
                "\n6. Sauvegarder les comptes\n7. De l'aide\nVotre choix :"
        );
    }
}
