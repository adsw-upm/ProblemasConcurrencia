package es.upm.dit.adsw.rw;

// Actualizado el 21.04.2020
// Ejemplo de interbloqueo y hambruna

/**
 * Lector
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class Lector extends Thread {

	private Gestor gestor;
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int id;
	private long retardoInicial;
	private int  retardoMax;

	public Lector(Gestor gestor, int id, long retardoInicial) {
		this.gestor         = gestor;
		this.id             = id;
		this.retardoMax     = 5000;// Poned 10000 para el caso de hambruna
		this.retardoInicial = retardoInicial;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(retardoInicial);
				gestor.empiezaLeer(id);
				Thread.sleep(generador.nextInt(retardoMax));
				gestor.terminaLeer(id);
			}
		} catch (InterruptedException ie) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!");
			return;
		}
	}

}
