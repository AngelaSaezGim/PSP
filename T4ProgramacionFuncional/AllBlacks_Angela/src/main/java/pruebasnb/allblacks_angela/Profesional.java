/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasnb.allblacks_angela;

/**
 *
 * @author angsaegim
 */
public class Profesional {

    private String name;
    private int age;
    private char gender; // 'm' masculino, 'f' femenino
    private int profesionalType; // 1: Jugador, 2: Directivo, 3: Fisio

    public Profesional(String name, int age, char gender, int profesionalType) {
        this.name = name;
        this.age = validAge(age);
        this.gender = validGender(gender);
        this.profesionalType = validProfesionalType(profesionalType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //validación edad - no puede ser negativa (aunque luego por facilidad del usuario en el main lo vuelvo a hacer)
    public int validAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("La edad debe ser mayor que 0.");
        }
        return age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = validAge(age);
    }

    //validación genero (aunque luego por facilidad del usuario en el main lo vuelvo a hacer)
    public char validGender(char gender) {
        if (gender == 'm' || gender == 'f') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("El género debe ser 'm' (mascúlino) o 'f' (femenino).");
        }
        return gender;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = validGender(gender);
    }

    //validación profesional (aunque luego por facilidad del usuario en el main lo vuelvo a hacer)
    public int validProfesionalType(int profesionalType) {
        if (profesionalType >= 1 && profesionalType <= 3) {
            this.profesionalType = profesionalType;
        } else {
            throw new IllegalArgumentException("El tipo de profesional debe ser 1 (Jugador), 2 (Directivo), o 3 (Fisio).");
        }
        return profesionalType;
    }

    public int getProfesionalType() {
        return profesionalType;
    }

    public void setProfesionalType(int profesionalType) {
        this.profesionalType = validProfesionalType(profesionalType);

    }

    @Override
    public String toString() {
        String profesionalTypeStr = "";
        String genderStr = "";

        switch (profesionalType) {
            case 1:
                profesionalTypeStr = "Jugador";
                break;
            case 2:
                profesionalTypeStr = "Directivo";
                break;
            case 3:
                profesionalTypeStr = "Fisio";
                break;
            default:
                break;
        }
        
        switch (gender) {
            case 'm':
                genderStr = "másculino";
                break;
            case 'f':
                genderStr = "femenino";
                break;
            default:
                break;
        }

        return  name 
                + " de " + age + " años, "
                + "del género " + genderStr
                + " y de tipo " + profesionalTypeStr;
    }

}
