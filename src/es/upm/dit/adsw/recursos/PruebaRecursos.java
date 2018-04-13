package es.upm.dit.adsw.recursos;

/**
 * @author jpuente
 * @version 2018.04.13
 */
public class PruebaRecursos {

    public static void main (String[] args) {

        GestorRecursos gestor = new GestorRecursos();
        new Cliente("Cliente 1", Servicio.S1, gestor).start();
        new Cliente("Cliente 2", Servicio.S2, gestor).start();
        new Cliente("Cliente 3", Servicio.S3, gestor).start();

    }

}
