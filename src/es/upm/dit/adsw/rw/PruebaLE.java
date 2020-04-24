package es.upm.dit.adsw.rw;

import es.upm.dit.adsw.GestorAparcamiento.CocheEste;
import es.upm.dit.adsw.GestorAparcamiento.CocheOeste;

/**
 * Programa de prueba de lectores y escritores
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class PruebaLE {

	public static void main(String[] args) {

		final int nLectores = 10;
		final int nEscritores = 8;
		
		java.util.Random generador  = new java.util.Random(System.currentTimeMillis());
		long retardoInicial;
		long maxRetardoInicial      = 10000; 
//		Gestor gestor = new GestorLE();
//		Gestor gestor = new GestorLEPrioridadEscritores();
		Gestor     gestor   = new GestorLEEquitativo();
		Lector[]   lector   = new Lector[nLectores];
		Escritor[] escritor = new Escritor[nEscritores];

		for (int i = 0; i < nEscritores; i++) {
			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			escritor[i] = new Escritor(gestor, i, retardoInicial);
			escritor[i].start();
		}
		for (int i = 0; i < nLectores; i++) {
			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			lector[i] = new Lector(gestor, i*1000, retardoInicial);
			lector[i].start();
		}
	
	}
}
