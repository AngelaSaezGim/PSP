package bancoPackage;

public class Cliente {

	private String nombre;
	private int age;
	private char nacionalidadEspañola;

	Cliente(String nombre, int age, char nacionalidadEspañola) {
		this.nombre = nombre;
		this.age = age;
		this.nacionalidadEspañola = nacionalidadEspañola;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getNacionalidad() {
		return nacionalidadEspañola;
	}

	public void setNacionalidad(char nacionalidadEspañola) {
		this.nacionalidadEspañola = nacionalidadEspañola;
	}
	
	@Override
	public String toString() {
		return "-Nombre=" + nombre + ", Edad=" + age + ", Nacionalidad Española="
				+ nacionalidadEspañola + "";
	}

}
