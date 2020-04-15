package es.upm.dit.adsw.GestorMuseo;

public class SimularSala {
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezarP    = 5;
		int paraEmpezarJ    = 3;
		int paraSeguir      = 10;
		int nVecesT         = 10;
		int idPersona       = 0;
		int idJubilado      = 1000;
		int retardoInicial;
		int maxRetardoInicial = 10000; //milisegundos
		int ratio             = 4;
		GestorSalaClase elGestor = new GestorSalaClase();
		
		retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
		new SensorTemperatura(elGestor, nVecesT, retardoInicial);
		
		//Creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezarP; i++) {
			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Persona(elGestor, idPersona , retardoInicial); 
			idPersona++;
		}

		for (int i = 0; i < paraEmpezarJ; i++) {
			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Persona(elGestor, idJubilado , retardoInicial); 
			idJubilado++;
		}

		
		//Luego voy creando poco a poco

		for (int i = 0; i < paraSeguir; i++){

			retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
			new Persona(elGestor, idPersona , retardoInicial); 
			idPersona++;

			// Crear un jubilado por cada persona
			if (i % ratio == 0) {
				retardoInicial = (int) (maxRetardoInicial * generador.nextFloat());
				new Persona(elGestor, idJubilado , retardoInicial); 
				idJubilado++;
			}
		}
	}
}
