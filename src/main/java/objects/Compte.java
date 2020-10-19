package objects;

import java.util.Scanner;

public class Compte {

    protected String type;
    protected String numero;
    protected double valeur;
    protected LigneComptable [] ligne;
    public  static  final  int NBLigne = 10;

    public int getNbLigneReel() {
        return nbLigneReel;
    }

    public double getValeur() {
        return valeur;
    }

    public LigneComptable[] getLigne() {
        return ligne;
    }

    private int nbLigneReel;

    public Compte(String type){
        Scanner lectureClavier = new Scanner(System.in);
        if(type.equals("")){
            this.type = controleType();
        }else{
            this.type = type;
        }
        System.out.println("Numero de compte :");
        this.numero = lectureClavier.next();
        this.valeur = controleValinit();
        this.ligne = new LigneComptable[NBLigne];
        nbLigneReel = -1;

    }

    public Compte(String type, String numero, double valeur) {
        this.type = type;
        this.numero = numero;
        this.valeur = valeur;
        this.ligne = new LigneComptable[NBLigne];
        nbLigneReel = -1;
    }

    public String afficherCpteIHM() {
        String sLignes = "";
        try{
            for(LigneComptable l: this.ligne){
                sLignes += l.afficherLigneIHM();
            }
        }catch(NullPointerException e){
            if(getNbLigneReel() == -1)
                sLignes = "Pas de ligne comptable pour ce compte.";
        }
        return "Compte n°" + numero +":" +
                "\nType de compte : " + type +
                "\nSolde initial : " + valeur +
                "\nLigne(s) comptable(s) : \n" + sLignes;
    }

    public void afficherCpte(){
        System.out.println("Type de compte : " + type);
        System.out.println("Numero de compte : " + numero);
        System.out.println("Valeur: " + valeur);
        try{
            System.out.println("Ligne(s) comptable(s) : ");
            for(LigneComptable l: this.ligne){
                l.afficherLigne();
            }
        }catch(NullPointerException e){
            if(getNbLigneReel() == -1)
                System.out.println("Pas de ligne comptable pour ce compte.");
        }

    }

    public void creerLigne(LigneComptable newLigne){
        int index;
        this.nbLigneReel += 1;
        if(this.nbLigneReel < NBLigne){
            index = nbLigneReel;
            this.ligne[index] = newLigne;
        }else{
            decalerLesLignes();
            index = NBLigne - 1;
            this.ligne[index] = newLigne;
        }

        String sensTransaction = this.ligne[index].getSens();
        double montant = this.ligne[index].getMontant();
        if(sensTransaction.equals("debit")){
            valeur -= montant;
        }else {
            valeur += montant;
        }
    }

    public void creerLigne(){
        LigneComptable newLigne = new LigneComptable();
        int index;
        this.nbLigneReel += 1;
        newLigne.creerLigneComptable();
        if(this.nbLigneReel < NBLigne){
            index = nbLigneReel;
            this.ligne[index] = newLigne;
        }else{
            decalerLesLignes();
            index = NBLigne - 1;
            this.ligne[index] = newLigne;
        }

        String sensTransaction = this.ligne[index].getSens();
        double montant = this.ligne[index].getMontant();
        if(sensTransaction.equals("debit")){
            valeur -= montant;
        }else {
            valeur += montant;
        }
    }

    private void decalerLesLignes() {
        for (int i = 1; i < NBLigne; i++){
            ligne[i-1] = ligne[i];
        }
    }

    private double controleValinit() {
        Scanner lectureClavier = new Scanner(System.in);
        double tmpc = 0;
        do{
            System.out.println("Première valeur creditee :");
            tmpc = lectureClavier.nextDouble();
        }while(tmpc<=0);
        return tmpc;
    }

    private String controleType() {
        String tmpc;
        String tmpS = "Courant";
        Scanner lectureClavier = new Scanner(System.in);
        do{
            System.out.println("Type du compte [Types possibles : courant (C), joint (J)]:");
            tmpc = lectureClavier.next();
        }while(!tmpc.equals("C") && !tmpc.equals("J"));
        switch (tmpc){
            case "C" -> tmpS = "courant";
            case "J" -> tmpS = "joint";
        }
        return tmpS;
    }

}
