package es.upm.dit.adsw.GestorMuseo;


public class Persona extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idPersona;
	private GestorSalaClase unGestor;
	private int retardoInicial;
	private int  retardoMax = 10000;

	public Persona (GestorSalaClase unGestor, int idPersona, int retardoInicial) {
		this.idPersona        = idPersona;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarSala(idPersona);
			Thread.sleep(generador.nextInt(retardoMax));
			unGestor.salirSala(idPersona);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}

}


