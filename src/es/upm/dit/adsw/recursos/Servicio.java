package es.upm.dit.adsw.recursos;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jpuente
 * @version 2018.04.12
 */
public enum Servicio {

    S1 (EnumSet.of(Recurso.R1, Recurso.R2)),
    S2 (EnumSet.of(Recurso.R2, Recurso.R3)),
    S3 (EnumSet.of(Recurso.R3, Recurso.R1));

    private final EnumSet<Recurso> recursos;

    Servicio (EnumSet<Recurso> recursos) {
        this.recursos = recursos;
    }

    public EnumSet<Recurso> recursos() {
        return this.recursos;
    }
}
