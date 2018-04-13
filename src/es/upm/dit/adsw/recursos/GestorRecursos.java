package es.upm.dit.adsw.recursos;

import java.util.EnumSet;

/**
 * @author jpuente
 * @version 2018.04.13
 */
public class GestorRecursos {

    private boolean ocupados (EnumSet<Recurso> recursos) {

        boolean ocupados = false;
        for (Recurso r : recursos) {
            if ( r.ocupado() ) {
                ocupados = true;
            }
        }
        return ocupados;
    }

    public synchronized void solicitar (Servicio servicio) {

        try {
            while ( ocupados( servicio.recursos() ) )
                wait();
        } catch (InterruptedException e) {
            return;
        }
        for (Recurso r : servicio.recursos())
            r.marcaOcupado(true);
        System.out.println("Servicio " + servicio.toString() + " empezado");
    }

    public synchronized void terminar(Servicio servicio) {
        for (Recurso r : servicio.recursos())
            r.marcaOcupado(false);
        notifyAll();
        System.out.println("Servicio " + servicio.toString() + " acabado");
    }

}
