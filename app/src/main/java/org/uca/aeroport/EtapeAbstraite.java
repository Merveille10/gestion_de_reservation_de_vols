package org.uca.aeroport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class EtapeAbstraite {

    // Aéroport associé à l'étape
    private Aeroport aeroport;

    // Date de l'étape
    private LocalDateTime date;

    public EtapeAbstraite(Aeroport aeroport, LocalDateTime date) {

        // Vérifie que l'aéroport existe
        if (aeroport == null) {
            throw new IllegalArgumentException(
                    "L'aéroport ne peut pas être null"
            );
        }

        // Vérifie que la date existe
        if (date == null) {
            throw new IllegalArgumentException(
                    "La date ne peut pas être null"
            );
        }

        this.aeroport = aeroport;
        this.date = date;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // Décale la date de l'étape
    public void decaler(Duration duree) {

        if (duree == null) {
            throw new IllegalArgumentException(
                    "La durée ne peut pas être null"
            );
        }

        date = date.plus(duree);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof EtapeAbstraite that)) {
            return false;
        }

        return Objects.equals(aeroport, that.aeroport)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aeroport, date);
    }

    @Override
    public String toString() {
        return aeroport + " - " + date;
    }
}