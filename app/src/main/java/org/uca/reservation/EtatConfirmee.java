package org.uca.reservation;

public class EtatConfirmee extends EtatReservation {

    // Une réservation confirmée peut être annulée
    @Override
    public EtatReservation annuler() {
        return new EtatAnnulee();
    }

    @Override
    public String getNom() {
        return "Confirmée";
    }
}