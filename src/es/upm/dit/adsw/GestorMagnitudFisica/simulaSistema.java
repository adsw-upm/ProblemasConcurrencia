package es.upm.dit.adsw.GestorMagnitudFisica;

/**
 * @author Alejandro Alonso
 * @since  20100628
 * Recopilado de solución de examen.
 */

public class simulaSistema {
	public static void main(String[] args) {

		int paraEmpezar     = 5;
		enteroAleatorio generadorEnteros = new enteroAleatorio();
		
		gestorHebras  elGestor = new gestorHebras();
		
		new hebraGestora(elGestor);
		
		//creo unas cuantas hebras para empezar
		for (int i = 0; i < paraEmpezar; i++) {
					    
			new hebraLectora(elGestor, generadorEnteros, i);

		}
		
}
}

