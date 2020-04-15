package es.upm.dit.adsw.GestorAparcamiento; 

public class SimularAparcamiento {
	/**
	 * @author Alejandro Alonso
	 * @since  20150223
	 * Recopilado de solución de examen.
	 */
	public static void main(String[] args) {

		java.util.Random generador  = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar             = 5;
		int nCoches                 = 20;
		int cochesAparcamiento      = 5;
		int idCoche                 = 0;
		long retardoInicial;
		long maxRetardoInicial      = 10000; //milisegundos
		GestorAparcamiento elGestor = new GestorAparcamiento(cochesAparcamiento);

		//Creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezar; i++) {

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new CocheEste(elGestor, idCoche, retardoInicial); 
			idCoche++;

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new CocheOeste(elGestor, idCoche, retardoInicial);
			idCoche++;
		}

		//Luego voy creando poco a poco

		for (int i = paraEmpezar; i < nCoches; i++){
			try {
				Thread.sleep ((long)(maxRetardoInicial * generador.nextFloat()/10));				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheEste(elGestor, idCoche, retardoInicial);
			idCoche++;

			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheOeste(elGestor, idCoche, retardoInicial);
			idCoche++;
		}
	}
}
