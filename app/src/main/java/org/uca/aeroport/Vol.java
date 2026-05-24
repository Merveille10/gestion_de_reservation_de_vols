package org.uca.aeroport;

import org.uca.reservation.Client;
import org.uca.reservation.Passager;
import org.uca.reservation.Reservation;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Vol {

    // Numéro du vol
    private String numero;

    // Compagnie associée
    private Compagnie compagnie;

    // Etape de départ
    private Etape depart;

    // Etape d'arrivée
    private Etape arrivee;

    // Liste ordonnée des escales
    private List<Escale> escales = new ArrayList<>();

    // Liste des réservations du vol
    private List<Reservation> reservations = new ArrayList<>();

    // Etat du vol : ouvert ou fermé aux réservations
    private boolean ouvert = false;

    public Vol(String numero, Etape depart, Etape arrivee) 
    {

        // Vérifie le numéro
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException(
                    "Le numéro ne peut pas être vide"
            );
        }

        // Vérifie le départ
        if (depart == null) {
            throw new IllegalArgumentException(
                    "Le départ ne peut pas être null"
            );
        }

        // Vérifie l'arrivée
        if (arrivee == null) {
            throw new IllegalArgumentException(
                    "L'arrivée ne peut pas être null"
            );
        }

        // Vérifie que l'arrivée est bien après le départ
        if (arrivee.getDate().isBefore(depart.getDate())) {
            throw new IllegalArgumentException(
                    "L'arrivée doit être après le départ"
            );
        }

        this.numero = numero;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public String getNumero() {
        return numero;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public Etape getDepart() {
        return depart;
    }

    public Etape getArrivee() {
        return arrivee;
    }

    // Retourne une vue non modifiable des escales
    public List<Escale> getEscales() {
        return Collections.unmodifiableList(escales);
    }

    // Retourne une vue non modifiable des réservations
    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    // Ouvre les réservations du vol
    public void ouvrir() {
        ouvert = true;
    }

    // Ferme les réservations du vol
    public void fermer() {
        ouvert = false;
    }

    // Vérifie si le vol est ouvert
    public boolean estOuvert() {
        return ouvert;
    }

    // Ajoute une escale au vol
    public void ajouterEscale(Escale escale) {

        // Vérifie que l'escale existe
        if (escale == null) {
            throw new IllegalArgumentException(
                    "L'escale ne peut pas être null"
            );
        }

        escales.add(escale);
    }

    // Décale toutes les étapes du vol
    public void decaler(Duration duree) {

        // Vérifie que la durée existe
        if (duree == null) {
            throw new IllegalArgumentException(
                    "La durée ne peut pas être null"
            );
        }

        // Décale le départ
        depart.decaler(duree);

        // Décale l'arrivée
        arrivee.decaler(duree);

        // Décale toutes les escales
        for (Escale escale : escales) {
            escale.decaler(duree);
        }
    }

    // Calcule la durée totale entre départ et arrivée
    public Duration obtenirDuree() {
        return Duration.between(
                depart.getDate(),
                arrivee.getDate()
        );
    }

    // Calcule le prix actuel du vol
    public Money prix() {

        // Exemple simple : prix de base + supplément par escale
        BigDecimal montant =
                BigDecimal.valueOf(150 + (escales.size() * 25));

        return new Money(montant, "EUR");
    }

    // Crée une réservation pour un client et un passager
    public Reservation creerReservation(
            Client client,
            Passager passager
    ) {

        // Vérifie que le vol est ouvert
        if (!estOuvert()) {
            throw new IllegalStateException(
                    "Le vol est fermé"
            );
        }

        // Génère un numéro simple de réservation
        String numeroReservation =
                "RES-" + numero + "-" + (reservations.size() + 1);

        Reservation reservation =
                new Reservation(
                        numeroReservation,
                        client,
                        passager,
                        prix(),
                        this
                );

        reservations.add(reservation);

        return reservation;
    }

    // Gestion de la double navigabilité avec Compagnie
    public void setCompagnie(Compagnie compagnie) {

        // Retire l'ancien lien
        if (this.compagnie != null) {
            this.compagnie.removeVolWithoutBidirectional(this);
        }

        this.compagnie = compagnie;

        // Ajoute le nouveau lien
        if (compagnie != null) {
            compagnie.addVolWithoutBidirectional(this);
        }
    }

    // Méthode utilisée pour éviter les boucles infinies
    protected void setCompagnieWithoutBidirectional(
            Compagnie compagnie
    ) {
        this.compagnie = compagnie;
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Vol vol)) {
            return false;
        }

        return Objects.equals(numero, vol.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}