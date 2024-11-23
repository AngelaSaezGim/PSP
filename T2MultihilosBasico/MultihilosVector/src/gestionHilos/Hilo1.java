package gestionHilos;

public class Hilo1 extends Thread {

	private Casilla[] vector;
	private final int limiteInferior;
	private final int limiteSuperior;

	public Hilo1(String nombre, Casilla[] vector, int limiteInferior, int limiteSuperior) {
		super(nombre);
		this.vector = vector;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
	}

	// Sobreescribimos
	@Override
	public void run() {

		// DE IZQUIERDA A DERECHA
		for (int i = 0; i < vector.length; i++) {
			if (vector[i].getValor() == 0) { // Si casilla vacía = rellenamos
				// Rellenamos con valor aleatorio (Math.random) - Entre el limite inferior y superior
				vector[i].setValor((int) (Math.random() * (limiteSuperior - limiteInferior + 1)) + limiteInferior);
				//id del hilo que rellenó (1)
				vector[i].setIdHilo(getName()); //Nombre del hilo = "1"

				gestionHilosPrincipal.mostrarVector(); // Mostramos vector

				try {
					Thread.sleep(500); // Esperamos- asi podemos ir visualizando los cambios
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
