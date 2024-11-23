package ejercicioMultihilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrupoHilos extends ThreadGroup {

	public GrupoHilos(String nombre) {
		super(nombre);
		System.out.println("Creando grupo.. " + getName());
	}
	

	protected List<String> leerMenu(String rutaArchivoEntrada, String marcadorMenu) throws IOException {

		FileReader lectorReader = null;
		BufferedReader brR = null;
		List<String> platos = new ArrayList<>();

		try {
			lectorReader = new FileReader(rutaArchivoEntrada);
			brR = new BufferedReader(lectorReader);

			String linea;
			while ((linea = brR.readLine()) != null) {
				if (linea.startsWith(marcadorMenu)) {
					platos.add(linea.substring(2)); // Ignorar el marcador al agregar
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			brR.close();
		}

		return platos;

	}

	//Escribimos en el .txt con nuestro array de platos como string a escribir 
	protected void escribirMenu(String rutaArchivoSalida, List<String> platos) throws IOException {

		FileWriter escritorWriter = null;
		BufferedWriter brW = null;

		try {
			escritorWriter = new FileWriter(rutaArchivoSalida);
			brW = new BufferedWriter(escritorWriter);
			for (String plato : platos) {
				System.out.println("El hilo " + Thread.currentThread().getName() + " escribe... " + plato);
				brW.write(plato);
				brW.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			brW.flush();
			brW.close();
		}

	}
	
}
