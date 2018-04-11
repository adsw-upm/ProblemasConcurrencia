package es.upm.dit.adsw.buffer;

import java.util.Random;

/**
 * Consumidor. Recibe mensajes de texto.
 *
 * @author jpuente
 * @version 2018.04.10
 */
public class Consumidor<E> implements Runnable {

    private Buffer<E> buffer;
    private String id;

    private final Random random = new Random();

    public Consumidor(String id, Buffer<E> buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                String msg = buffer.recibir().toString();
                System.out.println("Consumidor " + id + " recibe " + msg);
                Thread.sleep(random.nextInt(3) * 1000);
            }
        } catch (Exception e){System.err.println(e.toString());}
    }
}
