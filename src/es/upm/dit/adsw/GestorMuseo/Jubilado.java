package es.upm.dit.adsw.GestorMuseo;


public class Jubilado extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idJubilado;
	private GestorSalaClase unGestor;
	private int retardoInicial;
	private int  retardoMax = 10000;

	public Jubilado (GestorSalaClase unGestor, int idJubilado, int retardoInicial) {
		this.idJubilado     = idJubilado;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarSalaJubilado(idJubilado);
			Thread.sleep(generador.nextInt(retardoMax));
			unGestor.salirSala(idJubilado);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}

}