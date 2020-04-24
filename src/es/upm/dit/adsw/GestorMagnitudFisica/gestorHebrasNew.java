package es.upm.dit.adsw.GestorMagnitudFisica;

/**
 * @author Alejandro Alonso
 * @since  20100628
 * Recopilado de solución de examen.
 */

public class gestorHebras {

	private boolean cicloActivo = false;
	private boolean hayEsperando = false;
	private int valorMinimo = Integer.MIN_VALUE;
	private int nCiclo= 0;
	
	public synchronized void inicioCiclo ()
		throws InterruptedException
	{

		if (! cicloActivo) {
			nCiclo ++;
			System.out.println("+++++ Inicio del ciclo " + nCiclo);
			while (hayEsperando) wait();
			cicloActivo = true;
		}
	}

	public synchronized  void finCiclo()
	{
		if (cicloActivo){
			cicloActivo = false;
			System.out.println("+++++ Fin del ciclo " + nCiclo);
			notifyAll();
		}
	}

	public synchronized boolean notificarValor (int elValor, int idCliente)
		throws InterruptedException
	{
		
		if (!cicloActivo) return false;
 
		hayEsperando = true;
		if (valorMinimo <= elValor) valorMinimo = elValor;
		notifyAll(); // Ver si se puede dejar en un notify a secas
		
		while (elValor >= valorMinimo && cicloActivo) wait();
		
		if (elValor == valorMinimo) {
			hayEsperando = false;
			notifyAll();
			valorMinimo = Integer.MIN_VALUE;
			return true;
		}
		else return false;
	}	

}
