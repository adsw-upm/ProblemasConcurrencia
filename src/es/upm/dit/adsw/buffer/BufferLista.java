package es.upm.dit.adsw.buffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Buffer con capacidad para varios elementos
 *
 * @author jpuente
 * @version 07.04.2016
 */
public class BufferLista<E> implements Buffer<E> {

    private final List<E> almacen;
    private int nmax;            // tamaño del buffer
    private int n = 0;               // número de elementos almacenados
    private int in = 0;
    private int out = 0;

    private int nIn, nOut = 0;


    /**
     * Crear un nuevo buffer
     *
     * @param n tamaño del buffer
     */
    @SuppressWarnings("unchecked")
    public BufferLista(int n) {
        this.nmax = n;
        this.almacen = new ArrayList<>();
    }

    public synchronized void enviar(E dato) {
        if (almacen.size() > nmax)
            throw new IllegalStateException();
        try {
            while (almacen.size() >= nmax) {
                //System.out.println("productor suspendido");
                wait(); // espera que haya sitio
            }
        } catch (InterruptedException ignored) {
        }
        almacen.add(dato);
        notifyAll(); // avisa de que hay un valor
    }

    public synchronized E recibir() {
        if (almacen.size() > nmax)
            throw new IllegalStateException();
        try {
            while (almacen.size() <= 0) {
                //System.out.println("consumidor suspendido");
                wait(); // espera que haya un valor
            }
        } catch (InterruptedException ignored) {
        }
        notifyAll(); // avisa de que hay sitio
        return almacen.remove(0);
    }

}
