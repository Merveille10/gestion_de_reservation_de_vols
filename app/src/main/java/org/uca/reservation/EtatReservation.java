package org.uca.reservation;

import java.time.LocalDateTime;

public abstract class EtatReservation {

    // Pattern State utilisé pour gérer les états d'une réservation
    private final LocalDateTime date;

    public EtatReservation() {
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    // Par défaut, payer n'est pas autorisé
    public EtatReservation payer() {
        throw new IllegalStateException(
                "Impossible de payer dans cet état"
        );
    }

    // Par défaut, confirmer n'est pas autorisé
    public EtatReservation confirmer() {
        throw new IllegalStateException(
                "Impossible de confirmer dans cet état"
        );
    }

    // Par défaut, annuler n'est pas autorisé
    public EtatReservation annuler() {
        throw new IllegalStateException(
                "Impossible d'annuler dans cet état"
        );
    }

    // Permet d'afficher simplement le nom de l'état
    public abstract String getNom();
}