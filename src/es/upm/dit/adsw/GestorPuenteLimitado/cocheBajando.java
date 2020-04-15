package es.upm.dit.adsw.GestorPuenteLimitado;

/**
 * @author Alejandro Alonso
 * @since 20060525
**/

public class cocheBajando extends Thread{
		
	private int idCoche;
	private gestorPuente unGestor;
	private long retardoInicial;
		
	public cocheBajando (gestorPuente unGestor, int idCoche, long retardoInicial) {
		this.idCoche  = idCoche;
		this.unGestor = unGestor;
		this.retardoInicial = retardoInicial;
	}

	public void run(){
		
		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarBajando(idCoche);
			Thread.sleep(750);
			unGestor.salirBajando(idCoche);
		} catch (InterruptedException e) {}
		
	}
}
