package es.upm.dit.adsw.GestorMagnitudFisica;

/**
 * @author Alejandro Alonso
 * @since  20100628
 * Recopilado de solución de examen.
 */

public class hebraLectora extends java.lang.Thread {
	
	private gestorHebras elGestor;
	private int clienteId;
	private enteroAleatorio generadorEnteros;
//	private boolean cortePelo = false;
	
	public hebraLectora(gestorHebras elGestor, enteroAleatorio elGenerador, int clienteId){
		this.elGestor = elGestor;
		this.clienteId  = clienteId;
		this.generadorEnteros = elGenerador;
		this.start();
	}
	
	public void run(){
		boolean esMayor = false;
		int     valor   = Integer.MIN_VALUE;
		
		while (true) {
		
			try{
				Thread.sleep(2000);
				valor = generadorEnteros.otroEntero();
				System.out.println("---- El cliente " + clienteId + " lee el valor: " + valor);
				esMayor = elGestor.notificarValor(valor, clienteId);
				System.out.println("---- El cliente " + clienteId + " obtiene el valor: " + esMayor);
			
			} catch (InterruptedException e){};
		}
	}
}