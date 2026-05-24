package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompagnieTest {

    // Vérifie l'ajout d'un vol
    @Test
    public void testAjoutVol() {

        Compagnie compagnie =
                new Compagnie(
                        "Air France",
                        new GenerateurNumeroVol()
                );

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

        compagnie.addVol(vol);

        // Vérifie que le vol est ajouté
        assertTrue(
                compagnie.getVols().contains(vol)
        );

        // Vérifie la double navigabilité
        assertEquals(
                compagnie,
                vol.getCompagnie()
        );
    }

    // Vérifie la suppression d'un vol
    @Test
    public void testSuppressionVol() {

        Compagnie compagnie =
                new Compagnie(
                        "Air France",
                        new GenerateurNumeroVol()
                );

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

        compagnie.addVol(vol);

        compagnie.removeVol(vol);

        // Vérifie que le vol a disparu
        assertFalse(
                compagnie.getVols().contains(vol)
        );

        // Vérifie que le lien inverse est supprimé
        assertNull(
                vol.getCompagnie()
        );
    }

    // Vérifie la génération automatique
    // des numéros de vols
    @Test
    public void testGenerationNumeroVol() {

        Compagnie compagnie =
                new Compagnie(
                        "Air France",
                        new GenerateurNumeroVol()
                );

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
                compagnie.creerVol(
                        depart,
                        arrivee
                );

        // Vérifie que le numéro existe
        assertNotNull(
                vol.getNumero()
        );

        // Vérifie que le vol appartient
        // à la compagnie
        assertEquals(
                compagnie,
                vol.getCompagnie()
        );
    }
}