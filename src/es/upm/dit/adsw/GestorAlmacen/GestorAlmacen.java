package es.upm.dit.adsw.GestorAlmacen; 

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */
public class GestorAlmacen {

	private int cantidadAlmacen = 0;
	private boolean peticionPendiente = false;
	
	public synchronized void solicitarPiezas(int cantidadPiezas, int idProductor)
			throws InterruptedException {
		while (peticionPendiente) {
			System.out.println("++++++++ Se bloquea un Productor (alguno ocupado) " + idProductor );
			wait();
		}
		peticionPendiente = true;
		while (cantidadAlmacen < cantidadPiezas) { 
			System.out.println("******** Se bloquea un Productor (faltan fiezas) " + idProductor 
					+ " Hay: " + cantidadAlmacen + " Se necesitan: " + cantidadPiezas);
			wait();
		}
		cantidadAlmacen = cantidadAlmacen - cantidadPiezas;
		peticionPendiente = false;
		notifyAll();
		System.out.println(">>>>>>> Sale un Productor " + idProductor);
	}
	public synchronized void agregarPiezas(int cantidadPiezas, int idConstructor)
			throws InterruptedException {
		cantidadAlmacen = cantidadAlmacen + cantidadPiezas;
		notifyAll();
		System.out.println("<<<<<<<< Sale un consumidor " + idConstructor
					+ " Agrega " + cantidadPiezas);
	}
}