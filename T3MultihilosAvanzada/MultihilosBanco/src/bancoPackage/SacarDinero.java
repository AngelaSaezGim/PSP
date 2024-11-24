package bancoPackage;

public class SacarDinero extends Thread {

	/*
	 * El método de sacar dinero, será un método sincronizado y responderá de la
	 * siguiente forma: Si existe saldo, informará que se va a retirar el saldo,
	 * modifica el saldo e informará de la cantidad antes y después de retirar el
	 * dinero, Si no existe saldo, se informará que no puede retirar el dinero. Si
	 * el saldo fuera negativo (-10 euros por ej) se dictará que esa cuenta es
	 * deudora.
	 */

	private Cuenta cuenta;
	private String nombreBeneficiario;

	public SacarDinero(Cuenta cuenta, String nombreBeneficiario) {
		this.cuenta = cuenta;
		this.nombreBeneficiario = nombreBeneficiario;
	}

	// Es en total 30 pero se retirá de 10 en 10
	// . Los beneficiarios siempre sacarán un importe de 30 euros en billetes de 10.
	@Override
	public void run() {
		int dineroRetirar=10;
		synchronized (cuenta) { // Solo un hilo pueda acceder al método al mismo tiempo
			// Intentamos retirar 10 euros tres veces (en total 30 euros)
			for (int i = 0; i < 3; i++) {
				if (cuenta.getSaldo() >= dineroRetirar) { // Solo si saldo es suficiente
	                System.out.println(nombreBeneficiario + ": SE VA A RETIRAR SALDO (Actual es " + cuenta.getSaldo() + " euros)");
	                cuenta.retirarDinero(dineroRetirar); // Cada beneficiario intenta retirar 10 euros
	                System.out.println(nombreBeneficiario + " retira " + dineroRetirar + " euros - Saldo actual: " + cuenta.getSaldo() + " euros");
	            } else if(cuenta.getSaldo()<0){
	            	System.out.println(nombreBeneficiario + " es deudor/a con " + cuenta.getSaldo() +" no debería estar retirando dinero");
	            }
				else {
	                System.out.println(nombreBeneficiario + " no puede retirar dinero. No hay saldo; tiene " + cuenta.getSaldo() + " euros.");
	            }
				try {
					Thread.sleep(1000); // 1 segundo entre retiradas
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
