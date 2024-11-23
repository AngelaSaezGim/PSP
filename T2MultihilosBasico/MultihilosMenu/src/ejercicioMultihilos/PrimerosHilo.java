package ejercicioMultihilos;

import java.io.IOException;
import java.util.List;

public class PrimerosHilo extends Thread {

	private String archivoEntrada;
	private String archivoSalida;
	private GrupoHilos grupo;

	public PrimerosHilo(GrupoHilos grupo, String archivoEntrada, String archivoSalida) {
		super(grupo, "MenuPrimeros");
		this.archivoEntrada = archivoEntrada;
		this.archivoSalida = archivoSalida;
		this.grupo = grupo;
		System.out.println("Creando hilo.. " + getName() + " del grupo: " + getThreadGroup().getName());
	}

	@Override
    public void run() {
        try {
            List<String> platos = grupo.leerMenu(archivoEntrada, "1-");
            grupo.escribirMenu(archivoSalida, platos);
        } catch (IOException e) {
            System.out.println("Error en el hilo " + getName() + ": " + e.getMessage());
        }
    }

}
