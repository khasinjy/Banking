package IHM;

import objects.Compte;
import objects.LigneComptable;

import javax.swing.*;
import java.awt.*;

public class CompteEdit {

    public CompteEdit(String numCpte) {
        Compte c = AccueilInterfaceMode.listeCpt.rechercheUnCompteIHM(numCpte);
        Frame f = new Frame("Edition de comptes bancaires");
        Label lb_cpt = new Label("Compte n°" + numCpte);
        lb_cpt.setBounds(50, 20, 300, 50);
        f.add(lb_cpt);

        Panel pColonnes = new Panel();
        pColonnes.setLayout(new BoxLayout(pColonnes,BoxLayout.X_AXIS));
        pColonnes.setBounds(10,80,400,15);
        pColonnes.setBackground(Color.pink);
        Label lb_ref= new Label("Réf.");
        Label lb_date = new Label("Date");
        Label lb_montant = new Label("Montant");
        Label lb_motif = new Label("Motif");
        Label lb_mode = new Label("Mode");
        pColonnes.add(lb_ref);
        pColonnes.add(lb_date);
        pColonnes.add(lb_montant);
        pColonnes.add(lb_motif);
        pColonnes.add(lb_mode);
        f.add(pColonnes);

        Panel pContent = new Panel();
        pContent.setLayout(new BoxLayout(pContent,BoxLayout.Y_AXIS));
        pContent.setBounds(15,90,400,280);
        for (LigneComptable l : c.getLigne()) {
            Panel pLigne = new Panel();
            pLigne.setLayout(new BoxLayout(pLigne,BoxLayout.X_AXIS));
            pLigne.setSize(280,5);
            if(l != null){
                pLigne.add(new Label(l.getReference()));
                pLigne.add(new Label(l.getDate()));
                String sens = l.getSens().equals("debit") ? "-" : "+";
                String montant = String.valueOf(l.getMontant());
                pLigne.add(new Label(sens + montant + " €"));
                pLigne.add(new Label(l.getMotif()));
                pLigne.add(new Label(l.getMode()));
            }
            pContent.add(pLigne);
        }
        f.add(pContent);

        String solde = String.valueOf(c.getValeur());
        Label lb_solde = new Label("Solde : " + solde + "€");
        lb_solde.setBounds(50,380,200,20);
        f.add(lb_solde);

        Button b_ok = new Button("OK");
        b_ok.setBounds(50,410,50,20);
        f.add(b_ok);

        Button b_supp = new Button("Supprimer");
        b_supp.setBounds(50,440,80,20);
        f.add(b_supp);


        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        b_ok.addActionListener(e -> {
            AccueilInterfaceMode.Accueil();
            f.dispose();
        });

        b_supp.addActionListener(e -> {
            AccueilInterfaceMode.listeCpt.supprimeUnCompteIHM(numCpte);
            JOptionPane.showMessageDialog(f,
                    "Le compte n°" + numCpte + " a été supprimé.",
                    "Compte supprimé",
                    JOptionPane.INFORMATION_MESSAGE);
            AccueilInterfaceMode.Accueil();
            f.dispose();
        });

    }
}
