package es.upm.dit.adsw.GestorSubasta;

/**
 * @author Alejandro Alonso 
 * @since  20141226
 */

import java.util.Random;
public class HebraGestora extends Thread{

	static final int n  = 20;
	int maxPuja = 50000;
	GestorSubasta unMonitorSubasta; //= new monitorOrden();
	Random generador = new java.util.Random(System.currentTimeMillis());
	
	public HebraGestora (GestorSubasta unMonitorSubasta){
		this.unMonitorSubasta = unMonitorSubasta;
	}
	
	public void run () {
		int i = 0;
		long maxRetardo = 10000; //milisegundos
		int precio = 0;
		
		for (i=0; i < n; i++ ){
			
			try {			
				precio = (int)(maxPuja * generador.nextFloat());
				unMonitorSubasta.iniciarSubastaLote(precio);
				Thread.sleep(maxRetardo);
				unMonitorSubasta.finalizarSubastaLote();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
