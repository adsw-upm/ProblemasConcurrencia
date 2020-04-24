package es.upm.dit.adsw.DespegueAviones;


/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen. LPRS junio 2013
 */

public class GestorDespegue {

    private boolean      pistaOcupada;
    private int          nVIPEsperando;
    private int          nEsperando;
    private final int    tiempoAvion = 3;
    private boolean      anteriorVIP;
    private Temporizador unTemporizador; 

    public GestorDespegue() {
        pistaOcupada   = false;
        nVIPEsperando  = 0;
        nEsperando     = 0;
        anteriorVIP    = false;
        unTemporizador = new Temporizador(this);

    }
    
    public synchronized void despegarAvion(int id) throws InterruptedException {

        nEsperando ++;

        if (pistaOcupada || (nEsperando > 0 && !anteriorVIP)) {
           System.out.println("### Avion     id: " + id + " está bloqueado");            
        }

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

    public synchronized void  despegarAvionVIP(int id) throws InterruptedException {
        nVIPEsperando++;

        if (pistaOcupada || (nEsperando > 0 && anteriorVIP)) {
           System.out.println("*** Avion Vip id: " + id + " está bloqueado");            
        }


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
        System.out.println("Expira el temporizador. Despegue seguro");
        notifyAll();
    }
}
