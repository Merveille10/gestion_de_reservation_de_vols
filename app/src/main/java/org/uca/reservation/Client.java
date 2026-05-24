package org.uca.reservation;

import java.util.Objects;

public class Client {

    // Nom du client
    private String nom;

    // Moyen de paiement du client
    private String moyenPaiement;

    // Adresse mail du client
    private String mail;

    // Points de fidélité du client
    private int points;

    public Client(
            String nom,
            String moyenPaiement,
            String mail,
            int points
    ) {

        // Vérifie que le nom est valide
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom du client ne peut pas être vide"
            );
        }

        // Vérifie que le moyen de paiement est valide
        if (moyenPaiement == null || moyenPaiement.isBlank()) {
            throw new IllegalArgumentException(
                    "Le moyen de paiement ne peut pas être vide"
            );
        }

        // Vérifie que le mail est valide
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException(
                    "Le mail ne peut pas être vide"
            );
        }

        // Les points ne peuvent pas être négatifs
        if (points < 0) {
            throw new IllegalArgumentException(
                    "Les points ne peuvent pas être négatifs"
            );
        }

        this.nom = nom;
        this.moyenPaiement = moyenPaiement;
        this.mail = mail;
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public String getMail() {
        return mail;
    }

    public int getPoints() {
        return points;
    }

    // Ajoute des points de fidélité
    public void ajouterPoints(int points) {

        if (points < 0) {
            throw new IllegalArgumentException(
                    "Les points ajoutés doivent être positifs"
            );
        }

        this.points += points;
    }

    @Override
    public String toString() {
        return nom + " (" + mail + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Client client)) {
            return false;
        }

        return Objects.equals(mail, client.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}