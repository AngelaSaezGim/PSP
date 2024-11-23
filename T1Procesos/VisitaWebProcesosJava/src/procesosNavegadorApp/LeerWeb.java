package procesosNavegadorApp;

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class LeerWeb {

	private static Scanner tcl = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		System.out.println("Dime paginas web (max 3), separarlas por @ ej (www.uv.es@www.uji.es...)");
		String inputPaginasJuntas = tcl.nextLine();

		String[] listaPaginas = inputPaginasJuntas.split("@");

		System.out.println(Arrays.toString(listaPaginas));

		// Crear proceso visitaWeb
		File directorio = new File("/home/angsaegim/eclipse-workspace/procesosNavegador/bin");
		ProcessBuilder pb = new ProcessBuilder("java", "procesosNavegadorApp.VisitarWeb");
		pb.directory(directorio);
		Process p = pb.start();

		// Escribir la entrada en proceso VisitarWeb
		OutputStream os = p.getOutputStream();
		os.write(inputPaginasJuntas.getBytes());
		os.flush();
		os.close();

		// Leer la salida de VisitaWeb
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}
		is.close();
	}

}
