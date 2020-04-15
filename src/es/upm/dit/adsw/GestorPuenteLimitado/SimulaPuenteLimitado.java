package es.upm.dit.adsw.GestorPuenteLimitado;

/**
 * @author Alejandro Alonso
 * @since 20060525
**/



//import java.util.Random;

public class SimulaPuenteLimitado {
	
	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar     = 7;
		int nCoches         = 60;
		long retardoInicial;
		long maxRetardoInicial = 4000; //milisegundos
		gestorPuente elGestor = new gestorPuente();
		cocheSubiendo unCocheSubiendo;
		cocheBajando  unCocheBajando;
		
		//creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezar; i++) {
			
			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			unCocheSubiendo = new cocheSubiendo(elGestor, i, retardoInicial); 
			unCocheSubiendo.start();
			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			unCocheBajando  = new cocheBajando(elGestor, i, retardoInicial);
			unCocheBajando.start();
		}
		//Luego voy creando poco a poco
		
		try{

			for (int i = paraEmpezar; i < nCoches; i++){
				retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
				unCocheBajando  = new cocheBajando(elGestor, i, retardoInicial);
				unCocheBajando.start();
				retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
				unCocheSubiendo = new cocheSubiendo(elGestor, i, retardoInicial);
				unCocheSubiendo.start();
				Thread.sleep(1000);
				retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
				unCocheBajando  = new cocheBajando(elGestor, i + nCoches, retardoInicial);
				unCocheBajando.start();			
			}
		}
		catch (InterruptedException e) {}	
	}
}
