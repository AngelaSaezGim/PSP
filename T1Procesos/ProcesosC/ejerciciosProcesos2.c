#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/wait.h>

int main(void){

    printf("----EJERCICIO2---- \n");

      __pid_t proceso;

       proceso = fork(); //FORK 1- primer hijo
    if ( proceso < 0) { //Referencia padre
    } else if (proceso == 0) { // HIJO 1
        // Proceso hijo 1
        printf("Yo soy el proceso: %d y HIJO 1 mi padre es: %d\n", getpid(), getppid());
         // HIJO 1 = NIETO 1
        proceso = fork();
        if (proceso < 0) { //Referencia padre
        } else if (proceso == 0) {
            // Nieto 1 del hijo 1
            printf("Yo soy el proceso: %d y NIETO 1 del HIJO 1, mi padre es (hijo 1): %d\n", getpid(), getppid());
        }
    }
    else {
        proceso = fork(); //FORK 2 - segundo hijo
        if (proceso < 0) { //PADRE
        } else if (proceso == 0) { // HIJO 2
            // Proceso hijo 2
            printf("Yo soy el proceso: %d y HIJO 2 mi padre es: %d\n", getpid(), getppid());
             // HIJO 2 = NIETO 2
            proceso = fork();
            if (proceso < 0) { //Referencia padre
            } else if (proceso == 0) {
            // Nieto 1 del hijo 1
            printf("Yo soy el proceso: %d y NIETO 2 del HIJO 2, mi padre es (hijo 2): %d\n", getpid(), getppid());
            }
        } 
        else {
             //FINAL - REFERENCIAMOS PROCESO PADRE
                 // Proceso padre (espera a que terminen los hijos)
                wait(NULL);
                wait(NULL);
                printf("Yo soy el proceso: %d, SOY EL PADRE \n", getpid());
            }
    }

    return 0;
}