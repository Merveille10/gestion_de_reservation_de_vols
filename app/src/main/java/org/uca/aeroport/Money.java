package org.uca.aeroport;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal montant;

    private String devise;

    public Money(BigDecimal montant, String devise) {
        if (montant == null || montant.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Le montant doit être positif"
            );
        }

        if (devise == null || devise.isBlank()) {
            throw new IllegalArgumentException(
                "La devise ne peut pas être vide"
            );
        }

        this.montant = montant;
        this.devise = devise;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getDevise() {
        return devise;
    }

    @Override
    public String toString() {
        return montant + " " + devise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;

        return Objects.equals(montant, money.montant)
                && Objects.equals(devise, money.devise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant, devise);
    }
}