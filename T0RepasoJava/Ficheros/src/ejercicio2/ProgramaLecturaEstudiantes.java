package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author angsaegim
 */
public class ProgramaLecturaEstudiantes {

    /*
    Ejercicio 2. Escribe un programa en Java que lea un archivo CSV llamado "estudiantes.csv", donde
cada línea contiene el nombre del estudiante y sus calificaciones separadas por punto y coma, y
calcule la media de las calificaciones de cada estudiante. Después, escribe el nombre del estudiante
y su media en un nuevo archivo CSV llamado "medias.csv".
 El archivo "estudiantes.csv" con los datos a utilizar se creará con una hoja de cálculo. La
primera columna contendrá el nombre y las columnas siguientes las notes. Ten en cuenta, a
la hora de exportar el archivo como CSV, indicarle que delimite los datos por el caracter (;).
 El método "split()" de la clase String ayuda a dividir les palabras utilizando un delimitador.
 Sube la solución del código a Aules (formato .java) y una captura (o capturas) de la
ejecución. Todo, dentro de una carpeta llamada "UD0-3_Ejercicio2".
Ejemplo del archivo "estudiantes.csv":
     */
    public static void main(String[] args) throws IOException {

        File archivoAleer = null;
        File archivoFileSalida = null;

        archivoAleer = new File("estudiantes.csv");
        archivoFileSalida = new File("media.csv");

        archivoFileSalida = comprobarSalida(archivoFileSalida);

        leerFile(archivoAleer);

        archivoFileSalida = escribirMedia(archivoAleer, archivoFileSalida);

        System.out.println("");
        leerFile(archivoFileSalida);

        // Lo borro para q cada vez q ejecutemos se lea y escriba
        archivoFileSalida.delete();
    }

    public static File comprobarSalida(File archivoFileSalida) {

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

    public static File escribirMedia(File archivoFileEntrada, File archivoFileSalida) throws IOException {

        FileReader lectorReader = null;
        BufferedReader brR = null;

        FileWriter escritorWriter = null;
        BufferedWriter brW = null;

        try {
            lectorReader = new FileReader(archivoFileEntrada);
            brR = new BufferedReader(lectorReader);
            escritorWriter = new FileWriter(archivoFileSalida);
            brW = new BufferedWriter(escritorWriter);

            String linea;

            while ((linea = brR.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];  // guardamos nombres de estudiantesç
                double sumaNotas = 0;
                int cantidadNotas = datos.length - 1; //Quitamos la columna de nombres

                //sumamos las notas de cada persona
                for (int i = 1; i < datos.length; i++) {
                    sumaNotas += Integer.parseInt(datos[i]);
                }

                //calculo media con la syma de notas
                double media = sumaNotas / cantidadNotas;

                // Write the result to the output file
                brW.write(nombre + " = " + media);
                brW.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brR != null) {
                brR.close();
            }
            if (brW != null) {
                brW.flush();
                brW.close();
            }
        }

        return archivoFileSalida;
    }
}
