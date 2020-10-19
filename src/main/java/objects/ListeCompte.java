package objects;

import java.util.HashMap;
import java.util.Map;

public class ListeCompte {
    private Map<String,Compte> listeCpte;

    public void ajouteUnCompte(String type){
        Compte c;
        switch (type){
            case "E" -> c = new CpteEpargne();
            default ->  c = new Compte("");
        }
        listeCpte.put(c.numero, c);
    }

    public void ajouteUnCompte(Compte c){
        listeCpte.put(c.numero, c);
    }

    public void ajouteUneLigne(String numero){
        try{
            listeCpte.get(numero).creerLigne();
        }catch(NullPointerException e){
            System.out.println("Le numero de compte n'est pas valide.");
        }
    }

    public void ajouteUneLigne(String numCpte, LigneComptable ligne){
        listeCpte.get(numCpte).creerLigne(ligne);
    }

    public void rechercheUnCompte(String numero){
        try{
            listeCpte.get(numero).afficherCpte();
        }catch(NullPointerException e){
            System.out.println("Le numero de compte n'est pas valide.");
        }
    }

    public Compte rechercheUnCompteIHM(String numero){
        Compte c = null;
        try{
            c = listeCpte.get(numero);
        }catch(NullPointerException ignored){
        }
        return c;
    }

    public void supprimeUnCompte(String numero){
        try{
            listeCpte.remove(numero);
        }catch(NullPointerException e){
            System.out.println("Le numero de compte n'est pas valide.");
        }
    }

    public String supprimeUnCompteIHM(String numero){
        String ret = "";
        try{
            listeCpte.remove(numero);
        }catch(NullPointerException e){
            ret = "Le numero de compte n'est pas valide.";
        }
        return ret;
    }

    public void afficheLesComptes(){
        for (Compte c : listeCpte.values()) {
            c.afficherCpte();
        }
    }

    public void afficheLesComptesIHM(){
        for (Compte c : listeCpte.values()) {
            c.afficherCpteIHM();
        }
    }

    public ListeCompte() {
        listeCpte = new HashMap<>();
    }

    public Map<String, Compte> getListeCpte() {
        return listeCpte;
    }

    public void restoreListeCpte(ListeCompte listeCpte){
        this.listeCpte = listeCpte.getListeCpte();
    }
}
