package IHM;

import objects.Compte;
import objects.FichierCompte;
import objects.ListeCompte;

import javax.swing.*;
import java.awt.*;

public class AccueilInterfaceMode {
    public static FichierCompte fichier = new FichierCompte();
    public static ListeCompte listeCpt = new ListeCompte();

    public AccueilInterfaceMode() {
        if(fichier.lire() != null){
            listeCpt.restoreListeCpte(fichier.lire());
        }
        Accueil();
    }

    public static void Accueil(){
        Frame f = new Frame("Gestion de comptes bancaires");

        MenuBar menuBar = new MenuBar();
        f.setMenuBar(menuBar);
        Menu menu = new Menu("Fichier");
        MenuItem menuItem = new MenuItem("Ouvrir");
        MenuItem menuQuitter = new MenuItem("Quitter");
        menu.add(menuItem);
        menu.add(menuQuitter);
        menuBar.add(menu);

        Panel pRadioButtons = new Panel();
        pRadioButtons.setLayout(new GridLayout(4,1));

        Label lb_cpt = new Label("Comptes");
        pRadioButtons.add(lb_cpt);
        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox cb_CreerCpt = new Checkbox("Créer", cbg, true);
        Checkbox cb_AjoutLigne = new Checkbox("Ajouter une ligne comptable", cbg, false);
        Checkbox cb_EditCpt = new Checkbox("Editer", cbg, false);
        pRadioButtons.add(cb_CreerCpt);
        pRadioButtons.add(cb_AjoutLigne);
        pRadioButtons.add(cb_EditCpt);
        pRadioButtons.setBounds(50,80,300,100);
        f.add(pRadioButtons);

        Panel pNum = new Panel();
        pNum.setLayout(new BorderLayout(0,0));
        pNum.setBounds(50,200,300,50);

        Button b_ok = new Button("OK");
        b_ok.setBounds(0,0,100,10);

        TextField tx_num = new TextField();
        tx_num.setBounds(30,0,100,30);

        Label lb_num = new Label("Numero de compte :");
        pNum.add(lb_num,BorderLayout.NORTH);
        pNum.add(tx_num);
        pNum.add(b_ok,BorderLayout.EAST);

        f.add(pNum);

        b_ok.addActionListener(e -> {
            String numCpte = tx_num.getText();
            if(!numCpte.equals("")){
                if(cb_CreerCpt.getState()) {
                    new CompteDialogue(numCpte);
                    f.dispose();
                }else {
                    Compte c = listeCpt.rechercheUnCompteIHM(numCpte);
                    if(c != null){
                        if(cb_AjoutLigne.getState()){
                            new LigneDialogue(numCpte);
                            f.dispose();
                        }else{
                            new CompteEdit(numCpte);
                            f.dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(f,
                                "Le numero de compte saisi n'est pas valide.",
                                "Numero de compte introuvable",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(f,
                        "Veuillez saisir un numero de compte.",
                        "Numero de compte recquis",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        f.setSize(400,500);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
    }
}
