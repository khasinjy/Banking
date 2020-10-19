import IHM.AccueilInterfaceMode;
import ModeConsole.AccueilConsoleMode;

import java.util.Scanner;

public class Projet {

    public static void main(String [] argument) {
        boolean ok = false;
        do{
            try{
                menu();
                ok = true;
            }catch (Exception e){
                System.out.println("Veuillez saisir 1 ou 2.");
            }
        }while(!ok);

    }

    private static void menu() {
        Scanner lectureClavier = new Scanner(System.in);
        int choix = 0;
        do{
            System.out.println("Quel mode lancer : Mode Console (1) ou Mode Interface (2) ?");
            choix = lectureClavier.nextInt();
        }while(choix != 1 && choix != 2);


        switch (choix) {
            case 1 -> new AccueilConsoleMode();
            case 2 -> new AccueilInterfaceMode();
        }
    }

}
