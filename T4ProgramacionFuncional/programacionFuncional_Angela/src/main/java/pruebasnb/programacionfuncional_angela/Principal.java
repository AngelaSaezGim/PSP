/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasnb.programacionfuncional_angela;

/**
 *
 * @author angsaegim
 */
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Runnable hilo1 = () -> {

            for (int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
                try {
                    Thread.sleep(3000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable hilo2 = () -> {
            //con el try catch el scanner ya se cerrará automaticamente
            try ( Scanner tcl = new Scanner(System.in)) {
                for (int i = 1; i <= 3; i++) {
                    String input = tcl.nextLine();
                    synchronized (System.out) { // Asi no se mezcla
                        System.out.println("Has escrito : " + input + "(" + i + "/3)");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread h1 = new Thread(hilo1);
        Thread h2 = new Thread(hilo2);

        h1.start();
        h2.start();
    }

}
