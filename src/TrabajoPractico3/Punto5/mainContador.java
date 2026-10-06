package TrabajoPractico3.Punto5;

import java.util.Random;

public class mainContador {
    public static void main(String[] args) {
        int[] arr = new int[50000];
        Random ran = new Random();
        //cargo el arreglo
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ran.nextInt(1,10); // Valores pequeños para evitar desbordar el int
        
        }

        int numHilos = 4;
        Thread[] hilos = new Thread[numHilos];
        Arreglo recurso = new Arreglo();

        int tamanoBloque = 50000 / numHilos;

        // Crear y arrancar los hilos con sus rangos proporcionales
        for (int h = 0; h < numHilos; h++) {
            int inicio = h * tamanoBloque;
            // El último hilo abarca el resto por si no es división exacta
            int fin = (h == numHilos - 1) ? 50000 : (inicio + tamanoBloque);

            Runnable tarea = new Contador(arr, inicio, fin, recurso);
            hilos[h] = new Thread(tarea, ""+h);
            hilos[h].start();
        }

        // Esperar a que todos los hilos terminen de ejecutarse
        for (int h = 0; h < numHilos; h++) {
            try {
                hilos[h].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el resultado final
        System.out.println("Suma total obtenida por los hilos: " + recurso.getSumaTotal());
    }
    
}
