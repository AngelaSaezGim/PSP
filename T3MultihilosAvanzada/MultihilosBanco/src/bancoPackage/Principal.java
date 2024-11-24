package bancoPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	private static Scanner tcl = new Scanner(System.in);

	private enum MenuOption {
		QUERY_ALTA, QUERY_RESUMEN, QUERY_SACAR, EXIT
	};

	//lista estática de cuentas que nos va a permitir imprimirlas todas y ver si está vacia o no
	private static List<Cuenta> cuentas = new ArrayList<>();

	public static void main(String[] args) {

		MenuOption opcionElegida = null;

		do {
			System.out.print("----------------------------------\r\n"
							+ "1-Dar de alta una cuenta\r\n"
							+ "2-Resumen de cuentas\r\n"
							+ "3-Sacar dinero de una cuenta\r\n"
							+ "4-Salir\r\n"
							+ "---------------------------------");
			opcionElegida = readChoice();

			switch (opcionElegida) {
			case QUERY_ALTA:
				altaCuenta();
				break;
			case QUERY_RESUMEN:
				printResumenCuenta();
				break;
			case QUERY_SACAR:
				sacarDineroCuenta();
				break;
			case EXIT:
				System.out.println("\n\n Gracias por usar nuestra App. Un saludo,adios!! \n\n");
				break;
			default:
				System.out.println("Opción no válida, intenta nuevamente.");
			}

		} while (opcionElegida != MenuOption.EXIT);

		tcl.close();

	}

	private static MenuOption readChoice() {
		try {
			int choiceInt = Integer.valueOf(tcl.nextLine());
			return MenuOption.values()[choiceInt - 1];
		} catch (RuntimeException re) {
			System.out.println("Opción inválida... Inténtelo otra vez.");
			return readChoice();
		}
	}
	
	/* 1. Dar de alta una cuenta: */

	private static void altaCuenta() {

		// Damos de alta 1 cuenta
		Cuenta nuevaCuenta = new Cuenta();
		System.out.println("Vamos a dar de alta a la cuenta nº" +  nuevaCuenta.getId());
		// Solicitará el número de beneficiarios de la cuenta.
		System.out.print("Número de beneficiarios: ");
		int numBeneficiarios = tcl.nextInt();
		tcl.nextLine(); 

		for (int i = 0; i < numBeneficiarios; i++) {
			System.out.println("Beneficiario nº" + (i + 1) + ":");

			System.out.print("Nombre: ");
			String nombre = tcl.nextLine();

			System.out.print("Edad: ");
			int edad = tcl.nextInt();
			tcl.nextLine();

			boolean entradaValida = false;
			char nacionalidad = '\0';
			do {
				System.out.print("¿Nacionalidad española? (s/n): ");
				nacionalidad = tcl.next().charAt(0);
				tcl.nextLine();

				// Validamos si la entrada es valida (prefiero desde aqui antes que en el constructor)
				if (nacionalidad == 's' || nacionalidad == 'n' || nacionalidad == 'S' || nacionalidad == 'N') {
					entradaValida = true; // Valido- salimos del bucle
				} else {
					System.out.println("Dato no válido - La nacionalidad debe ser 's' (sí) o 'n' (no).");
				}
			} while (!entradaValida);

			Cliente cliente = new Cliente(nombre, edad, nacionalidad);
			// Le agregamos a esa nueva cuenta esos clientes (for)
			// En el contructor de la cuenta ya tenemos los 100 de regalo
			nuevaCuenta.agregarBeneficiario(cliente);
		}

		// Se agrega esa cuenta a la lista estática de cuentas
		cuentas.add(nuevaCuenta);
		System.out.println("Cuenta creada exitosamente \n" + nuevaCuenta);
	}

	// 2. Resumen de cuentas ;
	private static void printResumenCuenta() {

		// si no hay cuentas, notificamos
		if (cuentas.isEmpty()) {
			System.out.println("No hay cuentas registradas.");
			return;
		}

		System.out.println("\nResumen global de cuentas:");
		// :Datos de todas las cuentas, que serán: identificador,saldo y estado
		for (Cuenta cuenta : cuentas) { // iteramos en todas las cuentas agregadas y las mostramos
			// Datos de la cuenta
			System.out.println(cuenta);
		}
	}
	/*
	 * 3. Sacar dinero de una cuenta:. (metodo sacar dinero en objeto Cuenta)
	 */
	//metodo para buscar cuenta por ID - la cuenta deberá existir para sacar dinero
	private static Cuenta buscarCuentaPorId(int idCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getId() == idCuenta) {
                return cuenta;
            }
        }
        return null;
    }

	private static void sacarDineroCuenta() {

		Cuenta cuenta = null;
		// Solicitará el identificador de la cuenta, que deberá existir
		while (cuenta == null) {
			// Solicitar el identificador de la cuenta
			System.out.print("Introduce el ID de la cuenta: ");
			int idCuenta = tcl.nextInt();
			tcl.nextLine(); // Limpiar buffer

			cuenta = buscarCuentaPorId(idCuenta);

			if (cuenta == null) {
				System.out.println("No se encontró ninguna cuenta con ese ID. Intenta de nuevo.");
			}
		}

		// Crear y arrancar un hilo por cada beneficiario
		// trabajar tantos hilos como beneficiarios tenga la cuenta, que será el objeto
		// compartido entre todos ellos
		List<SacarDinero> hilos = new ArrayList<>();

		for (Cliente beneficiario : cuenta.getListadoBeneficiarios()){
		    SacarDinero hilo = new SacarDinero(cuenta, beneficiario.getNombre());
		    hilos.add(hilo);
		    hilo.start();
		}
		
		for (SacarDinero hilo : hilos) {
		    try {
		        hilo.join(); 
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

        System.out.println("Se ha terminado la operación correctamente");

	}
}
