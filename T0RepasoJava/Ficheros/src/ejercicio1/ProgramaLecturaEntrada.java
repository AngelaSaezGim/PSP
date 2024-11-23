/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author angsaegim
 */
public class ProgramaLecturaEntrada {
    
    /*
    Ejercicio 1. Escribe un programa que lea un archivo de texto llamado "entrada.txt", cuente el
    número de palabras en cada línea y escriba el resultado en un nuevo archivo llamado "salida.txt".
    
     Utiliza las clases eficientes vistas anteriormente.
     El método "split()" de la clase String ayuda a dividir las palabras utilizando un delimitador.
     Asegurate de tener un archivo llamado "entrada.txt" con contenido de prueba en el mismo
    directorio que tu programa.
     Sube la solución a Aules (formato .java) y una captura (o capturas) de la ejecución. Todo,
    dentro de una carpeta llamada "UD0-3_Ejercicio1".
    */

    public static void main(String[] args) throws IOException {

        File  archivoFileEntrada = null;
        File archivoFileSalida = null;

        //Crear objeto File (archivo a leer)
        archivoFileEntrada = new File("entrada.txt");
        archivoFileSalida = new File("salida.txt");
        
        archivoFileSalida=comprobarSalida(archivoFileSalida);

        leerFile(archivoFileEntrada);
        
        archivoFileSalida = contarPalabrasYEscribir(archivoFileEntrada,archivoFileSalida);
        
        leerFile(archivoFileSalida);
        
        // Lo borro para q cada vez q ejecutemos se lea y escriba
        archivoFileSalida.delete();

    }
    
    public static File comprobarSalida(File archivoFileSalida){
        
        if (archivoFileSalida.exists()) {
            System.out.println("El fichero de salida existe"); //Existira siempre(lo creamos previamente en codigo)
            System.out.println("");
        } else {
            System.out.println("El fichero de salida no existe. Lo creamos");
            System.out.println("");
            try {
            if (archivoFileSalida.createNewFile()) {
                System.out.println("Fichero creado correctamente!");
                System.out.println("");
            } else {
                System.out.println("No se ha podido crear el fichero!");
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return archivoFileSalida;
    }

    public static void leerFile(File archivoFile) throws IOException {

        FileReader lectorReader = null;
        BufferedReader brR = null;

        try {
            //Despues objeto FileReader (abrir archivo para lectura)
            lectorReader = new FileReader(archivoFile);
            //Leer de forma eficiente FileReader->BufferedReader
            brR = new BufferedReader(lectorReader);

            System.out.println("[Leyendo " + archivoFile.getName() + "]");
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

    public static File contarPalabrasYEscribir(File archivoFileEntrada, File archivoFileSalida) throws IOException {

        FileReader lectorReader = null;
        BufferedReader brR = null;
        int contadorLineas=0;
        ArrayList<Integer> contadorPalabrasPorLinea = new ArrayList<>();

        try {
            lectorReader = new FileReader(archivoFileEntrada);
            brR = new BufferedReader(lectorReader);
            String linea;
            
            while ((linea = brR.readLine()) != null) {
                contadorLineas++;
                String[] palabras = linea.split(" "); //Divide por espacio en blanco
                contadorPalabrasPorLinea.add(palabras.length);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //UNA VEZ SE ACABA DE LEER - se cierra el archivo (con el try
            brR.close();
        }     
        //CLASE FileWriter
        FileWriter escritorWriter = null;
        //CLASE BufferedWritter
        BufferedWriter brW = null;

        try {
            escritorWriter = new FileWriter(archivoFileSalida);
            brW = new BufferedWriter(escritorWriter);
            brW.write("Numero de lineas del fichero de entrada " + contadorLineas);
            brW.newLine(); // Añade un salto de línea
            brW.write("Número de palabras en cada línea:");
            brW.newLine();
            for (int i = 0; i < contadorPalabrasPorLinea.size(); i++) {
                brW.write("Línea " + (i + 1) + ": " + contadorPalabrasPorLinea.get(i) + " palabras");
                brW.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            brW.flush();
            brW.close();
        }
        return archivoFileSalida;
    }
}
