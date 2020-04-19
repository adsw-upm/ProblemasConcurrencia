package es.upm.dit.adsw.GestorMuseo;

public class SensorTemperatura extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */

	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private GestorSalaClase unGestor;
	private int retardoInicial;
	private int retardoMax = 10000;
	private int nVeces;
	private int maxT = 45;

	public SensorTemperatura (GestorSalaClase unGestor, int nVeces, int retardoInicial) {
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.nVeces         = nVeces;
		this.start();
	}

	public void run(){
		try {
			Thread.sleep(retardoInicial);	
		} catch (Exception e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

		for (int i = 0; i < nVeces; i++) {

			try {
				unGestor.notificarTemperatura(generador.nextInt(maxT));
				Thread.sleep(generador.nextInt(retardoMax));
			} catch (InterruptedException e) {
			}
		}
	}
}
