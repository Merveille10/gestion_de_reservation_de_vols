package org.uca.reservation;

public class EtatAnnulee extends EtatReservation {

    // Etat final : on ne redéfinit pas payer/confirmer/annuler car une réservation annulée ne peut plus changer d'état

    @Override
    public String getNom() {
        return "Annulée";
    }
}