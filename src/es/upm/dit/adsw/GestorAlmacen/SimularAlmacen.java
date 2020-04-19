package es.upm.dit.adsw.GestorAlmacen; 

public class SimularAlmacen {
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar     = 5;
		int nTrabajadores   = 8;
		int idConsumidor    = 0;
		int idProductor     = 0;
		int retardoInicial;
		long maxRetardoInicial = 10000; //milisegundos
		GestorAlmacen elGestor = new GestorAlmacen();

		//Creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezar; i++) {

			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Productor(elGestor, idProductor, retardoInicial); 
			idProductor++;

			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Constructor(elGestor, idConsumidor, retardoInicial);
			idConsumidor++;
		}

		//Luego voy creando poco a poco

		for (int i = paraEmpezar; i < nTrabajadores; i++){

			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Productor(elGestor, idProductor, retardoInicial); 
			idProductor++;

			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Constructor(elGestor, idConsumidor, retardoInicial);
			idConsumidor++;
		}
	}
}
