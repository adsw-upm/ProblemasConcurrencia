package es.upm.dit.adsw.GestorMagnitudFisica;

/**
 * @author Alejandro Alonso
 * @since  20100628
 * Recopilado de solución de examen.
 */


public class hebraGestora extends java.lang.Thread {
	
	private gestorHebras elGestor;
//	private boolean cortePelo = false;
	
	public hebraGestora(gestorHebras elGestor){
		this.elGestor = elGestor;
		this.start();
	}
	
	public void run(){
				
		while (true) {
		
			try{
				Thread.sleep(2000);
				elGestor.inicioCiclo();
				Thread.sleep(10000);
				elGestor.finCiclo();
			
			} catch (InterruptedException e){};
		}
	}
}

