package littleManEjercicio;

import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;

public class LittleManApp {

	private static Scanner tcl = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String inputComando;
		boolean exit = true;

		System.out.println("Bienvenido a LITTLE MAN");
		do {
			System.out.println("Introduce el comando (ej ls, mkdir) o 'salir'");
			inputComando = tcl.nextLine();
			
			if (inputComando.equalsIgnoreCase("salir")) {
				break;
			}
			
			System.out.println("Comando = man " + inputComando);
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", "man " + inputComando);

			try {
				Process p = pb.start();

				// LEER EL COMANDO (si da algo)
				try (InputStream is = p.getInputStream()) {

					String nombreLinea = null;
	                String sinopsisLinea = null;
	                Scanner tcl2 = new Scanner(is);
                    
                    /*
					1MAN(1)                Utilidades de paginador del manual                MAN(1)
					2
					3NOMBRE
   					4man - interfaz de los manuales de referencia del sistema
					5
					6SINOPSIS
   					7man [opciones de man] [[secciÃ³n] pÃ¡gina ...] ...
   					
   					Solo quiero la linea 4 y la linea 7 y almacenarlas en variables
                     */
                    
                    for (int i = 1; i <= 7 && tcl2.hasNextLine(); i++) { //Recorremos el texto entrada
                        String linea = tcl2.nextLine();
                        if (i == 4) {
                            nombreLinea = linea; // 4
                        } else if (i == 7) {
                            sinopsisLinea = linea; // 7
                        }
                    }
                    
                    if(nombreLinea!=null || sinopsisLinea!=null) {
					System.out.println("- Nombre :    " + nombreLinea);
					System.out.println("- Prototipo : " + sinopsisLinea);
					System.out.println("");
					}
					
					tcl2.close();
				}

				// Espera a que el proceso termine - saber SI acabó bien o si es comando valido
				int exitVal = p.waitFor();
				if (exitVal != 0) {
					System.out.println("ERROR - El proceso NO terminó correctamente o NO existe el comando");
				} else {
					System.out.println("El proceso TERMINÓ correctamente");
				}
			} catch (IOException e) {
				System.err.println("Error al ejecutar el comando: " + e.getMessage());
			} catch (InterruptedException e) {
				System.err.println("El proceso fue interrumpido: " + e.getMessage());
			}

		} while (exit);

		System.out.println("Has salido");
	}
}
