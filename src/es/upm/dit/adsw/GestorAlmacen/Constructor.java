package es.upm.dit.adsw.GestorAlmacen; 


public class Constructor extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idConstructor;
	private GestorAlmacen unGestor;
	private int retardoInicial;
	private int cantidadPiezasMax = 5;

	public Constructor (GestorAlmacen unGestor, int idConstructor, int retardoInicial) {
		this.idConstructor        = idConstructor;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.agregarPiezas(generador.nextInt(cantidadPiezasMax), idConstructor);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}

}


