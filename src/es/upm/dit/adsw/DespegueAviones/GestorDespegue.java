package es.upm.dit.adsw.DespegueAviones;


/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen. LPRS junio 2013
 */

public class GestorDespegue {

    private boolean      pistaOcupada   = false;
    private int          nVIPEsperando  = 0;
    private int          nEsperando     = 0;
    private final int    tiempoAvion    = 3;
    private boolean      anteriorVIP    = false;
    private Temporizador unTemporizador = new Temporizador(this);

    public synchronized void despegarAvion() throws InterruptedException {

        nEsperando ++;
        try {
            while (pistaOcupada || (nVIPEsperando > 0 && !anteriorVIP)) wait();
        } catch (InterruptedException e) {
            // Notificar al llamante que no se ha despegado el avion
            throw e;
        }

        // Salgo o no el avion, hay que decrementar los esperando
        nEsperando --;
        anteriorVIP = false;
        unTemporizador.iniciarTemporizador(tiempoAvion);
        pistaOcupada = true;
    }

    public synchronized void  despegarAvionVIP() throws InterruptedException {
        nVIPEsperando++;
        try {
            while (pistaOcupada || (nEsperando > 0 && anteriorVIP)) wait();
        } catch (InterruptedException e) {
            // Notificar al llamante que no se ha despegado el avion
            throw e;
        }

        // Salgo o no el avion, hay que decrementar los esperando
        nVIPEsperando--;
        anteriorVIP = true;
        unTemporizador.iniciarTemporizador(tiempoAvion);
        pistaOcupada = true;
    }

    public synchronized void  finTemporizador() throws InterruptedException {
        pistaOcupada = false;
        notifyAll();
    }
}
