package org.uca.reservation;

public class EtatInitial extends EtatReservation {

    // Depuis l'état initial, on peut payer
    @Override
    public EtatReservation payer() {
        return new EtatPayee();
    }

    // Depuis l'état initial, on peut annuler
    @Override
    public EtatReservation annuler() {
        return new EtatAnnulee();
    }

    @Override
    public String getNom() {
        return "Initiale";
    }
}