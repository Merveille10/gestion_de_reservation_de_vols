package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.GenerateurNumeroVol;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurNumeroVolTest {

    // Vérifie que le générateur produit un numéro
    @Test
    public void testGenerationNumero() {

        GenerateurNumeroVol generateur =
                new GenerateurNumeroVol();

        String numero =
                generateur.next();

        assertNotNull(numero);

        assertFalse(
                numero.isBlank()
        );
    }

    // Vérifie que deux numéros générés
    // sont différents
    @Test
    public void testNumerosDifferents() {

        GenerateurNumeroVol generateur =
                new GenerateurNumeroVol();

        String numero1 =
                generateur.next();

        String numero2 =
                generateur.next();

        assertNotEquals(
                numero1,
                numero2
        );
    }

    // Vérifie le format simple des numéros
    @Test
    public void testFormatNumero() {

        GenerateurNumeroVol generateur =
                new GenerateurNumeroVol();

        String numero =
                generateur.next();

        assertTrue(
                numero.startsWith("VOL-")
        );
    }
}