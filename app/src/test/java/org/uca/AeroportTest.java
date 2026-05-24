package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.Aeroport;

import static org.junit.jupiter.api.Assertions.*;

public class AeroportTest {

    // Vérifie la création correcte d'un aéroport
    @Test
    public void testCreationAeroport() {

        Aeroport aeroport =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        assertEquals(
                "Charles de Gaulle",
                aeroport.getNom()
        );

        assertEquals(
                "Paris",
                aeroport.getVille()
        );
    }

    // Vérifie qu'un nom vide
    // provoque une exception
    @Test
    public void testNomVide() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Aeroport(
                        "",
                        "Paris"
                )
        );
    }

    // Vérifie qu'une ville vide
    // provoque une exception
    @Test
    public void testVilleVide() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Aeroport(
                        "Charles de Gaulle",
                        ""
                )
        );
    }

    // Vérifie que deux aéroports identiques
    // sont considérés comme égaux
    @Test
    public void testEqualsAeroport() {

        Aeroport aeroport1 =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        Aeroport aeroport2 =
                new Aeroport(
                        "Charles de Gaulle",
                        "Paris"
                );

        assertEquals(
                aeroport1,
                aeroport2
        );
    }
}