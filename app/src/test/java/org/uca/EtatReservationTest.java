package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.*;
import org.uca.reservation.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EtatReservationTest {

    // Crée une réservation de test
    private Reservation creerReservationTest() {

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
                        "VOL-STATE",
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

        return vol.creerReservation(
                client,
                passager
        );
    }

    // Vérifie le passage Initiale -> Payée
    @Test
    public void testPaiementReservation() {

        Reservation reservation =
                creerReservationTest();

        reservation.payer();

        assertEquals(
                "Payée",
                reservation.getEtat().getNom()
        );
    }

    // Vérifie le passage Initiale -> Payée -> Confirmée
    @Test
    public void testConfirmationReservation() {

        Reservation reservation =
                creerReservationTest();

        reservation.payer();

        reservation.confirmer();

        assertEquals(
                "Confirmée",
                reservation.getEtat().getNom()
        );
    }

    // Vérifie qu'on ne peut pas confirmer
    // une réservation non payée
    @Test
    public void testConfirmationSansPaiement() {

        Reservation reservation =
                creerReservationTest();

        assertThrows(
                IllegalStateException.class,
                reservation::confirmer
        );
    }

    // Vérifie qu'une réservation annulée
    // ne peut plus être payée
    @Test
    public void testPaiementApresAnnulation() {

        Reservation reservation =
                creerReservationTest();

        reservation.annuler();

        assertEquals(
                "Annulée",
                reservation.getEtat().getNom()
        );

        assertThrows(
                IllegalStateException.class,
                reservation::payer
        );
    }

    // Vérifie qu'une réservation confirmée
    // peut être annulée
    @Test
    public void testAnnulationApresConfirmation() {

        Reservation reservation =
                creerReservationTest();

        reservation.payer();

        reservation.confirmer();

        reservation.annuler();

        assertEquals(
                "Annulée",
                reservation.getEtat().getNom()
        );
    }

    // Pour vérifier qu'une réservation ne peut pas être payée deux fois
     @Test
          public void testPaiementDeuxFoisImpossible() {

          Reservation reservation =
                    creerReservationTest();

          reservation.payer();

          assertEquals(
                    "Payée",
                    reservation.getEtat().getNom()
          );

          assertThrows(
                    IllegalStateException.class,
                    reservation::payer
          );
     }

     // Vérifie qu'une réservation annulée ne peut pas être confirmée
     @Test
     public void testConfirmationApresAnnulationImpossible() {

     Reservation reservation =
               creerReservationTest();

     reservation.annuler();

     assertEquals(
               "Annulée",
               reservation.getEtat().getNom()
     );

     assertThrows(
               IllegalStateException.class,
               reservation::confirmer
     );
     }
}