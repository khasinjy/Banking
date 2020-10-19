package objects;

import java.util.Scanner;

public class CpteEpargne extends Compte {

    private double taux;

    public CpteEpargne(){
        super("epargne");
        this.taux = controleTaux();
    }

    public CpteEpargne(double taux, String numero, double valeur){
        super("epargne", numero, valeur);
        this.taux = taux;
    }

    public void afficherCpte(){
        super.afficherCpte();
        System.out.println("Taux de placement : " + taux);
    }

    public String afficherCpteIHM(){
        return super.afficherCpteIHM()+ '\n' + "Taux de placement : " + taux;
    }

    private double controleTaux() {
        Scanner lectureClavier = new Scanner(System.in);
        double tmpc = 0;
        do{
            System.out.println("Taux de placement :");
            tmpc = lectureClavier.nextDouble();
        }while(tmpc<0);
        return tmpc;
    }
}
