package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.Aeroport;
import org.uca.aeroport.Escale;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EscaleTest {

    // Vérifie la création correcte d'une escale
    @Test
    public void testCreationEscale() {

        Aeroport dubai =
                new Aeroport(
                        "Dubai International",
                        "Dubai"
                );

        Escale escale =
                new Escale(
                        dubai,
                        LocalDateTime.now(),
                        Duration.ofHours(2)
                );

        assertEquals(
                dubai,
                escale.getAeroport()
        );

        assertEquals(
                Duration.ofHours(2),
                escale.getDuree()
        );
    }

    // Vérifie qu'une durée négative
    // provoque une exception
    @Test
    public void testDureeNegative() {

        Aeroport dubai =
                new Aeroport(
                        "Dubai International",
                        "Dubai"
                );

        assertThrows(
                IllegalArgumentException.class,

                () -> new Escale(
                        dubai,
                        LocalDateTime.now(),
                        Duration.ofHours(-2)
                )
        );
    }

    // Vérifie qu'une durée nulle
    // provoque une exception
    @Test
    public void testDureeNulle() {

        Aeroport dubai =
                new Aeroport(
                        "Dubai International",
                        "Dubai"
                );

        assertThrows(
                IllegalArgumentException.class,

                () -> new Escale(
                        dubai,
                        LocalDateTime.now(),
                        Duration.ZERO
                )
        );
    }
}