package es.upm.dit.adsw.GestorAparcamiento;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */

public class GestorAparcamiento
{
	private int     numPlazas, numCoches;
	private boolean turnoEste, turnoOeste;
	private int     esperaEnEste, esperaEnOeste;
	
	public GestorAparcamiento (int numPlazas)
	{
		this.numPlazas  = numPlazas;
		numCoches       = 0;
		turnoEste       = turnoOeste    = true;
		esperaEnEste    = esperaEnOeste = 0;
	}
	
	public synchronized void entraCochePorEste (int idCoche) throws InterruptedException
	{
		esperaEnEste++;
		while ((numCoches >= numPlazas) || (turnoOeste && (esperaEnOeste > 0)))
		{
			System.out.println("-------- Se bloquea el coche " + idCoche + " que entra por el este" );
			try {
				wait(); 				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println(">>>>>>> Entra el coche " + idCoche + " desde el este" );
		esperaEnEste--;
		numCoches++;
		
		if (numCoches == numPlazas)
		{
			turnoEste  = false;
			turnoOeste = true;
		}
	}
	public synchronized void entraCochePorOeste (int idCoche) throws InterruptedException
	{
		esperaEnOeste++;
		
		while ((numCoches >= numPlazas) || (turnoEste && (esperaEnEste > 0)))
		{
			System.out.println("++++++++ Se bloquea el coche " + idCoche + " que entra por el oeste" );
			wait(); 
		}

		System.out.println(">>>>>>> Entra el coche " + idCoche + " desde el oeste" );
		
		esperaEnOeste--;
		numCoches++;

		if (numCoches == numPlazas)
		{
			turnoEste= true;
			turnoOeste= false;
		}
	}
	
	public synchronized void saleCoche (int idCoche)
	{
		numCoches--;
		
		System.out.println("<<<<<<<< Sale el coche " + idCoche);
		
		notifyAll();
	}
}

