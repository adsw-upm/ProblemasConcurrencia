package es.upm.dit.adsw.DespegueAviones;

/**
 * Created by aalonso on 29/3/17.
 */

import java.util.Random;

public class Avion extends Thread {

    private GestorDespegue gestor;
    private int retardo          = 0;  // segundos
    private int retardoAleatorio = 10; // segundos
    private int id;
    private Random random;

    public Avion (GestorDespegue gestor, int retardo, int id) {
        this.gestor  = gestor;
        this.retardo = retardo;
        this.id      = id;

        // Genero algo de aleatorio
        random = new Random(retardo);
    }

    public void run() {

        try {
            // Espera un retardo
            Thread.sleep(retardo * 1000);
            // Espera un retardo aleatorio antes de despegar
            Thread.sleep(random.nextInt(retardoAleatorio)*1000);
            System.out.println("!!! Avion     id: " + id + " empieza a despegar");

            gestor.despegarAvionVIP();
            System.out.println("--- Avion     id: " + id + " ha despegado");

        } catch (InterruptedException e) {
            return;
        }

    }
}
