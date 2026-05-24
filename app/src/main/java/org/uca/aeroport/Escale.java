package org.uca.aeroport;

import java.time.Duration;
import java.time.LocalDateTime;

public class Escale extends EtapeAbstraite {

    // Durée de l'escale
    private Duration duree;

    public Escale(
            Aeroport aeroport,
            LocalDateTime date,
            Duration duree
    ) {

        super(aeroport, date);

        // Vérifie que la durée est positive
        if (duree == null
                || duree.isZero()
                || duree.isNegative()) {

            throw new IllegalArgumentException(
                    "La durée doit être positive"
            );
        }

        this.duree = duree;
    }

    public Duration getDuree() {
        return duree;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Escale : "
                + duree.toHours()
                + "h";
    }
}