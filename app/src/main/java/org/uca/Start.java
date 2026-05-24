package org.uca;

import org.uca.aeroport.*;
import org.uca.reservation.Client;
import org.uca.reservation.Passager;
import org.uca.reservation.Reservation;

import java.time.Duration;
import java.time.LocalDateTime;

public class Start {

    public static void main(String[] args) {

        // Création des aéroports
        Aeroport cdg =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport dxb =
                new Aeroport(
                        "Dubai International",
                        "Dubai"
                );

        Aeroport syd =
                new Aeroport(
                        "Sydney Airport",
                        "Sydney"
                );

        Aeroport lhr =
                new Aeroport(
                        "Heathrow",
                        "Londres"
                );

        // Création de la compagnie
        Compagnie airFrance =
                new Compagnie(
                        "Air France",
                        new GenerateurNumeroVol()
                );

        System.out.println("================================");
        System.out.println("        VOL DIRECT");
        System.out.println("================================");

        // Dates du vol direct
        LocalDateTime dateDepartDirect =
                LocalDateTime.of(
                        2026,
                        10,
                        21,
                        13,
                        0
                );

        LocalDateTime dateArriveeDirect =
                dateDepartDirect.plusHours(1).plusMinutes(30);

        // Etapes du vol direct
        Etape departDirect =
                new Etape(
                        cdg,
                        dateDepartDirect
                );

        Etape arriveeDirect =
                new Etape(
                        lhr,
                        dateArriveeDirect
                );

        // Création du vol direct par la compagnie
        Vol volDirect =
                airFrance.creerVol(
                        departDirect,
                        arriveeDirect
                );

        System.out.println("Compagnie : "
                + volDirect.getCompagnie());

        System.out.println("Numéro du vol direct : "
                + volDirect.getNumero());

        System.out.println("Aéroport de départ : "
                + volDirect.getDepart().getAeroport().getNom());

        System.out.println("Aéroport d'arrivée : "
                + volDirect.getArrivee().getAeroport().getNom());

        System.out.println("Date de départ : "
                + volDirect.getDepart().getDate());

        System.out.println("Date d'arrivée : "
                + volDirect.getArrivee().getDate());

        System.out.println("Durée du vol direct : "
                + volDirect.obtenirDuree().toHours()
                + "h "
                + volDirect.obtenirDuree().toMinutesPart()
                + "min");

        System.out.println("Nombre d'escales : "
                + volDirect.getEscales().size());

        System.out.println("Prix actuel : "
                + volDirect.prix());

        System.out.println();

        System.out.println("================================");
        System.out.println("        VOL AVEC ESCALE");
        System.out.println("================================");

        // Dates du vol avec escale
        LocalDateTime dateDepartEscale =
                LocalDateTime.of(
                        2026,
                        10,
                        21,
                        13,
                        0
                );

        LocalDateTime dateEscale =
                dateDepartEscale.plusHours(6);

        LocalDateTime dateArriveeEscale =
                dateEscale.plusHours(2).plusHours(14);

        // Etapes du vol avec escale
        Etape departEscale =
                new Etape(
                        cdg,
                        dateDepartEscale
                );

        Etape arriveeEscale =
                new Etape(
                        syd,
                        dateArriveeEscale
                );

        // Création du vol avec escale
        Vol volAvecEscale =
                airFrance.creerVol(
                        departEscale,
                        arriveeEscale
                );

        // Création de l'escale
        Escale escaleDubai =
                new Escale(
                        dxb,
                        dateEscale,
                        Duration.ofHours(2)
                );

        // Ajout de l'escale au vol
        volAvecEscale.ajouterEscale(escaleDubai);

        System.out.println("Compagnie : " + volAvecEscale.getCompagnie());

        System.out.println("Numéro du vol avec escale : " + volAvecEscale.getNumero());

        System.out.println("Aéroport de départ : " + volAvecEscale.getDepart().getAeroport().getNom());

        System.out.println("Aéroport d'arrivée : " + volAvecEscale.getArrivee().getAeroport().getNom());

        System.out.println("Date de départ : " + volAvecEscale.getDepart().getDate());

        System.out.println("Date d'arrivée : " + volAvecEscale.getArrivee().getDate());

        System.out.println("Durée totale du vol : " + volAvecEscale.obtenirDuree().toHours() + "h");

        System.out.println("Nombre d'escales : " + volAvecEscale.getEscales().size());

        System.out.println("Escale à : " + volAvecEscale.getEscales().get(0).getAeroport().getNom());

        System.out.println("Durée de l'escale : " + volAvecEscale.getEscales().get(0).getDuree().toHours() + "h");

        System.out.println("Prix actuel : " + volAvecEscale.prix());

        System.out.println();

        System.out.println("================================");
        System.out.println("     DECALAGE DU VOL");
        System.out.println("================================");

        // Décalage du vol de 2 heures
        volAvecEscale.decaler(Duration.ofHours(2));

        System.out.println("Nouvelle date de départ : "
                + volAvecEscale.getDepart().getDate());

        System.out.println("Nouvelle date d'arrivée : "
                + volAvecEscale.getArrivee().getDate());

        System.out.println("Nouvelle date de l'escale : "
                + volAvecEscale.getEscales()
                        .get(0)
                        .getDate());

        System.out.println();

        System.out.println("================================");
        System.out.println("        RESERVATION");
        System.out.println("================================");

        // Création du client
        Client client =
                new Client(
                        "Alice",
                        "Carte bancaire",
                        "alice@mail.com",
                        20
                );

        // Création du passager
        Passager passager =
                new Passager(
                        "Jeanne",
                        "PA123456",
                        25,
                        "0600000000"
                );

        // On ouvre le vol aux réservations
        volAvecEscale.ouvrir();

        // Création de la réservation
        Reservation reservation = volAvecEscale.creerReservation(client, passager);

        System.out.println("Réservation créée : " + reservation);

        System.out.println("Etat initial : " + reservation.getEtat().getNom());

        // Pattern State : Initiale -> Payée
        reservation.payer();

        System.out.println("Etat après paiement : " + reservation.getEtat().getNom());

        // Pattern State : Payée -> Confirmée
        reservation.confirmer();

        System.out.println("Etat après confirmation : " + reservation.getEtat().getNom());

        System.out.println("Nombre de réservations sur le vol : " + volAvecEscale.getReservations().size());

        System.out.println();

        System.out.println("================================");
        System.out.println("        TESTS D'ERREURS");
        System.out.println("================================");

        // Test : impossible de réserver un vol fermé
        try {
            Vol volFerme = airFrance.creerVol(departDirect, arriveeDirect);

            volFerme.creerReservation(client, passager);

        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue vol fermé : "
                    + e.getMessage());
        }

        // Test : impossible de confirmer une réservation initiale
        try {
            Vol autreVol = airFrance.creerVol(departDirect, arriveeDirect);
            autreVol.ouvrir();

            Reservation reservationNonPayee = autreVol.creerReservation(client, passager);
            reservationNonPayee.confirmer();

        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue confirmation sans paiement : "
                    + e.getMessage());
        }

        // Test : impossible de payer une réservation annulée
        try {
            Vol autreVol =
                    airFrance.creerVol(
                            departDirect,
                            arriveeDirect
                    );

            autreVol.ouvrir();

            Reservation reservationAnnulee =
                    autreVol.creerReservation(
                            client,
                            passager
                    );

            reservationAnnulee.annuler();

            reservationAnnulee.payer();

        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue paiement après annulation : "
                    + e.getMessage());
        }
    }
}