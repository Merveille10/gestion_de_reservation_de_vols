package org.uca;

import org.junit.jupiter.api.Test;
import org.uca.aeroport.Money;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    // Vérifie la création correcte d'un prix
    @Test
    public void testCreationMoney() {

        Money money =
                new Money(
                        BigDecimal.valueOf(150),
                        "EUR"
                );

        assertEquals(
                BigDecimal.valueOf(150),
                money.getMontant()
        );

        assertEquals(
                "EUR",
                money.getDevise()
        );
    }

    // Vérifie qu'un montant négatif
    // provoque une exception
    @Test
    public void testMontantNegatif() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Money(
                        BigDecimal.valueOf(-10),
                        "EUR"
                )
        );
    }

    // Vérifie qu'une devise vide
    // provoque une exception
    @Test
    public void testDeviseVide() {

        assertThrows(
                IllegalArgumentException.class,

                () -> new Money(
                        BigDecimal.valueOf(100),
                        ""
                )
        );
    }
}