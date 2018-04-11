package es.upm.dit.adsw.buffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Buffer con capacidad para varios elementos
 * 
 * @author jpuente
 * @version 07.04.2016
 */
public class BufferMultiple <E> implements Buffer<E> {

	private final E[] almacen;
	private final int nmax;       // tamaño del buffer
	private int n = 0;           // número de elementos almacenados
	private int in = 0;
	private int out = 0;

	private int nIn, nOut = 0;
	
	/**
	 * Crear un nuevo buffer
	 * 
	 * @param n tamaño del buffer
	 */
	@SuppressWarnings("unchecked")
	public BufferMultiple (int n) {
		this.nmax = n;
		this.almacen = (E[]) new Object[n];
	}

	public synchronized void enviar(E dato) {
		try {
			while (n >= nmax) {
				System.out.println("productor suspendido");
				wait(); // espera que haya sitio
			}
		} catch (InterruptedException ignored) {
		}
		almacen[in] = dato;
		nIn++;
		in = ++in % nmax;
		n++;
		notifyAll(); // avisa de que hay un valor
		assert (0 <= n && n <= nmax && n == nIn -nOut) : "n = " + n + " nIn = " + nIn + " nOut = " + nOut;
	}

	public synchronized E recibir() {
		E dato = null;
		try {
			while (n <= 0) {
				System.out.println("consumidor suspendido");
				wait(); // espera que haya un valor
			}
		} catch (InterruptedException ignored) {
		}
		dato = almacen[out];
		nOut++;
		out = ++out % nmax;
		n--;
		notifyAll(); // avisa de que hay sitio
		assert (0 <= n && n <= nmax && n == nIn -nOut) : "n = " + n + " nIn = " + nIn + " nOut = " + nOut;
		return dato;
	}

}
