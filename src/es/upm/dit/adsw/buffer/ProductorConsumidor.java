package es.upm.dit.adsw.buffer;

import java.util.List;

/**
 * Prueba de productor y consumidor
 *
 * @author jpuente
 * @version 2018.04.10
 */
public class ProductorConsumidor {

    public static void main(String[] args) {
//		Buffer<String> buffer = new BufferSimple<String>();
        Buffer<String> buffer = new BufferMultiple<String>(5);
//		Buffer<String> buffer = new BufferLista<String>(5);

        Productor<String> productor1 = new Productor<String>("p1", buffer, 1000);
        Productor<String> productor2 = new Productor<String>("p2", buffer, 2000);
        Consumidor<String> consumidor = new Consumidor<String>("c", buffer);
        Thread p1 = new Thread(productor1);
        Thread p2 = new Thread(productor2);
        Thread c = new Thread(consumidor);
        p1.start();
        p2.start();
        c.start();
    }
}
