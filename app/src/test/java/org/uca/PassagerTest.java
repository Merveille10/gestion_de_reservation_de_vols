package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.reservation.Passager;

import static org.junit.jupiter.api.Assertions.*;

public class PassagerTest {

    // On vérifie la création correcte d'un passager
    @Test
    public void testCreationPassager() {

        Passager passager =
                new Passager(
                        "Bob",
                        "PB123456",
                        30,
                        "0611111111"
                );

        assertEquals(
                "Bob",
                passager.getNom()
        );

        assertEquals(
                "PB123456",
                passager.getNumeroPasseport()
        );

        assertEquals(
                30,
                passager.getAge()
        );

        assertEquals(
                "0611111111",
                passager.getTelephone()
        );
    }

    // on vérifie qu'un âge négatif provoque une exception
    @Test
    public void testAgeNegatif() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Passager(
                        "Bob",
                        "PB123456",
                        -1,
                        "0611111111"
                )
        );
    }

    // On vérifie qu'un passeport vide provoque une exception
    @Test
    public void testPasseportVide() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Passager(
                        "Bob",
                        "",
                        30,
                        "0611111111"
                )
        );
    }
}