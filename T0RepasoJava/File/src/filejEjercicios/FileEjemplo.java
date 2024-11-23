/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filejEjercicios;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author angsaegim
 */
public class FileEjemplo {

    public static void main(String[] args) {

        System.out.println();
        //CREAR UN FICHERO
        // crear una instancia de la clase «File» con una ruta determinad
        File miFichero = new File("pruebaFichero.txt");
        try {
            //Creamos el fichero con método createNewFilee
            if (miFichero.createNewFile()) {
                System.out.println("Fichero creado correctamente!");
            } else {
                System.out.println("No se ha podido crear el fichero!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CREAR UN DIRECTORIO 
        //Instanciamos la clase File con la ruta del fichero
        File miDirectorio = new File("/home/angsaegim/Escriptori/directori_nou");
        //Creamos el directorio
        miDirectorio.mkdir();
        
        System.out.println();
        //COMPROBAR SI EXISTE FICHERO
        //Comprobamos si existe
        //Podriamos intanciar otro que no estuviese en codigo tmb
        if (miFichero.exists()) {
            System.out.println("El fichero existe!"); //Existira siempre(lo creamos previamente en codigo)
        } else {
            System.out.println("El fichero no existe!");
        }
        System.out.println();
        
        //COMPROBAR TIPO FICHERO (archivo o directorio)
        //Comprobamos si miFichero ES UN FICHERO
        if (miFichero.isFile()) {
            System.out.println(miFichero.getName() + " es un fichero");
        }
        System.out.println();
        //Comprobamos si miDirectorio ES UN DIRECTORIO
        if (miDirectorio.isDirectory()) {
            System.out.println(miDirectorio.getName() + " es un directorio");
        }

        //COMPROBAR PERMISOS DE UN FICHERO 
        // Booleans - LEER canRead(), ESCRIBIR canWrite() Y EJECUTAR canExecute()
        //Comprobamos los permisos de lectura
        if (miFichero.canRead()) {
            System.out.println(miFichero.getName() + " tiene permisos de lectura");
        } else {
            System.out.println(miFichero.getName() + " NO tiene permisos de lectura");
        }
        // Comprobamos los permisos de escritura
        if (miFichero.canWrite()) {
            System.out.println(miFichero.getName() + " tiene permisos de escritura");
        } else {
            System.out.println(miFichero.getName() + " NO tiene permisos de escritura");
        }
        //Comprobamos los permisos de ejecución
        if (miFichero.canExecute()) {
            System.out.println(miFichero.getName() + " tiene permisos de ejecución");
        } else {
            System.out.println(miFichero.getName() + " NO tiene permisos de ejecución");
        }
        System.out.println();

        //CAMBIAR PERMISOS DE UN FICHERO
        // SETS
        //Modificamos los permisos - TODO EN FALSO
        miFichero.setExecutable(false);
        miFichero.setReadable(false);
        miFichero.setWritable(false);
        System.out.println("Permisos cambiados correctamente (todos en false)");

        System.out.println();
        //OBTENER NOMBRE FICHERO
        //Mostramos el nombre del fichero
        System.out.println(miFichero.getName());

        System.out.println();
        //OBTENER RUTA COMPLETA FICHERO
        //Mostramos la ruta completa del fichero
        System.out.println(miFichero.getAbsolutePath());

        System.out.println();
        //OBTENER RUTA RELATIVA (ruta padre)
        //Mostramos la ruta completa del fichero sin incluir el fichero
        System.out.println(miFichero.getParent());

        System.out.println();
        //LISTAR TODOS LOS ARCHIVOS Y DIRECTORIOS
        //OPCION 1
        System.out.println("Opcion 1 listar ficheros");
        //Obtenemos el Array de archivos
        File miFichero2 = new File("/home/angsaegim/Escriptori");
        File[] ficheros = miFichero2.listFiles();
        //Iteramos sobre el Array y además comprobamos si es fichero o directorio
        for (File f : ficheros) {
            System.out.println(f);
            if (f.isFile()) {
                System.out.println(f.getName() + " es un fichero");
            }
            if (f.isDirectory()) {
                System.out.println(f.getName() + " es un directorio");
            }
        }

        //OPCION 2
        System.out.println("");
        System.out.println("Opcion 2 listar ficheros");
        //Obtenemos el Array de los nombres del contenido
        String[] ficherosList = miFichero2.list();
        //Iteramos sobre el Array y los mostramos por pantalla
        for (int i = 0; i < ficherosList.length; i++) {
            System.out.println(ficheros[i]);
        }

        //BORRAR UN FICHERO Y DIRECTORIO
        miFichero.delete();
        miDirectorio.delete();
    }
}
