package gestionHilos;

import java.util.Scanner;

public class gestionHilosPrincipal {

	private static Scanner tcl = new Scanner(System.in);
	// Creamos el vector (objectoCasilla) para almacenar los valores
	private static Casilla[] vector = new Casilla[10]; //10 = posiciones

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Introduzca el limite inferior");
		int limiteInferior = tcl.nextInt();

		System.out.println("Introduzca el limite superior");
		int limiteSuperior = tcl.nextInt();

		// Al principio todo el vector serán 0s
		for (int i = 0; i < vector.length; i++) {
			vector[i] = new Casilla();
		}
		
		// Mostramos el índice y la barra primero (ver que está en 0)
		System.out.println("Vector iniciado");
        mostrarVector();

		Hilo1 h1 = new Hilo1("1", vector, limiteInferior, limiteSuperior);
		Hilo2 h2 = new Hilo2("2", vector, limiteInferior, limiteSuperior);

		// Los hilos rellenan;
		h1.start();
		h2.start();

	}

	// Metodo que usamos en hilo 1 y hilo 2 (común) para mostrar el vector
	
	//USAMOS syncronized para que hilos no empiezen a imprimir a la vez y sea el print demasiado caótico 
	protected synchronized static void mostrarVector() {
		
		 System.out.println();
		
		// USAMOS FORMATO DE ALINEADO - printf("%-4s") 
		//Tuve que usar 4-5 ya que no se me printeaba bien y salia desconfigurado
		
		// PRIMERA LINEA - Mostramos indices de cada casilla
		 
		for (int i = 0; i < vector.length; i++) {
			System.out.printf("%-4d ", i);  // Índices con 2 caracteres de ancho
		}
		System.out.println();
		
		// SEGUNDA LINEA Mostramos la barra
		 for (int i = 0; i < vector.length+2; i++) {
	            System.out.print("----");  // Barra mismo tamaño que vector
	        }
	    System.out.println();
        
        //TERCERA LINEA - Valores
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("%-5d", vector[i].getValor());
        }
        System.out.println();
        
        //CUARTA LINEA - Identificador del hilo que rellena
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("%-5s", vector[i].getIdHilo());
        }
        
        System.out.println();
        System.out.println();
	}

}
