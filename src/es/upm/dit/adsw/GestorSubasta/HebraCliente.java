package es.upm.dit.adsw.GestorSubasta;

/**
 * @author Alejandro Alonso 
 * @since  20141226
 */

import java.util.Random;
public class HebraCliente extends Thread{

	int id = 0;
	int avance = 0;
	static final int n  = 20;
	int maxPuja = 50000;
	GestorSubasta unMonitorSubasta; //= new monitorOrden();
	Random generador = new java.util.Random(System.currentTimeMillis());
	
	public HebraCliente (int unId, GestorSubasta unMonitorSubasta, int avance){
		id = unId;
		this.unMonitorSubasta = unMonitorSubasta;
		this.avance = avance;
	}
	
	public void run () {
		int i = 0;
		long maxRetardo = 3000; //milisegundos
        long retardo = 0;
		int puja = 0;
		
		for (i=0; i < n; i++ ){
			
			try {			
				puja = (int)(maxPuja * generador.nextFloat());
				unMonitorSubasta.pujarLote(unMonitorSubasta.obtenerNLote() + avance, puja, id);
				retardo = (long) (maxRetardo * generador.nextFloat());
				Thread.sleep(retardo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
