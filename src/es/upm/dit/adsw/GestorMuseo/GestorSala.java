package es.upm.dit.adsw.GestorMuseo;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Monitor que gestiona la entrada a una sala de museo, dependiendo de la capacidad m'axima
 * que depende de la temperatura. Los jubilados tienen prioridad
 */
public class GestorSala {

	private int nPersonas;
	private int nMaxPersonasNormalT;
	private int nMaxPersonasAltaT;
	private int nMaxPersonas;
	private int tUmbral;
	private int nJubilados;

	public GestorSala() {
		nPersonas = 0;
		// Los dos siguientes parámetros se cambian para 
		// simplificar la simulación de esta clase con 
		// un número menor de personas.
	
		nMaxPersonasNormalT = 10; //50;
		nMaxPersonasAltaT   = 5;  //35;
		nMaxPersonas = nMaxPersonasNormalT;
		tUmbral = 30;
		nJubilados = 0;
	}
	
	public synchronized void entrarSalaJubilado(int idJubilado)
			throws InterruptedException {
		nJubilados ++;
		while (nPersonas >= nMaxPersonas) {
			System.out.println("++++++++ Se bloquea el jubilado " + idJubilado );
			wait();
		}
		nJubilados --;

		
		// Siguiente es un caso si entra un jubilado y hay hueco en la sala
		// y hay personas esperando. Puede ocurrir cuando se notificaTemperatura
		// al reducir el umbral. s
        if (nJubilados == 0) notifyAll();		
		nPersonas ++;
		System.out.println(">>>>>>> Entra la jubilado " + idJubilado +
				" nPersonas dentro " + nPersonas);
	}

	public synchronized void entrarSala (int idPersona)
			throws InterruptedException {
		while (nPersonas >= nMaxPersonas || nJubilados > 0)
		{
			// Espero si no pueden entrar m'as personas
			System.out.println("++++++++ Se bloquea la persona " + idPersona );
			wait();
		}
		nPersonas++;
		System.out.println(">>>>>>> Entra la persona " + idPersona  +
				" nPersonas dentro " + nPersonas);
	}

	public synchronized void salirSala (int idPersona)
			throws InterruptedException {
		System.out.println("<<<<<<<< Sale la persona " + idPersona);
		if (nPersonas <= nMaxPersonas) {
			notifyAll();
		}
		nPersonas--;
	}

	public synchronized void notificarTemperatura (int temperatura)
	{   
		System.out.print("!!!!!!!!! Notificar T " + temperatura);
		if (temperatura > tUmbral) {
			nMaxPersonas = nMaxPersonasAltaT;
			System.out.println(" Cambia el Aforo " + nMaxPersonas);
		}
		if (temperatura < tUmbral) {
			nMaxPersonas = nMaxPersonasNormalT;
			System.out.println(" Cambia el Aforo " + nMaxPersonas);
			notifyAll();
		}
	}
}