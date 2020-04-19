package es.upm.dit.adsw.GestorAlmacen; 


public class Productor extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idProductor;
	private GestorAlmacen unGestor;
	private int retardoInicial;
	private int cantidadPiezasMax = 5;

	public Productor (GestorAlmacen unGestor, int idProductor, int retardoInicial) {
		this.idProductor    = idProductor;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.solicitarPiezas(generador.nextInt(cantidadPiezasMax), idProductor);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}

}