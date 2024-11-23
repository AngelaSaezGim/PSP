/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arraylistEjercicio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author angsaegim
 */
public class SumaYMediaArrayList_Angela {

    /*
    EJERCICIO:
Calcular la suma y la media aritmética de los valores contenidos en un ArrayList.
    
Se desea crear un programa Java que lea una serie de valores numéricos enteros, desde el teclado, y
los guarde en un ArrayList de tipo Integer. La lectura de números enteros acaba cuando se
introduzca el valor -99. Este valor no se guarda en el ArrayList. A continuación, el programa
mostrará por pantalla el número de valores que se ha leído, su suma y su media. Finalmente se
mostrarán todos los valores leídos, indicando cuantos de ellos son mayores que la media.
Se han de crear 3 métodos:
 Un método para a leer valores.
 Un método para a calcular la suma.
 Un método para a mostrar los resultados.
     */
    private static Scanner tcl = new Scanner(System.in);
    public static ArrayList<Integer> lista = new ArrayList<>();
    public static ArrayList<Integer> valoresMayorQueMedia = new ArrayList<>();

    public static void main(String[] args) {

        int input = 0;

        while (input != -99) {
            System.out.println("Dime un numero entero para agregar a la lista (pon -99 para acabar)");
            input = tcl.nextInt();
            if (input != -99) {
                lista = agregarArray(input); //Método para leer valores
            }
        }
        System.out.println("Lista creada");
        System.out.println(lista.toString());

        int sumaValoresArray=sumarValores(lista); //Método calcular la suma
        double mediaValoresArray=mediaValores(lista); //Método calcular la media
        valoresMayorQueMedia = compararValores(lista, mediaValoresArray); // Sacar una lista de los valores mayor a la media
        
        System.out.println("");
        mostrarResultados(lista, sumaValoresArray, mediaValoresArray, valoresMayorQueMedia); //Método mostrar resultados

    }

    public static ArrayList agregarArray(int input) {

        lista.add(input);
        System.out.println("Agregado valor " + input + " en la lista correctamente");
        System.out.println(lista.toString());
        return lista;

    }

    public static int sumarValores(ArrayList<Integer> lista) {

        int sumaArray = 0;

        for (int i = 0; i < lista.size(); i++) {
            int elementoSumar = lista.get(i);
            sumaArray += elementoSumar;
        }
        return sumaArray;
    }

    public static double mediaValores(ArrayList<Integer> lista) {

        double mediaArray = 0;
        int sumaArray = 0;

        for (int i = 0; i < lista.size(); i++) {
            int elementoSumar = lista.get(i);
            sumaArray += elementoSumar;
        }

        mediaArray = sumaArray / lista.size();
        return mediaArray;

    }
    
    public static ArrayList<Integer> compararValores(ArrayList<Integer> lista, double mediaValoresArray){ 
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i)>mediaValoresArray){
                 valoresMayorQueMedia.add(lista.get(i));
            }
        }
        return valoresMayorQueMedia;
        
    }
    
    public static void mostrarResultados(ArrayList<Integer> lista, int sumaValores, double mediaValores, ArrayList<Integer> valoresMayorQueMedia){
        
        System.out.println("Resumen de la lista");
        System.out.println("Lista = " + lista.toString());
        System.out.println("Suma de sus valores = " + sumaValores);
        System.out.println("Media de sus valores = " + mediaValores);
        System.out.println("Lista de los valores mayores que la media de la lista = " + valoresMayorQueMedia.toString());
    }

}
