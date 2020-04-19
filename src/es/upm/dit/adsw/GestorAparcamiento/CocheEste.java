package es.upm.dit.adsw.GestorAparcamiento; 


public class CocheEste extends Thread{
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idCoche;
	private GestorAparcamiento unGestor;
	private long retardoInicial;
	private int  retardoMax = 10000;

	public CocheEste (GestorAparcamiento unGestor, int idCoche, long retardoInicial) {
		this.idCoche        = idCoche;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.entraCochePorEste(idCoche);
			Thread.sleep(generador.nextInt(retardoMax));
			unGestor.saleCoche(idCoche);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
			System.out.println(e.toString());
		}

	}

}
