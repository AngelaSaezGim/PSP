package gestionHilos;

public class Casilla {

	private int valor;
	private String idHilo;

	// Constructor = Se inicializa valor a 0 del vector y el identificador del hilo
	public Casilla() {
		this.valor = 0;
		this.idHilo = ""; 
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getIdHilo() {
		return idHilo;
	}

	public void setIdHilo(String idHilo) {
		this.idHilo = idHilo;
	}

}
