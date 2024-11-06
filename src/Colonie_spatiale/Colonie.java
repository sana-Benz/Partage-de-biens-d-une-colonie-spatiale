package Colonie_spatiale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colonie {
    public int n;
    private ArrayList <Colon> colons;
    private Map<Ressource,Colon> ressources;

    public Colonie(int n) throws ExceptionColon {
        if (n > 26) {
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser 26.");
        }
        this.n = n;
        this.colons = new ArrayList<>();
        this.ressources = new HashMap<>();
        for (char lettre = 'A'; lettre <= 'Z' && colons.size() < n; lettre++) {
            Colon c = new Colon(lettre);
             colons.add(c); }
        for(int i=1;i<=n;i++){
            Ressource r = new Ressource((char) ('0' + i));
            ressources.put(r, null);
        }
    }

    public  int getn(){
        return n;
    }

    public Colon getColon(char nom){

        for(Colon x : colons){
            if(x.getNom()==nom){
                return x;
            }
        }
        return null;
    }


    public void ajoutColon (Colon c) throws ExceptionColon {
        if (colons.size() > n) {
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser le nombre donné.");
        }
        colons.add(c);
    }
    public ArrayList<Colon> getlistecolons(){
        return colons;
    }
    public Map<Ressource, Colon> getRessources() {
        return ressources;
    }
    //solution naive
    public void affectationNaive(){
        for (Colon c : colons){
            for (Preference p : c.getlistepreferences()){
                Ressource ressource = getRessourceParNom(p.getNom());
                if (ressource !=null && ressources.get(ressource)==null){
                    c.setRessourceAttribuée(ressource);
                    ressources.put(ressource,c);
                    break;
                }
            }
        }
    }



    public int nombreColonsJaloux() {
        int nombreJaloux = 0;

        for (Colon colon : this.colons) {
            for (Colon ennemi : colon.getEnnemis()) {
                Ressource ressourceColon = colon.getRessourceAttribuée();
                Ressource ressourceEnnemi = ennemi.getRessourceAttribuée();

                if (colon.prefereObjet(ressourceEnnemi) && !ressourceColon.equals(ressourceEnnemi)) {
                    System.out.println("Le colon " + colon.getNom() + " est jaloux de l'ennemi " + ennemi.getNom() + " avec ressource " + ressourceEnnemi);
                    nombreJaloux++;
                    break;
                }
            }
        }

        return nombreJaloux;
    }


    public Ressource getRessourceParNom(char nom) {
        for (Ressource r : ressources.keySet()) {
            if (r.getNom() == nom) {
                return r;
            }
        }
        return null;
    }
    public void affichageaffection(){
        for (Map.Entry<Ressource, Colon> entry : ressources.entrySet()) {

            if (entry.getValue() != null) { // Vérifie si la valeur n'est pas null

                System.out.println( entry.getValue().getNom() + " : " + entry.getKey().getNom() );
            }

        }
    }
    public void echangerRessources(Colon colon1, Colon colon2) {

        Ressource ressource1 = colon1.getRessourceAttribuée();
        Ressource ressource2 = colon2.getRessourceAttribuée();


        if (ressource1 != null && ressource2 != null) {

            colon1.setRessourceAttribuée(ressource2);

            colon2.setRessourceAttribuée(ressource1);

            // Met à jour le dictionnaire de ressources

            ressources.put(ressource1, colon2);

            ressources.put(ressource2, colon1);

        } else {

            System.out.println("L'un des colons n'a pas de ressource attribuée, impossible d'échanger.");

        }

    }


}
