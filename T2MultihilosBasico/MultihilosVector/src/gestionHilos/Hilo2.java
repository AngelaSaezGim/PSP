package gestionHilos;

public class Hilo2 extends Thread {

	private Casilla[] vector;
	private int limiteInferior;
	private int limiteSuperior;

	public Hilo2(String nombre, Casilla[] vector, int limiteInferior, int limiteSuperior) {
		super(nombre);
		this.vector = vector;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
	}

	public void run() {

		// DE DERECHA A IZQUIERDA (i--)
		for (int i = vector.length - 1; i >= 0; i--) {
			if (vector[i].getValor() == 0) { // Si casilla vacía = rellenamos
				// Rellenamos con valor aleatorio (Math.random) - Entre el limite inferior y superior
				vector[i].setValor((int) (Math.random() * (limiteSuperior - limiteInferior + 1)) + limiteInferior);
				//id del hilo que rellenó (2)
				vector[i].setIdHilo(getName());

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
