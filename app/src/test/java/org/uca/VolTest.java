package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.*;
import org.uca.reservation.Client;
import org.uca.reservation.Passager;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VolTest {

    // Teste la création d'un vol
    @Test
    public void testCreationVol() {

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
                        LocalDateTime.of(
                                2026,
                                6,
                                10,
                                14,
                                0
                        )
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        LocalDateTime.of(
                                2026,
                                6,
                                11,
                                10,
                                0
                        )
                );

        Vol vol =
                new Vol(
                        "VOL-1",
                        depart,
                        arrivee
                );

        assertEquals("VOL-1", vol.getNumero());

        assertEquals(depart, vol.getDepart());

        assertEquals(arrivee, vol.getArrivee());
    }

    // Teste l'ouverture du vol
    @Test
    public void testOuvertureVol() {

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
                        LocalDateTime.now().plusHours(10)
                );

        Vol vol =
                new Vol(
                        "VOL-2",
                        depart,
                        arrivee
                );

        assertFalse(vol.estOuvert());

        vol.ouvrir();

        assertTrue(vol.estOuvert());

        vol.fermer();

        assertFalse(vol.estOuvert());
    }

    // Vérifie qu'une réservation
    // est impossible si le vol est fermé
    @Test
    public void testReservationVolFerme() {

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
                        LocalDateTime.now().plusHours(10)
                );

        Vol vol =
                new Vol(
                        "VOL-3",
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

        assertThrows(
                IllegalStateException.class,
                () -> vol.creerReservation(
                        client,
                        passager
                )
        );
    }

    // Vérifie le décalage d'un vol
    @Test
    public void testDecalageVol() 
    {
        Aeroport paris =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport dubai =
                new Aeroport(
                        "Dubai International",
                        "Dubai"
                );

        Aeroport tokyo =
                new Aeroport(
                        "Haneda",
                        "Tokyo"
                );

        LocalDateTime dateDepart =
                LocalDateTime.of(
                        2026,
                        6,
                        10,
                        14,
                        0
                );

        LocalDateTime dateEscale =
                dateDepart.plusHours(6);

        LocalDateTime dateArrivee =
                dateDepart.plusHours(12);

        Etape depart =
                new Etape(
                        paris,
                        dateDepart
                );

        Etape arrivee =
                new Etape(
                        tokyo,
                        dateArrivee
                );

        Escale escale =
                new Escale(
                        dubai,
                        dateEscale,
                        Duration.ofHours(2)
                );

        Vol vol =
                new Vol(
                        "VOL-DECALAGE",
                        depart,
                        arrivee
                );

        vol.ajouterEscale(escale);

        vol.decaler(Duration.ofHours(2));

        assertEquals(
                dateDepart.plusHours(2),
                vol.getDepart().getDate()
        );

        assertEquals(
                dateArrivee.plusHours(2),
                vol.getArrivee().getDate()
        );

        assertEquals(
                dateEscale.plusHours(2),
                vol.getEscales().get(0).getDate()
        );
        }
}