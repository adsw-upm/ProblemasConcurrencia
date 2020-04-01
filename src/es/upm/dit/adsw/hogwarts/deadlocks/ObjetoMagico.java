package es.upm.dit.adsw.hogwarts.deadlocks;

public class ObjetoMagico {

	
	private boolean ocupado;
	private String nombre;

	public ObjetoMagico(String nombre) {
		this.ocupado = false;
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public boolean estaOcupado(){
		return this.ocupado;
	}
	
	public void ocupar(){
		this.ocupado = true;
	}
	
	public void desocupar(){
		this.ocupado = false;
	}	
	
}
