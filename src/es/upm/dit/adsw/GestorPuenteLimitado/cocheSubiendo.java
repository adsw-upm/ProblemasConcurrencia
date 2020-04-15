package es.upm.dit.adsw.GestorPuenteLimitado;

/**
 * @author Alejandro Alonso
 * @since 20060525
**/

public class cocheSubiendo extends Thread{
	
	private int idCoche;
	private gestorPuente unGestor;
	private long retardoInicial;
	
	public cocheSubiendo (gestorPuente unGestor, int idCoche, long retardoInicial) {
		this.idCoche  = idCoche;
		this.unGestor = unGestor;
		this.retardoInicial = retardoInicial;
	}

	public void run(){
	
		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarSubiendo(idCoche);
			Thread.sleep(1000);
			unGestor.salirSubiendo(idCoche);
		} catch (InterruptedException e) {}
		
	}
	
}
