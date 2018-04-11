package es.upm.dit.adsw.buffer;

import java.util.Random;

/**
 * Productor que envía mensajes de texto.
 *
 * @author jpuente
 * @version 2018.04.10
 */
public class Productor<E> implements Runnable {

    private Buffer<E> buffer;
    private String id;
    private int n = 0;

    private final Random random = new Random();

    public Productor(String id, Buffer<E> buffer, int n0) {
        this.id = id;
        this.buffer = buffer;
        this.n = n0;
    }

    public void run() {
        while (true) {
            try {
                E msg = (E) String.valueOf(n++);
                buffer.enviar(msg);
                System.out.println("Productor " + id + " envía " + msg);
                Thread.sleep(random.nextInt(3) * 1000);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
}

