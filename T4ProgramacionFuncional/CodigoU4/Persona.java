package concurrente;

public class Persona {
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public String toString() {
		return nombre + " tiene " + edad + " años.";
	}
	
	public int getEdad() {
		return edad;
	}
	public String getNombre(){
		return nombre;
	}
}
