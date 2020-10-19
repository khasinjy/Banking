package objects;

import java.io.*;
import com.google.gson.Gson;

public class FichierCompte {

    public void ecrire(ListeCompte listeCpte){

        Gson gson = new Gson();// create the gson object
        try {
            FileWriter writer = new FileWriter("src/main/java/ressources/Compte.dat");
            gson.toJson(listeCpte,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public ListeCompte lire(){
        ListeCompte listeCompte = new ListeCompte();
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/ressources/Compte.dat"));
            listeCompte = gson.fromJson(bufferedReader, ListeCompte.class);
            bufferedReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("Pas de fichier de sauvegarde de comptes trouvé.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return listeCompte;
    }
}