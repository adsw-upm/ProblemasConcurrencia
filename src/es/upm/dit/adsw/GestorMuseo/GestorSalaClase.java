package es.upm.dit.adsw.GestorMuseo;

/**
 * @author Alejandro Alonso
 * @since  20150223
 */

public class GestorSalaClase {

	private int AforoMaxA;
	private int AforoMaxB;
	private int AforoMax;
	private int Aforo;
	private int nJubilados;
	private int tUmbral;
	private int temperatura;

	public GestorSalaClase() {
		this.AforoMaxA   =  5; //50;
		this.AforoMaxB   =  3; //30 
		this.AforoMax    = AforoMaxA;
		this.Aforo       = 0;
		this.nJubilados  = 0;
		this.tUmbral     = 35;
		this.temperatura = 0;		
	}
	
	
	public synchronized void entrarSalaJubilado(int idJubilado) throws InterruptedException {
		nJubilados++;

		if (Aforo >= AforoMax) {
			System.out.println("++++++++ Se bloquea el jubilado " + idJubilado );
		}
		
		while (Aforo >= AforoMax) {
				wait();				
		}
		System.out.println(">>>>>>> Entra el jubilado " + idJubilado +
				" nPersonas dentro " + Aforo);
		nJubilados --;
		Aforo ++;
	}
	
	public synchronized void entrarSala(int idPersona) throws InterruptedException {

		if (Aforo >= AforoMax || nJubilados > 0) {
			System.out.println("++++++++ Se bloquea un no jubilado " + idPersona );
		}
		
		while (Aforo >= AforoMax || nJubilados > 0) {
			try {
				wait();				
			} catch (Exception  InterruptedException) {
			
			}
		}

		Aforo ++;
		System.out.println(">>>>>>> Entra el no jubilado " + idPersona +
				" nPersonas dentro " + Aforo);
	}
	
	public synchronized void salirSala(int idPersona) {
		
		Aforo--;
		notifyAll();
		System.out.println("<<<<<<<< Sale la persona " + idPersona);

	}
	
	
	public synchronized void notificarTemperatura(int temperatura) {
		this.temperatura = temperatura;
		if (temperatura >= tUmbral) {AforoMax = AforoMaxB;}
		if (temperatura < tUmbral) {AforoMax = AforoMaxA;}
		System.out.println("!!!!!!!!! Notificar T " + temperatura + "; Aforo máximo: " + AforoMax);
	}

}

