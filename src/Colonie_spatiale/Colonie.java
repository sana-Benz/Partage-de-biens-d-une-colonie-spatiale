package Colonie_spatiale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Colonie {
    private int n;
    private List <Colon> colons;

    public Colonie(int n) throws ExceptionColon {
        if (n > 26) {
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser 26.");
        }
        this.n = n;
        this.colons = new ArrayList<>();
    }


    public void ajoutColon (Colon c) throws ExceptionColon {
        if (colons.size() > n) {
            throw new ExceptionColon("Le nombre de colons ne peut pas dépasser le nombre donné.");
        }
        colons.add(c);
    }
}
