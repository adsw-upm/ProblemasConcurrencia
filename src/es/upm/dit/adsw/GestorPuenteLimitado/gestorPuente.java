package es.upm.dit.adsw.GestorPuenteLimitado;

/**
 * @author Alejandro Alonso
 * @since 20060525
**/

//NOTA: Seg�n el enunciado, puede haber varios coches en el puente
//	simult�neamente, lo que se representa en las variables número_Subiendo
//	y número_Bajando. Lo que no puede haber son coches subiendo y bajando
//	simultaneamente. Si s�lo pudiera haber un coche en el puente, se
//	podr�a representar mediante una variable que indicara si el puente
//	est� ocupado y con otra que indique el sentido de marcha actual

public class gestorPuente {
	
	//número de coches subiendo o bajando
    private int límiteEnEspera = 8; // Lo puede dar el constructor
	private int númeroBajando  = 0;
	private int númeroEsperandoBajar = 0;
	private int númeroSubiendo = 0;
	private int númeroEsperandoSubir = 0;

	
	public synchronized void entrarSubiendo(int idCoche)
		throws InterruptedException{
	
		númeroEsperandoSubir++;
		System.out.println("++++ El coche que sube " + idCoche + " intenta entrar en el puente "
				           + númeroEsperandoSubir);

		
		while (númeroBajando > 0 || 
			   (númeroEsperandoBajar > límiteEnEspera &&
			    númeroEsperandoSubir < límiteEnEspera)) 
		{wait();}
		
		númeroEsperandoSubir--;		
		númeroSubiendo++;		
		
		System.out.println("++++ El coche que sube " + idCoche + " entra en el puente "
		           + númeroEsperandoSubir + " " + númeroSubiendo);
		
	}

	public synchronized void salirSubiendo(int idCoche){
		númeroSubiendo--;
		System.out.println("++++ El coche que sube " + idCoche + " sale del puente "
		                   + númeroEsperandoSubir + " " + númeroSubiendo);

		if (númeroSubiendo == 0 ||
			(númeroSubiendo > 0 && 
			  (númeroEsperandoSubir > 0 &&
			   (númeroEsperandoSubir > límiteEnEspera ||
			    númeroEsperandoBajar < límiteEnEspera)))) 
		{notifyAll();}
	}

	
	public synchronized void entrarBajando(int idCoche)
	    throws InterruptedException{

		númeroEsperandoBajar++;
		System.out.println("---- El coche que baja " + idCoche + " intenta entrar en el puente "
				           + númeroEsperandoBajar);
		
		while (númeroSubiendo > 0 || 
			  (númeroEsperandoSubir > límiteEnEspera)) 
		{wait();}
		
		númeroEsperandoBajar--;		
		númeroBajando++;
		
		System.out.println("---- El coche que baja " + idCoche + " entra en el puente "
				           + númeroEsperandoBajar + " " +númeroBajando);
	}

	public synchronized void salirBajando(int idCoche){
		númeroBajando--;
		System.out.println("---- El coche que baja " + idCoche + " sale del puente " 
                           + númeroEsperandoBajar + " " + númeroBajando);

		// No ejecuto notifyAll si no es necesario
		if (númeroBajando == 0 || 
		    (númeroBajando > 0        && 
		     númeroEsperandoBajar > 0 &&
		     númeroEsperandoSubir < límiteEnEspera)) 
		    {notifyAll();}
	}

}
