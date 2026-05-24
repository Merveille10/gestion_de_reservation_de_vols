package org.uca.aeroport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Compagnie {

    // Nom de la compagnie
    private String nom;

    // Liste des vols de la compagnie
    private List<Vol> vols = new ArrayList<>();

    // Générateur de numéros de vols
    private GenerateurNumeroVol GenerateurNumeroVol;

    public Compagnie(
            String nom,
            GenerateurNumeroVol GenerateurNumeroVol
    ) {

        // Vérifie le nom
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom ne peut pas être vide"
            );
        }

        // Vérifie le générateur
        if (GenerateurNumeroVol == null) {
            throw new IllegalArgumentException(
                    "Le générateur ne peut pas être null"
            );
        }

        this.nom = nom;
        this.GenerateurNumeroVol = GenerateurNumeroVol;
    }

    public String getNom() {
        return nom;
    }

    // Retourne une vue non modifiable des vols
    public List<Vol> getVols() {
        return Collections.unmodifiableList(vols);
    }

    public GenerateurNumeroVol getGenerateurNumeroVol() {
        return GenerateurNumeroVol;
    }

    // Crée un nouveau vol
    public Vol creerVol(
            Etape depart,
            Etape arrivee
    ) {

        String numero = GenerateurNumeroVol.next();

        Vol vol = new Vol(
                numero,
                depart,
                arrivee
        );

        addVol(vol);

        return vol;
    }

    // Ajoute un vol à la compagnie
    public void addVol(Vol vol) {

        // Vérifie que le vol existe
        if (vol == null) {
            throw new IllegalArgumentException(
                    "Le vol ne peut pas être null"
            );
        }

        // Évite les doublons
        if (!vols.contains(vol)) {

            vols.add(vol);

            // Met à jour le lien inverse
            vol.setCompagnieWithoutBidirectional(this);
        }
    }

    // Supprime un vol
    public void removeVol(Vol vol) {

        // Vérifie que le vol existe
        if (vol == null) {
            throw new IllegalArgumentException(
                    "Le vol ne peut pas être null"
            );
        }

        if (vols.remove(vol)) {

            // Supprime le lien inverse
            vol.setCompagnieWithoutBidirectional(null);
        }
    }

    // Méthodes utilisées pour éviter
    // les boucles infinies
    protected void addVolWithoutBidirectional(
            Vol vol
    ) {
        vols.add(vol);
    }

    protected void removeVolWithoutBidirectional(
            Vol vol
    ) {
        vols.remove(vol);
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Compagnie that)) {
            return false;
        }

        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}