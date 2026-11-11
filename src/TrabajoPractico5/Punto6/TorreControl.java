package TrabajoPractico5.Punto6;

import java.util.concurrent.Semaphore;

public class TorreControl {
    private int aterrizajes = 0;
    private Semaphore semPista = new Semaphore(1); // Garantiza que solo un avión usa la pista a la vez
    private Semaphore semAter = new Semaphore(1); // Da prioridad inicial a los aterrizajes
    private Semaphore semDesp = new Semaphore(0); // Frena los despegues hasta que ocurran 10 aterrizajes

    public void aterrizar() throws InterruptedException {
        semAter.acquire(); // Espera su turno de aterrizaje
        semPista.acquire(); // Adquiere la pista exclusiva

        System.out.println(Thread.currentThread().getName() + " aterriza en la pista.");
        Thread.sleep(1500); // Simula el tiempo de aterrizaje

        aterrizajes++;

        // Si se cumplieron exactamente 10 aterrizajes consecutivos
        if (aterrizajes == 10) {
            aterrizajes = 0; // Reseteamos el contador
            semDesp.release(); // Damos paso obligatorio a un despegue (NO liberamos semAter, frenando los
                               // siguientes aterrizajes temporalmente)
        } else {
            semAter.release(); // Si no, permitimos que siga aterrizando otro avión
        }
        System.out.println(Thread.currentThread().getName() + " libera la pista.");

        semPista.release(); // Libera la pista
    }

    public void despegar() throws InterruptedException {
        semDesp.acquire(); // Espera a que los 10 aterrizajes obligatorios ocurran
        semPista.acquire(); // Adquiere la pista exclusiva

        System.out.println(Thread.currentThread().getName() + " despega de la pista.");
        Thread.sleep(1500); // Simula el tiempo de despegue
        System.out.println(Thread.currentThread().getName() + " libera la pista (despego).");

        semPista.release(); // Libera la pista

        semAter.release(); // Devolvemos el permiso para que los aterrizajes retomen su prioridad
    }
}