package org.uca.aeroport;

import java.util.Objects;

public class Aeroport {

    private String nom;

    private String ville;

    public Aeroport(String nom, String ville) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom ne peut pas être vide"
            );
        }

        if (ville == null || ville.isBlank()) {
            throw new IllegalArgumentException(
                    "La ville ne peut pas être vide"
            );
        }

        this.nom = nom;
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return nom + " (" + ville + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aeroport aeroport)) return false;

        return Objects.equals(nom, aeroport.nom)
                && Objects.equals(ville, aeroport.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, ville);
    }
}
