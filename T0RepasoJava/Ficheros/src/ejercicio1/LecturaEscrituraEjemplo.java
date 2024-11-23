/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author angsaegim
 */
public class LecturaEscrituraEjemplo {

    public static void main(String[] args) throws IOException {

        /*1. LEER*/
        //Se inicializan en null
        File archivoFile = null;

        //Crear objeto File (archivo a leer)
        archivoFile = new File("/home/angsaegim/Documents/archivo.txt");

        leerFile(archivoFile);

        /*2. ESCRIBIR*/
        //USAMOS EL FILE ANTERIOR
        /*File archivoFile = null; 
        archivoFile = new File("/home/angsaegim/Documents/archivo.txt");
         */
        escribirFile(archivoFile);
        
        leerFile(archivoFile);
    }

    public static void leerFile(File archivoFile) throws IOException {

        FileReader lectorReader = null;
        BufferedReader brR = null;

        try {
            //Despues objeto FileReader (abrir archivo para lectura)
            lectorReader = new FileReader(archivoFile);
            //Leer de forma eficiente FileReader->BufferedReader
            brR = new BufferedReader(lectorReader);

            //LECTURA FICHERO
            String linea;
            while ((linea = brR.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //UNA VEZ SE ACABA DE LEER - se cierra el archivo (con el try
            brR.close();
        }

    }

    public static void escribirFile(File archivoFile) throws IOException {

        //CLASE FileWriter
        FileWriter escritorWriter = null;
        //CLASE BufferedWritter
        BufferedWriter brW = null;

        try {
            escritorWriter = new FileWriter(archivoFile);
            brW = new BufferedWriter(escritorWriter);
            //Escribir archivo (puede ser en seco o string como parametro)
            String hola = "Holaaaaa, mundo!";
            brW.newLine(); // Añade un salto de línea
            brW.write(hola);
            brW.newLine(); // Añade un salto de línea
            brW.write("Escribiendo en achivos con Java. :)");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //UNA VEZ SE ACABA DE ESCRIBIR - se LIMPIA y cierra el archivo (con el try
            brW.flush();
            brW.close();
        }

    }

}
