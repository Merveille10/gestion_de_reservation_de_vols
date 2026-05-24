package org.uca.reservation;

public class EtatPayee extends EtatReservation {

    // Une réservation payée peut être confirmée
    @Override
    public EtatReservation confirmer() {
        return new EtatConfirmee();
    }

    // Une réservation payée peut aussi être annulée
    @Override
    public EtatReservation annuler() {
        return new EtatAnnulee();
    }

    @Override
    public String getNom() {
        return "Payée";
    }
}