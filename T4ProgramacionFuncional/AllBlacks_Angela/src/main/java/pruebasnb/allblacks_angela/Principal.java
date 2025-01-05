/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pruebasnb.allblacks_angela;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

/**
 *
 * @author angsaegim
 */
public class Principal {

    /*Iterator iter = listProfessionalByAge.iterator();
            while (iter.hasNext()) {
                System.out.println(" -> " + iter.next());
            }*/
    private enum MenuOption {
        QUERY_PROFESSIONAL, QUERY_FILTERS, QUERY_LIST_NAMES, EXIT
    };

    private enum MenuOption2 {
        QUERY_FILTER_GENDER, QUERY_FILTER_TYPE, QUERY_FILTER_AGE, BACK
    };

    private static Scanner tcl = new Scanner(System.in);

    private static ArrayList<Profesional> professionalList = new ArrayList<>(); // Lista para almacenar los profesionales

    public static void main(String[] args) {

        MenuOption choosenOption = null;
        MenuOption2 choosenOption2 = null;

        //datos para pruebas (se inserta desde el método introProfesional());
        /*Profesional p1 = new Profesional("Paco", 23, 'm', 2);
        Profesional p2 = new Profesional("Maria", 48, 'f', 1);
        Profesional p3 = new Profesional("Ana", 67, 'f', 3);
        Profesional p4 = new Profesional("Alex", 32, 'm', 2);

        professionalList.add(p1);
        professionalList.add(p2);
        professionalList.add(p3);
        professionalList.add(p4);*/

        do {
            printOptions();
            choosenOption = readChoice();

            switch (choosenOption) {
                case QUERY_PROFESSIONAL:
                    introProfesional();
                    break;
                case QUERY_FILTERS:
                    do {
                        printOptions2();
                        choosenOption2 = readChoice2();

                        switch (choosenOption2) {
                            case QUERY_FILTER_GENDER:
                                listProfessionalsByGender();
                                break;
                            case QUERY_FILTER_TYPE:
                                listProfessionalsByType();
                                break;
                            case QUERY_FILTER_AGE:
                                listProfessionalsByAge();
                                break;
                            case BACK:
                                System.out.println("Regresando al menu principal..");
                                break;
                        }
                    } while (choosenOption2 != MenuOption2.BACK);
                    break;
                case QUERY_LIST_NAMES:
                    listProfessionalsByName();
                    break;
                case EXIT:
            }

        } while (choosenOption != MenuOption.EXIT);

        System.out.println("Programa cerrado. Adios!");
        tcl.close();

    }

    public static void waitIntro() {
        System.out.println("Presione Enter para continuar...");
        tcl.nextLine();
    }

    private static MenuOption readChoice() {
        try {
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption.values()[choiceInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice();
        }
    }

    private static MenuOption2 readChoice2() {
        try {
            int choiceInt = Integer.valueOf(tcl.nextLine());
            return MenuOption2.values()[choiceInt - 1];
        } catch (RuntimeException re) {
            System.out.println("Opción inválida... Inténtelo otra vez.");
            return readChoice2();
        }
    }

    private static void printOptions() {
        StringBuilder sb = new StringBuilder()
                .append("\nSOFTWARE DE GESTIÓN DE LOS ALL BLACKS")
                .append("\n -------------------- \n")
                .append("Elija una opción:\n")
                .append("\t1) Introducir datos de un profesional \n")
                .append("\t2) Consulta con filtros \n")
                .append("\t3) Listado de nombres \n")
                .append("\t4) Salir \n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }

    private static void printOptions2() {
        StringBuilder sb = new StringBuilder()
                .append("\n Submenu de consultas")
                .append("\n -------------------- \n")
                .append("Elija una opción:\n")
                .append("\t1) Profesionales por genero \n")
                .append("\t2) Profesionales por tipo \n")
                .append("\t3) Profesionales por edad \n")
                .append("\t4) Volver al menú principal \n")
                .append("Opción: ");
        System.out.print(sb.toString());
    }

    public static void introProfesional() {

        System.out.println("Vamos a insertar un profesional en la empresa");

        // Validar nombre
        String inputName;
        do {
            System.out.println("Introduce su nombre:");
            inputName = tcl.nextLine().trim();
            if (inputName.isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Intenta de nuevo.");
            }
        } while (inputName.isEmpty());

        //Validar edad
        int inputAge = -1;
        while (inputAge < 0) {
            try {
                System.out.println("Introduce su edad:");
                inputAge = tcl.nextInt();
                if (inputAge < 0) {
                    System.out.println("Edad no válida. Debe ser un número mayor a 0");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Introduce un número entero.");
                tcl.next();
            }
        }

        //Validar genero
        boolean validGender = false;
        char inputGender;
        do {
            System.out.println("Introduce su género ('m' o 'f')");
            // Lee el primer carácter (solo uno) y lo convierte a minúscula (asi da igual si lo ponemos en mayus o minus)
            inputGender = tcl.next().toLowerCase().charAt(0);

            if (inputGender != 'm' && inputGender != 'f') {
                System.out.println("Carácter introducido no válido. Vuelve a intentar");
            } else {
                validGender = true;
            }

        } while (!validGender);

        //Validar tipo profesional
        boolean validProfessionalType = false;
        int inputProfessionalType;
        do {
            System.out.println("Introduce su profesión (número) = 1 (Jugador) , 2 (Directivo) o 3 (Fisio)");
            inputProfessionalType = tcl.nextInt();

            if (inputProfessionalType < 1 || inputProfessionalType > 3) {
                System.out.println("Número no correspondiente a ninguna de nuestras profesiones. Vuelve a intentar");
            } else {
                validProfessionalType = true;
            }

        } while (!validProfessionalType);

        Profesional newProfessional = new Profesional(inputName, inputAge, inputGender, inputProfessionalType);
        System.out.println("Nuevo empleado creado correctamente");
        System.out.println(newProfessional);

        professionalList.add(newProfessional);
        tcl.nextLine();
    }

    // mostraremos todos los profesionales de género masculino y luego todos los profesionales del género femenino.
    public static void listProfessionalsByGender() {
        
        System.out.println("***** LISTADO PROFESIONALES POR GENERO *******");

        if (professionalList.isEmpty()) {
            System.out.println("No hay profesionales registrados en la empresa.");
        } else {

            List<Profesional> listProfessionalMasc = professionalList.stream()
                    .filter(p -> p.getGender() == 'm')
                    .collect(Collectors.toList());

            if (listProfessionalMasc.isEmpty()) {
                System.out.println("No se encontraron profesionales del género masculino para listar");
            } else {
                System.out.println("Profesionales de género masculino");
                listProfessionalMasc.forEach(profesional -> System.out.println(" -> " + profesional));
            }

            List<Profesional> listProfessionalFem = professionalList.stream()
                    .filter(p -> p.getGender() == 'f')
                    .collect(Collectors.toList());

            System.out.println();

            if (listProfessionalFem.isEmpty()) {
                System.out.println("No se encontraron profesionales del género femenino para listar");
            } else {
                System.out.println("Profesionales de género femenino");
                listProfessionalFem.forEach(profesional -> System.out.println(" -> " + profesional));
            }
        }
        waitIntro();
    }

    //mostraremos todos los jugadores (tipo 1), luego todos los directivos (tipo 2) y, para finalizar, todos los fisioterapeutas (tipo 3) del club.
    public static void listProfessionalsByType() {
        
        System.out.println("***** LISTADO PROFESIONALES POR PROFESIÓN *******");
        
        if (professionalList.isEmpty()) {
            System.out.println("No hay profesionales registrados en la empresa.");
        } else {

            //Listamos jugadores (tipo 1)
            List<Profesional> listProfessionalPlayers = professionalList.stream()
                    .filter(p -> p.getProfesionalType() == 1)
                    .collect(Collectors.toList());

            if (listProfessionalPlayers.isEmpty()) {
                System.out.println("No se encontraron profesionales jugadores para listar");
            } else {
                System.out.println("Profesionales del tipo Jugador");
                listProfessionalPlayers.forEach(profesional -> System.out.println(" -> " + profesional));
            }

            System.out.println();

            //Listamos directivos (tipo 2)
            List<Profesional> listProfessionalDirectors = professionalList.stream()
                    .filter(p -> p.getProfesionalType() == 2)
                    .collect(Collectors.toList());

            if (listProfessionalDirectors.isEmpty()) {
                System.out.println("No se encontraron profesionales directivos para listar");
            } else {
                System.out.println("Profesionales del tipo Director");
                listProfessionalDirectors.forEach(profesional -> System.out.println(" -> " + profesional));
            }

            System.out.println();
            //Listamos fisios (tipo 3)
            List<Profesional> listProfessionalPhysio = professionalList.stream()
                    .filter(p -> p.getProfesionalType() == 3)
                    .collect(Collectors.toList());

            if (listProfessionalPhysio.isEmpty()) {
                System.out.println("No se encontraron profesionales fisios para listar");
            } else {
                System.out.println("Profesionales del tipo Fisioterapeuta");
                listProfessionalPhysio.forEach(profesional -> System.out.println(" -> " + profesional));
            }
        }

        waitIntro();
    }

    public static void listProfessionalsByAge() {

        System.out.println("***** LISTADO PROFESIONALES POR EDAD *******");

        Boolean validAge = true;
        int minAgeInput = 0;
        int maxAgeInput = 0;

        do {
            try {
                System.out.println("Dime la edad mínima para listar");
                minAgeInput = tcl.nextInt();
                tcl.nextLine();

                System.out.println("Dime la edad máxima hasta la cual listaremos");
                maxAgeInput = tcl.nextInt();
                tcl.nextLine();

                //Se validará que el rango es válido
                if (minAgeInput > maxAgeInput) {
                    System.out.println("La edad mínima no puede ser mayor que la edad máxima");
                    validAge = false;
                } else {
                    validAge = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
                tcl.nextLine();
                validAge = false;
            }
        } while (!validAge);

        final int minAge = minAgeInput;
        final int maxAge = maxAgeInput;

        if (professionalList.isEmpty()) {
            System.out.println("No hay profesionales registrados en la empresa.");
        } else {
            List<Profesional> listProfessionalByAge = professionalList.stream()
                    .filter(p -> p.getAge() >= minAge && p.getAge() <= maxAge)
                    .collect(Collectors.toList());

            if (listProfessionalByAge.isEmpty()) {
                System.out.println("No se encontraron profesionales en el rango de edades especificado.");
            } else {
                System.out.println("Profesionales de " + minAge + " a " + maxAge + " años");
                listProfessionalByAge.forEach(profesional -> System.out.println(" -> " + profesional));
            }
        }
        waitIntro();
    }
    
        /*
    Listado de nombres: codificamos un stream quedándonos solo con el nombre del
    Profesional y luego los mostraremos separados por una coma (“,”) y finalizando el
    stream con un punto (“.”).
     */
    public static void listProfessionalsByName() {
        if (professionalList.isEmpty()) {
            System.out.println("No hay profesionales registrados en la empresa.");
        } else {
            String profesionalsName = professionalList.stream()
                    .map(p -> p.getName())
                    .collect(Collectors.joining(", ", "Nombres : ", "."));
            
            System.out.println(profesionalsName);
        }

        waitIntro();
    }
}
