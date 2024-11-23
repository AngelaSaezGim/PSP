package ejercicioMultihilos;

public class PrincipalMenu {

	public static void main(String[] args) {
		
		GrupoHilos grupo = new GrupoHilos("Menu Diario");

		PrimerosHilo h1 = new PrimerosHilo(grupo,"./src/menus.txt", "./src/primeros.txt");
		SegundosHilo h2 = new SegundosHilo(grupo,"./src/menus.txt", "./src/segundos.txt");
		PostresHilo h3 = new PostresHilo(grupo,"./src/menus.txt", "./src/postres.txt");
		
		h1.start();
		h2.start();
		h3.start();
	}
}
