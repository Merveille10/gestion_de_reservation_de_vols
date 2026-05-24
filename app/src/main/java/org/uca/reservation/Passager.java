package org.uca.reservation;

import java.util.Objects;

public class Passager {

    // Nom du passager
    private String nom;

    // Numéro de passeport
    private String numeroPasseport;

    // Age du passager
    private int age;

    // Téléphone du passager
    private String telephone;

    public Passager(
            String nom,
            String numeroPasseport,
            int age,
            String telephone
    ) {

        // Vérifie que le nom est valide
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom du passager ne peut pas être vide"
            );
        }

        // Vérifie que le passeport est valide
        if (numeroPasseport == null || numeroPasseport.isBlank()) {
            throw new IllegalArgumentException(
                    "Le numéro de passeport ne peut pas être vide"
            );
        }

        // Vérifie que l'âge est cohérent
        if (age < 0) {
            throw new IllegalArgumentException(
                    "L'âge ne peut pas être négatif"
            );
        }

        // Vérifie que le téléphone est valide
        if (telephone == null || telephone.isBlank()) {
            throw new IllegalArgumentException(
                    "Le téléphone ne peut pas être vide"
            );
        }

        this.nom = nom;
        this.numeroPasseport = numeroPasseport;
        this.age = age;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getNumeroPasseport() {
        return numeroPasseport;
    }

    public int getAge() {
        return age;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return nom + " - passeport : " + numeroPasseport;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Passager passager)) {
            return false;
        }

        return Objects.equals(
                numeroPasseport,
                passager.numeroPasseport
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPasseport);
    }
}