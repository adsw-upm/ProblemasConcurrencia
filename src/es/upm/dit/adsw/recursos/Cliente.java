package es.upm.dit.adsw.recursos;

import java.util.Random;

/**
 * @author jpuente
 * @version 2018.04.13
 */
public class Cliente extends Thread {

    private final String id;
    private final GestorRecursos gestor;
    private final Servicio servicio;

    private final Random random = new Random();

    public Cliente (String id, Servicio servicio, GestorRecursos gestor) {
        this.id = id;
        this.gestor = gestor;
        this.servicio = servicio;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep (random.nextInt(2)*5000);
                System.out.println(id + " solicita " + servicio.toString());
                gestor.solicitar(servicio);
                sleep (random.nextInt(1)+1000);
                gestor.terminar(servicio);
                System.out.println(id + " acaba " + servicio.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
