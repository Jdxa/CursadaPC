package TrabajoPractico2.Punto6;

import java.util.Scanner;

public class CarreraMultiThread {
    public static void main(String[] args) {
        int cantidadCorredores;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese cuantos corredores van a participar: ");
        cantidadCorredores = sc.nextInt();

        // 1. Crear corredores e hilos
        Corredor[] corredores = inicializarCorredores(cantidadCorredores);
        Thread[] hilos = lanzarCorredores(corredores);

        // 2. Sincronizar (esperar a que todos terminen)
        esperarFinDeCarrera(hilos);

        // 3. Obtener y mostrar el resultado
        Corredor ganador = encontrarGanador(corredores);
        System.out.println("\n--- RESULTADO FINAL ---");
        System.out.println("El ganador es " + ganador.getNombre() +
                " con una distancia de: " + ganador.getDistanciaRecorrida());
        sc.close();
    }

    // Cargo los corredores
    private static Corredor[] inicializarCorredores(int cantidad) {
        Corredor[] corredores = new Corredor[cantidad];
        for (int i = 0; i < cantidad; i++) {
            corredores[i] = new Corredor("Corredor #" + (i + 1));
        }
        return corredores;
    }

    // Los hago correr a los corredores
    private static Thread[] lanzarCorredores(Corredor[] corredores) {
        Thread[] hilos = new Thread[corredores.length];
        for (int i = 0; i < corredores.length; i++) {
            hilos[i] = new Thread(corredores[i]);
            hilos[i].start();
        }
        return hilos;
    }

    // espero a que termine la carrera
    private static void esperarFinDeCarrera(Thread[] hilos) {
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Un hilo fue interrumpido: " + e.getMessage());
            }
        }
    }

    private static Corredor encontrarGanador(Corredor[] corredores) {
        Corredor ganador = corredores[0];
        for (int i = 1; i < corredores.length; i++) {
            if (corredores[i].getDistanciaRecorrida() > ganador.getDistanciaRecorrida()) {
                ganador = corredores[i];
            }
        }
        return ganador;
    }

}
