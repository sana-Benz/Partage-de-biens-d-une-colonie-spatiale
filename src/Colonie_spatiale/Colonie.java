package Colonie_spatiale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Colonie {
    public int n;
    private List <Colon> colons;

    public Colonie(int n) throws ExceptionColon {
        if (n > 26) {
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser 26.");
        }
        this.n = n;
        this.colons = new ArrayList<>();

        for (char lettre = 'A'; lettre <= 'Z' && colons.size() < n; lettre++) {
            Colon c = new Colon(lettre);
             colons.add(c); } 
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
}
