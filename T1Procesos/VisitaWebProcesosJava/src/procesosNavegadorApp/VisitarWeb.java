package procesosNavegadorApp;
import java.io.*;

public class VisitarWeb {
	
	public static void main(String[] args) throws Exception {
		
		    // Leer la salida LeerWeb 
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String entrada = reader.readLine();  
		    reader.close();

	        String[] listaPaginas = entrada.split("@");

	        if (listaPaginas.length > 3) {
	            System.out.println("Error - Demasiadas Webs (más de 3)");
	            return;
	        }

	        // Abrir las páginas en Firefox - PROCESO VISITAR
	        
	        ProcessBuilder firefox = new ProcessBuilder();
	        firefox.command("firefox");

	        //Segun si es 1,2 o 3 - for
	        for (String pagina : listaPaginas) {
	            firefox.command().add(pagina);
	        }

	        System.out.println("Abriendo páginas...");
	        firefox.start();

		}
	
}
