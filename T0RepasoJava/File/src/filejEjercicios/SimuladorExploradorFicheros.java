/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filejEjercicios;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author angsaegim
 */
public class SimuladorExploradorFicheros {

    //  crea un programa que simule un explorador de ficheros y directorios por consola.
    /*
    El programa ha de tener, como a mínimo, les siguientes opciones:
    1. Mostrar ficheros del directorio actual
    2. Modificar el directorio actual
    3. Volver atrás
    4. Salir del programa
     */
    private static Scanner tlc = new Scanner(System.in);

    //Opciones del menú principal
    private enum MenuOption {
        QUERY_MOSTRARFICHEROS, QUERY_MODIFICARFICHERO, QUERY_CONSULTARPERMISOS, QUERY_ATRAS, EXIT
    };

    public static void main(String[] args) {

        File miDirectorio;
        
        miDirectorio = solicitarRuta();

        MenuOption opcionElegida = null;

        do {
            System.out.println("CARGADO FICHERO " + miDirectorio.getName() + " en " + miDirectorio.getAbsolutePath());
            System.out.println("1. Mostrar sus ficheros");
            System.out.println("2. Modificar el directorio actual");
            System.out.println("3. Consultar Permisos del fichero");
            System.out.println("4. Atras");
            System.out.println("5. Salir");

            opcionElegida = leerEnum();
            switch (opcionElegida) {
                case QUERY_MOSTRARFICHEROS:
                    System.out.println("Mostrando sus ficheros");
                    mostrarFicheros(miDirectorio);
                    break;
                case QUERY_MODIFICARFICHERO:
                    System.out.println("Modificamos ruta ((añade al path actual el directorio introducido)");
                    miDirectorio = modificarRuta(miDirectorio);
                    break;
                case QUERY_CONSULTARPERMISOS:
                    System.out.println("Consultando permisos de " + miDirectorio.getName());
                    consultarPermisos(miDirectorio);
                    break;
                case QUERY_ATRAS:
                    System.out.println("Atrás");
                    miDirectorio = solicitarRuta();
                    break;
                case EXIT:
                    System.out.println("MENU CERRADO");
                    tlc.close();
                    break;
            }
        } while (opcionElegida != MenuOption.EXIT);

    }
    
     private static File solicitarRuta() {
        File miDirectorio;
        do {
            System.out.println("Dime la ruta del fichero:");
            String rutaFichero = tlc.nextLine();
            miDirectorio = new File(rutaFichero);
            if (miDirectorio.exists()) {
                System.out.println("El directorio " + miDirectorio.getName() +  " existe!");
            } else {
                System.out.println("El directorio " + miDirectorio.getName() + " no existe!");
            }
        } while (!miDirectorio.exists());
        return miDirectorio;
    }
     

    private static MenuOption leerEnum() {
        try {
            int opcionInt = Integer.valueOf(tlc.nextLine());
            return MenuOption.values()[opcionInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return leerEnum();
        }
    }

    private static void mostrarFicheros(File directorio) {
        
        String[] ficherosList = directorio.list();
        
        for (int i = 0; i < ficherosList.length; i++) {
            System.out.print("["+(i+1)+"]" + " " + ficherosList[i] + " -> ");
            // Crea un objeto File para cada entrada de la lista
            File f = new File(directorio, ficherosList[i]);
            if (f.isFile()) {
                System.out.print(f.getName() + " es un fichero");
            }
            if (f.isDirectory()) {
                System.out.print(f.getName() + " es un directorio");
            }
            System.out.println("");
        }

    }

    private static File modificarRuta(File directorio) {
        
         do {
        System.out.println("Modificamos a partir de " + directorio.getName() + " en " + directorio.getAbsolutePath());
        System.out.println("Dime la ruta a seguir del fichero:");
        String rutaAñadida = directorio.getAbsolutePath() + tlc.nextLine();
        directorio = new File(rutaAñadida);
            if (directorio.exists()) {
                System.out.println("El nuevo directorio " + rutaAñadida + " existe!");
            } else {
                System.out.println("El nuevo directorio " + rutaAñadida + " no existe!");
            }
        } while (!directorio.exists());
        return directorio;
    }
    
     private static File consultarPermisos(File directorio) {
         
        System.out.println("-> LECTURA");
           if (directorio.canRead()) {
            System.out.println(directorio.getName() + " tiene permisos de lectura");
        } else {
            System.out.println(directorio.getName() + " NO tiene permisos de lectura");
        }
        System.out.println("-> ESCRITURA");
        if (directorio.canWrite()) {
            System.out.println(directorio.getName() + " tiene permisos de escritura");
        } else {
            System.out.println(directorio.getName() + " NO tiene permisos de escritura");
        }
        System.out.println("-> EJECUCIÓN");
        if (directorio.canExecute()) {
            System.out.println(directorio.getName() + " tiene permisos de ejecución");
        } else {
            System.out.println(directorio.getName() + " NO tiene permisos de ejecución");
        }
        System.out.println("");
         return directorio;
     }

}
