package IHM;

import objects.Compte;
import objects.CpteEpargne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompteDialogue {

    public CompteDialogue(String numCpte) {
        Frame f = new Frame("Créer un compte bancaire");
        Label lb_cpt = new Label("Compte n°"+ numCpte);
        lb_cpt.setBounds(50,10,300,100);
        f.add(lb_cpt);

        Panel pTypeCpte = new Panel();
        pTypeCpte.setLayout(new BoxLayout(pTypeCpte,BoxLayout.X_AXIS));
        pTypeCpte.setBounds(50,100,350,50);
        CheckboxGroup cbg_typeCpt = new CheckboxGroup();
        Checkbox cb_cptCourant = new Checkbox("Compte Courant", cbg_typeCpt, false);
        Checkbox cb_cptEpargne = new Checkbox("Compte Epargne", cbg_typeCpt, true);
        Checkbox cb_cptJoint = new Checkbox("Compte Joint", cbg_typeCpt, false);
        pTypeCpte.add(cb_cptCourant);
        pTypeCpte.add(cb_cptEpargne);
        pTypeCpte.add(cb_cptJoint);
        f.add(pTypeCpte);

        Panel pSolde = new Panel();
        pSolde.setLayout(new BoxLayout(pSolde,BoxLayout.X_AXIS));
        pSolde.setBounds(50,150,320,20);
        Label lb_solde = new Label("Solde :");
        TextField tx_euro = new TextField();
        Label lb_euro = new Label("Euros");
        pSolde.add(lb_solde);
        pSolde.add(tx_euro);
        pSolde.add(lb_euro);
        f.add(pSolde);

        Panel pTaux = new Panel();
        pTaux.setLayout(new BoxLayout(pTaux,BoxLayout.X_AXIS));
        pTaux.setBounds(50,200,320,20);
        Label lb_taux = new Label("Taux de placement (en %):");
        TextField tx_taux = new TextField(null,5);
        pTaux.add(lb_taux);
        pTaux.add(tx_taux);
        f.add(pTaux);

        Button b_valider = new Button("Valider");
        b_valider.setBounds(50,240,50,30);
        f.add(b_valider);

        Button b_retour = new Button("Accueil");
        b_retour.setBounds(50,275,50,30);
        f.add(b_retour);

        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        cb_cptEpargne.addItemListener(e -> {
            pTaux.setVisible(e.getStateChange() == ItemEvent.SELECTED);
        });

        cb_cptCourant.addItemListener(e -> {
            pTaux.setVisible(e.getStateChange() != ItemEvent.SELECTED);
        });

        cb_cptJoint.addItemListener(e -> {
            pTaux.setVisible(e.getStateChange() != ItemEvent.SELECTED);
        });

        b_valider.addActionListener(e -> {
            Compte c = null;
            String strSolde = tx_euro.getText();
            if(!strSolde.equals("")){
                double solde = Double.parseDouble(strSolde);
                if(cb_cptCourant.getState()) {
                    c = new Compte("courant", numCpte, solde);
                }else if(cb_cptJoint.getState()){
                    c = new Compte("joint", numCpte, solde);
                }else{
                    String strTaux = tx_taux.getText();
                    if(!strTaux.equals("")){
                        double taux = Double.parseDouble(strTaux);
                        c = new CpteEpargne(taux, numCpte, solde);
                    }else{
                        JOptionPane.showMessageDialog(f,
                                "Veuillez saisir une valeur pour le taux.",
                                "Taux recquis",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(c!= null){
                    AccueilInterfaceMode.listeCpt.ajouteUnCompte(c);
                    AccueilInterfaceMode.fichier.ecrire(AccueilInterfaceMode.listeCpt);
                    JOptionPane.showMessageDialog(f,
                            c.afficherCpteIHM(),
                            "Nouveau compte crée",
                            JOptionPane.INFORMATION_MESSAGE);
                    new AccueilInterfaceMode();
                    f.dispose();
                }
            }else{
                JOptionPane.showMessageDialog(f,
                        "Veuillez saisir une valeur pour le solde initial.",
                        "Solde recquis",
                        JOptionPane.WARNING_MESSAGE);

            }
        });

        b_retour.addActionListener(e -> {
            f.dispose();
            AccueilInterfaceMode.Accueil();
        });
    }

}