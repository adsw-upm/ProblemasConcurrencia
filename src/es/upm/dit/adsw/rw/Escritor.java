package es.upm.dit.adsw.rw;

/**
 * Escritor
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class Escritor extends Thread {

	private Gestor gestor;
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int id;
	private long retardoInicial;
	private int  retardoMax;

	public Escritor(Gestor gestor, int id, long retardoInicial) {
		this.gestor         = gestor;
		this.id             = id;
		this.retardoMax     = 5000;
		this.retardoInicial = retardoInicial;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(retardoInicial);
				gestor.empiezaEscribir(id);
				Thread.sleep(generador.nextInt(retardoMax));
				gestor.terminaEscribir(id);
			}
		} catch (InterruptedException ie) {
			return;
		}
	}

}
