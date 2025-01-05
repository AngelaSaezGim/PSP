package bancoPackage;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

	private static int contadorCuentas = 0; // Para que las cuentas sean unicas - static

	private int idCuenta; // id de cuenta instanciada
	private List<Cliente> listadoBeneficiarios;
	private int saldo;
	private boolean esDeudora;

	public Cuenta() {
		idCuenta = ++contadorCuentas;
		listadoBeneficiarios = new ArrayList<>();
		this.saldo = 100; // 100 euros de regalo
		// Al crearla, como tenemos los 100 euros de regalo, nunca va a ser deudora
		this.esDeudora = false;
	}

	public static int getIdCuenta() {
		return contadorCuentas;
	}

	public static void setIdCuenta(int idCuenta) {
		Cuenta.contadorCuentas = idCuenta;
	}

	public int getId() {
		return idCuenta;
	}

	public void setId(int id) {
		this.idCuenta = id;
	}

	public List<Cliente> getListadoBeneficiarios() {
		return listadoBeneficiarios;
	}

	public void setListadoBeneficiarios(List<Cliente> listadoBeneficiarios) {
		this.listadoBeneficiarios = listadoBeneficiarios;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public boolean isDeudora() {
		return esDeudora;
	}

	public void setDeudora(boolean deudora) {
		this.esDeudora = deudora;
	}

	// Agregar clientes a la cuenta
	public void agregarBeneficiario(Cliente cliente) {
		listadoBeneficiarios.add(cliente);
	}

	/*
	 * Tendremos hilos de ejecución que se encargarán de extraer dinero de una
	 * cuenta, que es la acción principal de la aplicación y que se tendrá que
	 * llevar a cabo de un modo sincronizado.
	 */
	
	//A ESTE MÉTODO SINCRONIZADO LO LLAMARÁ LA CLASE SacarDinero.java (donde están los hilos)
	//ES EL METODO DONDE PODEMOS RESTAR EL SALDO
	public synchronized void retirarDinero(int cantidad) {
		
		// antes de nada, validamos si la cuenta es deudora
		 if (saldo < 0) {
	            saldo -= cantidad;
	            esDeudora=true;
	        } else if (saldo >= cantidad) {
	            saldo -= cantidad;
	         //No hay suficiente saldo pero no es deudora
	        } else {
	        }
	}

	// Obtener estado de la cuenta en base si deudora es true o false
	// //estado; (ACTIVA si el saldo es >=0, DEUDORA si el 2saldo es < 0)
	// en el constructor revisamos si es deudora
	public String obtenerEstado() {
		if (esDeudora) {
			return "DEUDORA";
		} else {
			return "ACTIVA";
		}
	}

	@Override
	public String toString() {
		//Usamos el id de la instancia, no el contador
		String resultado = "[ID Cuenta=" + idCuenta + ", Saldo=" + saldo + ", Estado="
				+ (saldo >= 0 ? "ACTIVA" : "DEUDORA") + "]\n" + "Beneficiarios:\n";

		for (Cliente beneficiario : listadoBeneficiarios) {
			resultado += "  " + beneficiario + "\n"; // Cada cliente asociado a esa cuenta en una linea
		}

		return resultado;
	}

}
