package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.*;
import org.uca.reservation.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    // Vérifie la création correcte
    // d'une réservation
    @Test
    public void testCreationReservation() {

        Aeroport paris =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport tokyo =
                new Aeroport(
                        "Haneda",
                        "Tokyo"
                );

        Etape depart =
                new Etape(
                        paris,
                        LocalDateTime.now()
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        LocalDateTime.now().plusHours(12)
                );

        Vol vol =
                new Vol(
                        "VOL-1",
                        depart,
                        arrivee
                );

        Client client =
                new Client(
                        "Alice",
                        "Carte bancaire",
                        "alice@mail.com",
                        0
                );

        Passager passager =
                new Passager(
                        "Alice",
                        "PA123456",
                        25,
                        "0600000000"
                );

        vol.ouvrir();

        Reservation reservation =
                vol.creerReservation(
                        client,
                        passager
                );

        assertEquals(
                client,
                reservation.getClient()
        );

        assertEquals(
                passager,
                reservation.getPassager()
        );

        assertEquals(
                vol,
                reservation.getVol()
        );

        assertNotNull(
                reservation.getPrix()
        );

        assertEquals(
                "Initiale",
                reservation.getEtat().getNom()
        );
    }

    // Vérifie qu'une réservation
    // est ajoutée au vol
    @Test
    public void testReservationAjouteeAuVol() {

        Aeroport paris =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport tokyo =
                new Aeroport(
                        "Haneda",
                        "Tokyo"
                );

        Etape depart =
                new Etape(
                        paris,
                        LocalDateTime.now()
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        LocalDateTime.now().plusHours(12)
                );

        Vol vol =
                new Vol(
                        "VOL-2",
                        depart,
                        arrivee
                );

        Client client =
                new Client(
                        "Bob",
                        "Carte bancaire",
                        "bob@mail.com",
                        0
                );

        Passager passager =
                new Passager(
                        "Bob",
                        "PB123456",
                        30,
                        "0611111111"
                );

        vol.ouvrir();

        Reservation reservation =
                vol.creerReservation(
                        client,
                        passager
                );

        assertTrue(
                vol.getReservations()
                        .contains(reservation)
        );
    }

    // Vérifie qu'un client null
    // provoque une exception
    @Test
    public void testClientNull() {

        Aeroport paris =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport tokyo =
                new Aeroport(
                        "Haneda",
                        "Tokyo"
                );

        Etape depart =
                new Etape(
                        paris,
                        LocalDateTime.now()
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        LocalDateTime.now().plusHours(12)
                );

        Vol vol =
                new Vol(
                        "VOL-3",
                        depart,
                        arrivee
                );

        Passager passager =
                new Passager(
                        "Charlie",
                        "PC123456",
                        22,
                        "0622222222"
                );

        vol.ouvrir();

        assertThrows(
                IllegalArgumentException.class,

                () -> vol.creerReservation(
                        null,
                        passager
                )
        );
    }

    // Vérifie qu'un passager null
    // provoque une exception
    @Test
    public void testPassagerNull() {

        Aeroport paris =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport tokyo =
                new Aeroport(
                        "Haneda",
                        "Tokyo"
                );

        Etape depart =
                new Etape(
                        paris,
                        LocalDateTime.now()
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        LocalDateTime.now().plusHours(12)
                );

        Vol vol =
                new Vol(
                        "VOL-4",
                        depart,
                        arrivee
                );

        Client client =
                new Client(
                        "Charlie",
                        "Carte bancaire",
                        "charlie@mail.com",
                        0
                );

        vol.ouvrir();

        assertThrows(
                IllegalArgumentException.class,

                () -> vol.creerReservation(
                        client,
                        null
                )
        );
    }
}