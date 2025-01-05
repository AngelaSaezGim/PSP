package concurrente;

import java.util.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		Interface_Sumar interf = (x, y) -> System.out.println(x+y);
		
		System.out.println("Introduzca 2 números para sumarlos: ");
		int a = s.nextInt();
		int b = s.nextInt();
		interf.sumar(a,b);
	}

	public interface Interface_Sumar{
		public void sumar(int a, int b);
	}
}
