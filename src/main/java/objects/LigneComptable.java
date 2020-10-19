package objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LigneComptable {

    private String reference;
    private String date;
    private String sens;
    private String motif;
    private double montant;
    private String mode;

    public LigneComptable() {

    }

    public String getSens() {
        return sens;
    }

    public double getMontant() {
        return montant;
    }

    public String getReference() {
        return reference;
    }

    public String getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public String getMode() {
        return mode;
    }

    public LigneComptable(String reference, String date, String sens, String motif, double montant, String mode) {
        this.reference = reference;
        this.date = date;
        this.sens = sens;
        this.motif = motif;
        this.montant = montant;
        this.mode = mode;
    }

    public void creerLigneComptable(){
        Scanner lectureClavier = new Scanner(System.in);
        System.out.println("Reférence :");
        this.reference = lectureClavier.next();
        this.motif = controleMotif();
        this.date = controleDate();
        this.sens = controleSens();
        System.out.println("Montant de la transaction :");
        this.montant = lectureClavier.nextDouble();
        this.mode = controleMode();
    }

    private String controleDate() {
        Scanner lectureClavier = new Scanner(System.in);
        String dateSaisie, dateValide = "";
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date(); //par defaut aujoud'hui
        int nbEssai= 0;
        boolean ok = false;
        do  {
            System.out.println("Date de la transaction (JJ-MM-AAAA) :");
            dateSaisie = lectureClavier.next();
            try {
                String dateTab[] = dateSaisie.split("-");
                int annee = Integer.parseInt(dateTab[2]);
                int mois = Integer.parseInt(dateTab[1]);
                int jour = Integer.parseInt(dateTab[0]);
                if (mois < 1 || mois > 12 || jour < 1 || jour > 31){
                    ok = false;
                    throw new Exception();
                }else{
                    ok = true;
                    dateValide = dateSaisie;
                }
            }
            catch(Exception e) {
                nbEssai ++;
                System.out.println("Format de date incorrect.");
                ok = false;
            }
        }while ((!ok) && nbEssai<3);
        if(!ok){
            System.out.println("La date de la transaction est la date du jour.");
            dateValide = DATE_FORMAT.format(today);
        }
        return dateValide;
    }

    private String controleSens() {
        Scanner lectureClavier = new Scanner(System.in);
        String tmpc = "credit";
        do{
            System.out.println("Sens du flux [Types possibles : debit, credit)] :");
            tmpc = lectureClavier.next();
        }while(!tmpc.equals("debit") && !tmpc.equals("credit"));
        return tmpc;
    }

    private String controleMode() {
        Scanner lectureClavier = new Scanner(System.in);
        String tmpc;
        do{
            System.out.println("Mode de paiement [Types possibles : CB, virement, cheque)] :");
            tmpc = lectureClavier.next();
        }while(!tmpc.equals("CB") && !tmpc.equals("virement") && !tmpc.equals("cheque"));
        return tmpc;
    }

    private String controleMotif() {
        Scanner lectureClavier = new Scanner(System.in);
        String tmpc;
        do{
            System.out.println("Motif de la transaction [Types possibles : salaire, loyer, alimentation, divers]");
            tmpc = lectureClavier.next();
        }while(!tmpc.equals("salaire") && !tmpc.equals("loyer") && !tmpc.equals("alimentation") && !tmpc.equals("divers"));
        return tmpc;
    }

    public void afficherLigne() {
            System.out.println("{\nRéférence : " + reference +
                    "\nMotif : " + motif +
                    "\nDate de la transaction : " + date +
                    "\nSens du flux : " + sens +
                    "\nMontant de la transaction : " + montant +
                    "\nMode de paiement : " + mode +"\n}");
    }

    public String afficherLigneIHM() {
        return "{\nRéférence : " + reference +
                "\nMotif : " + motif +
                "\nDate de la transaction : " + date +
                "\nSens du flux : " + sens +
                "\nMontant de la transaction : " + montant +
                "\nMode de paiement : " + mode +"\n}";
    }

    @Override
    public String toString() {
        return "LigneComptable{" +
                "reference='" + reference + '\'' +
                ", date='" + date + '\'' +
                ", sens='" + sens + '\'' +
                ", motif='" + motif + '\'' +
                ", montant=" + montant +
                ", mode='" + mode + '\'' +
                '}';
    }
}
