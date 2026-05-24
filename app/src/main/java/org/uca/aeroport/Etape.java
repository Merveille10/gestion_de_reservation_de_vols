package org.uca.aeroport;

import java.time.LocalDateTime;

public class Etape extends EtapeAbstraite {

    public Etape(Aeroport aeroport, LocalDateTime date) {
        super(aeroport, date);
    }
}