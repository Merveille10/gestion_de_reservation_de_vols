package org.uca.reservation;

import org.uca.aeroport.Money;
import org.uca.aeroport.Vol;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {

    // Numéro de la réservation
    private final String numero;

    // Date de création de la réservation
    private final LocalDateTime date;

    // Prix fixé au moment de la réservation
    private final Money prix;

    // Client qui effectue la réservation
    private final Client client;

    // Passager concerné par la réservation
    private final Passager passager;

    // Vol associé à la réservation
    private final Vol vol;

    // Pattern State utilisé pour gérer l'état courant
    private EtatReservation etat;

    public Reservation(String numero, Client client, Passager passager, Money prix, Vol vol) 
    {

        // Vérifie que le numéro est valide
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException(
                    "Le numéro de réservation ne peut pas être vide"
            );
        }

        // Vérifie que le client existe
        if (client == null) {
            throw new IllegalArgumentException(
                    "Le client ne peut pas être null"
            );
        }

        // Vérifie que le passager existe
        if (passager == null) {
            throw new IllegalArgumentException(
                    "Le passager ne peut pas être null"
            );
        }

        // Vérifie que le prix existe
        if (prix == null) {
            throw new IllegalArgumentException(
                    "Le prix ne peut pas être null"
            );
        }

        // Vérifie que le vol existe
        if (vol == null) {
            throw new IllegalArgumentException(
                    "Le vol ne peut pas être null"
            );
        }

        this.numero = numero;
        this.date = LocalDateTime.now();
        this.client = client;
        this.passager = passager;
        this.prix = prix;
        this.vol = vol;

        // Une réservation commence toujours à l'état initial
        this.etat = new EtatInitial();
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Money getPrix() {
        return prix;
    }

    public Client getClient() {
        return client;
    }

    public Passager getPassager() {
        return passager;
    }

    public Vol getVol() {
        return vol;
    }

    public EtatReservation getEtat() {
        return etat;
    }

    // Paye la réservation
    // Paye la réservation
    public void payer() {

        // On demande d'abord à l'état si le paiement est possible
        EtatReservation nouvelEtat = etat.payer();

        // Si la transition est possible, on débite le client
        debiter();

        // Puis on change l'état de la réservation
        etat = nouvelEtat;
    }

    // Confirme la réservation
    public void confirmer() {

        // Le changement d'état est délégué à l'état courant
        etat = etat.confirmer();
    }

    // Annule la réservation
    public void annuler() {

        // Le changement d'état est délégué à l'état courant
        etat = etat.annuler();
    }

    // Méthode privée appelée au paiement
    private void debiter() {

        // Simulation simple du paiement
        if (client.getMoyenPaiement() == null || client.getMoyenPaiement().isBlank()) 
        {
            throw new IllegalStateException(
                    "Impossible de débiter le client"
            );
        }
    }

    @Override
    public String toString() {
        return numero
                + " | "
                + client.getNom()
                + " | "
                + passager.getNom()
                + " | "
                + prix
                + " | "
                + etat.getNom();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Reservation that)) {
            return false;
        }

        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}