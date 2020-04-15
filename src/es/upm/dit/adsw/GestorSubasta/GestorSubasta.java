package es.upm.dit.adsw.GestorSubasta;

/**
 * @author Alejandro Alonso 
 * @since  20141226
 */

public class GestorSubasta {

	int nLote             = 0;
	int precioLote        = 0;
	int ofertaLote        = 0;
    boolean subastaActiva = false;
    
    
	public synchronized void iniciarSubastaLote(int precio) {
 
		System.out.println("!!!! Inicio lote: " + " lote: " + (nLote + 1) + " precio " + precio);		 
		
		if (!subastaActiva) {
     	   subastaActiva = true;
    	   precioLote    = precio;
    	   ofertaLote    = 0;
    	   nLote++;
    	   notifyAll();        	
        }

	}
	
	public synchronized void finalizarSubastaLote() {
        if (subastaActiva) {
    		subastaActiva = false;
    		notifyAll();        	
        }
	}

	public synchronized int obtenerNLote() {
		return nLote;
	}	
	
	public synchronized boolean pujarLote(int lote, int oferta, int id) 
	throws InterruptedException {
		
		System.out.println(">>>>Entra hebra: " + id + " lote: " + lote + " oferta: " + oferta );
		// Bloque ofertas de lotes cuya subasta no ha comenzado
		while (lote > nLote) wait();

		// Fin de ejecuci'on de hebras con ofertas a lotes cuya
		// subasta ya ha finalizado
		if ((lote < nLote) || (lote == nLote && !subastaActiva)) {
			System.out.println("<<<<Hebra: " + id + " lote: " + lote + " lote finalizado ");
			return false;
		}

		if (oferta > ofertaLote) {
			// La oferta es la m'axima
			ofertaLote = oferta;

			// Si la oferta es mayor o igual que el precio, la subasta
			// del lote ha finalizado
			if (precioLote <= oferta) {
				subastaActiva = false;            	
				notifyAll();
				System.out.println("<<<<Hebra: " + id + " lote: " + lote + " oferta MAXIMA. Iguala ");
				return true;
			}

			while ((oferta == ofertaLote) && (subastaActiva)) {wait();}

			if (oferta == ofertaLote) {
				// La subasta del lote ha finalizado y es la mayor oferta
				System.out.println("<<<<Hebra: " + id + " lote: " + lote + " oferta MAXIMA. Final ");
				return true;
			} else {
				// Se ha recibido una oferta mayor
				System.out.println("<<<<Hebra: " + id + " lote: " + lote + " oferta baja ");
				return false;
			}

		} else {
			// La oferta es menor que la m'axima
			System.out.println("<<<<Hebra: " + id + " lote: " + lote + " oferta baja ");
			return false;
		}
			
	}
	
}
