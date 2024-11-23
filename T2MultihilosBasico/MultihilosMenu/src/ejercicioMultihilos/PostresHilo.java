package ejercicioMultihilos;

import java.io.IOException;
import java.util.List;

public class PostresHilo extends Thread {

	private String archivoEntrada;
	private String archivoSalida;
	private GrupoHilos grupo;

	public PostresHilo(GrupoHilos grupo, String archivoEntrada, String archivoSalida) {
		super(grupo, "MenuPostres");
		this.archivoEntrada = archivoEntrada;
		this.archivoSalida = archivoSalida;
		this.grupo = grupo;
		System.out.println("Creando hilo.. " + getName() + " del grupo: " + getThreadGroup().getName());
	}

	@Override
	public void run() {
		try {
			List<String> platos = grupo.leerMenu(archivoEntrada, "3-");
			grupo.escribirMenu(archivoSalida, platos);
		} catch (IOException e) {
			System.out.println("Error en el hilo " + getName() + ": " + e.getMessage());
		}
	}
}
