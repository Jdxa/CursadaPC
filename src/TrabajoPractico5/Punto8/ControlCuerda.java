package TrabajoPractico5.Punto8;

import java.util.concurrent.Semaphore;

public class ControlCuerda {
    /*
     * 5 babuinos max x vez
     * no se pueden chocar
     * si hay menos de 5 puedo usar un contador previo para ver si hay esperando y
     * ahi los cambio
     */

    // cantidad de monos x cuerda = 5, siempre empiezan desde la izquierda
    private Semaphore semIzqDer = new Semaphore(5);
    // este semaforo toma los permisos del otro
    private Semaphore semDerIzq = new Semaphore(0);

    private int contador = 0; // cantidad de babuinos actuales en la cuerda
    private int cantIzq = 0; // cuenta cantidad de babuinos que se acercan
    private int cantDer = 0; // lo mismo pero de la derecha

    private Semaphore proteccion = new Semaphore(1);

    public void acercarceBorde(String lado) throws InterruptedException {
        if (lado.equals("Izq")) {
            proteccion.acquire();
            cantIzq++; // cantidad esperando a la izquierda
            proteccion.release();
        } else {
            proteccion.acquire();
            cantDer++; // cantidad esperando a la derecha
            proteccion.release();
        }
    }

    public void subir(String lado) throws InterruptedException {
        if (lado.equals("Izq")) { // los separo segun su lado de la cuerda asignado al crearce
            // de izq a der
            semIzqDer.acquire();
            proteccion.acquire();
            contador++; // actualiza la cantidad actual en la cuerda
            cantIzq--; // controlo cuantos babuinos hay esperando subir
            proteccion.release();
        } else {
            // de der a izq
            semDerIzq.acquire();
            proteccion.acquire();
            contador++; // actualiza la cantidad actual en la cuerda
            cantDer--;// controlo cuantos babuinos hay esperando subir
            proteccion.release();
        }
        System.out.println("sube el mono: " + Thread.currentThread().getName());
    }

    public void bajar(String lado) throws InterruptedException {
        proteccion.acquire();
        System.out.println("baja el mono: " + Thread.currentThread().getName());
        contador--; // actualiza la cantidad actual en la cuerda

        if (contador == 0) { // si ya no hay mas babuinos en la cuerda (bajo el ultimo)
            System.out.println("---------------------------------------");
            // si el ultimo que bajo es de la izquierda
            if (lado.equals("Izq")) {
                // trato izq a der
                if (cantDer > 0) { // si aun hay babuinos a la derecha
                    int liberar = Math.min(5, cantDer); // libera exactamente la cantidad de babuinos esperando si es
                                                        // que son menos de 5, si son mas de 5 simplemente libera 5
                    semDerIzq.release(liberar);
                } // sino no intercambia
                else {
                    int liberar = Math.min(5, cantIzq);
                    semIzqDer.release(liberar);
                }
            } else { // si el ultimo que bajo es de la derecha
                // trato der a izq
                if (cantIzq > 0) { // si aun hay babuinos del lado izquierdo
                    int liberar = Math.min(5, cantIzq); // libera exactamente la cantidad de babuinos esperando si es
                                                        // que son menos de 5, si son mas de 5 simplemente libera 5
                    semIzqDer.release(liberar);
                } // sino no intercambia
                else {
                    int liberar = Math.min(5, cantDer);
                    semDerIzq.release(liberar);
                }
            }
        }
        proteccion.release();
    }
}