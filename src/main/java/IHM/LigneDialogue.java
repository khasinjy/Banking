package IHM;

import objects.LigneComptable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LigneDialogue {

    public LigneDialogue(String numCpte) {
        Frame f = new Frame("Créer une ligne comptable");
        Label lb_cpt = new Label("Compte n°"+ numCpte);
        lb_cpt.setBounds(50,10,300,100);
        f.add(lb_cpt);

        Panel pRef = new Panel();
        pRef.setLayout(new GridLayout(1,1));
        pRef.setBounds(50,110,320,20);
        Label lb_ref= new Label("Référence :");
        TextField tx_ref = new TextField();
        pRef.add(lb_ref);
        pRef.add(tx_ref);
        f.add(pRef);

        Panel pDate = new Panel();
        pDate.setLayout(new GridLayout(1,1));
        pDate.setBounds(50,140,320,25);
        Label lb_date = new Label("Date :");
        JDateChooser datePicker = new JDateChooser();
        pDate.add(lb_date);
        pDate.add(datePicker);
        f.add(pDate);

        Panel pMontant = new Panel();
        pMontant.setLayout(new GridLayout(1,1));
        pMontant.setBounds(50,180,320,20);
        Label lb_montant = new Label("Montant :");
        TextField tx_montant = new TextField();
        Label lb_euro = new Label("Euros");
        pMontant.add(lb_montant);
        pMontant.add(tx_montant);
        pMontant.add(lb_euro);
        f.add(pMontant);

        Panel pMotif = new Panel();
        pMotif.setLayout(new GridLayout(1,1));
        pMotif.setBounds(50,210,320,30);
        Label lb_motif = new Label("Motif :");
        Choice c_motif = new Choice();
        c_motif.add("Salaire");
        c_motif.add("Loyer");
        c_motif.add("Alimentation");
        c_motif.add("Divers");
        pMotif.add(lb_motif);
        pMotif.add(c_motif);
        f.add(pMotif);

        Panel pMode = new Panel();
        pMode.setLayout(new GridLayout(1,1));
        pMode.setBounds(50,240,320,30);
        Label lb_mode = new Label("Mode de paiement :");
        Choice c_mode = new Choice();
        c_mode.add("Carte bleue");
        c_mode.add("Virement");
        c_mode.add("Chèque");
        pMode.add(lb_mode);
        pMode.add(c_mode);
        f.add(pMode);

        Panel pSens = new Panel();
        pSens.setLayout(new GridLayout(1,2));
        pSens.setBounds(130,280,130,25);
        CheckboxGroup cbg_sens = new CheckboxGroup();
        Checkbox cb_debit = new Checkbox("Débit", cbg_sens, true);
        Checkbox cb_credit = new Checkbox("Crédit", cbg_sens, false);
        pSens.add(cb_debit);
        pSens.add(cb_credit);
        f.add(pSens);

        Button b_valider = new Button("Valider");
        b_valider.setBounds(50,310,50,30);
        f.add(b_valider);

        Button b_retour = new Button("Accueil");
        b_retour.setBounds(50,350,50,30);
        f.add(b_retour);

        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        b_valider.addActionListener(e -> {

            String ref = tx_ref.getText();
            String strMontant = tx_montant.getText();
            Date date = datePicker.getDate();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(date);
            String motif = c_motif.getSelectedItem();
            String mode = c_mode.getSelectedItem();
            LigneComptable l;
            if(!ref.equals("") || !strMontant.equals("") || date != null|| !motif.equals("") || !mode.equals("")){
                double montant = Double.parseDouble(strMontant);
                if(cb_debit.getState()) {
                    l = new LigneComptable(ref, strDate, "debit", motif, montant, mode);
                }else{
                    l = new LigneComptable(ref, strDate, "credit", motif, montant, mode);
                }
                AccueilInterfaceMode.listeCpt.ajouteUneLigne(numCpte,l);
                AccueilInterfaceMode.fichier.ecrire(AccueilInterfaceMode.listeCpt);
                JOptionPane.showMessageDialog(f,
                        l.afficherLigneIHM(),
                        "Nouvelle ligne crée",
                        JOptionPane.INFORMATION_MESSAGE);
                new AccueilInterfaceMode();
                f.dispose();

            }else{
                JOptionPane.showMessageDialog(f,
                        "Veuillez saisir tous les champs pour créer une ligne.",
                        "Informations incomplètes",
                        JOptionPane.WARNING_MESSAGE);

            }
        });

        b_retour.addActionListener(e -> {
            f.dispose();
            AccueilInterfaceMode.Accueil();
        });
    }
}
