package org.uca.aeroport;

public class GenerateurNumeroVol {

    private int compteur = 1;

    public String next() {
        return "VOL-" + compteur++;
    }
}