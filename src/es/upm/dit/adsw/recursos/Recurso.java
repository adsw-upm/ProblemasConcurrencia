package es.upm.dit.adsw.recursos;

/**
 *  @author jpuente
 *  @version 2018.04.12
 */
public enum Recurso {
    R1, R2, R3;

    private boolean ocupado = false;

    public boolean ocupado() {return this.ocupado;}

    public void marcaOcupado(boolean to) {this.ocupado = to;}

}

