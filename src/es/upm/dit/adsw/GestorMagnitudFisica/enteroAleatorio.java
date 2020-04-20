package es.upm.dit.adsw.GestorMagnitudFisica;

/**
 * @author Alejandro Alonso
 * @since  20100628
 * Recopilado de solución de examen.
 */

public class enteroAleatorio {

	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());	
	
	public int otroEntero() {

		return generador.nextInt();
	}
	
}
