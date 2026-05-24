package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.reservation.Client;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    // Vérifie la création correcte d'un client
    @Test
    public void testCreationClient() {

        Client client =
                new Client(
                        "Alice",
                        "Carte bancaire",
                        "alice@mail.com",
                        10
                );

        assertEquals(
                "Alice",
                client.getNom()
        );

        assertEquals(
                "Carte bancaire",
                client.getMoyenPaiement()
        );

        assertEquals(
                "alice@mail.com",
                client.getMail()
        );

        assertEquals(
                10,
                client.getPoints()
        );
    }

    // Vérifie qu'un client ne peut pas avoir
    // un nombre de points négatif
    @Test
    public void testPointsNegatifs() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Client(
                        "Alice",
                        "Carte bancaire",
                        "alice@mail.com",
                        -1
                )
        );
    }

    // Vérifie l'ajout de points
    @Test
    public void testAjouterPoints() {

        Client client =
                new Client(
                        "Alice",
                        "Carte bancaire",
                        "alice@mail.com",
                        0
                );

        client.ajouterPoints(20);

        assertEquals(
                20,
                client.getPoints()
        );
    }
}