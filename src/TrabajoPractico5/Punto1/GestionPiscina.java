package TrabajoPractico5.Punto1;

import java.util.concurrent.Semaphore;

public class GestionPiscina {
    private int capacidad;
    private Semaphore semCapacidad;
    // cantidad de espacios en la piscina

    public GestionPiscina(int cap) {
        this.capacidad = cap;
        this.semCapacidad = new Semaphore(capacidad, true); // orden fifo para la salida
    }

    public void ingresarPiscina() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " intenta entrar a la piscina...");
        if (!semCapacidad.tryAcquire()) {
            // si no pudo entrar
            System.out.println(Thread.currentThread().getName() + " no pudo entrar, espera afuera.");
            semCapacidad.acquire();
        }
        System.out.println(Thread.currentThread().getName() + " entro a la piscina");
    }

    public void salirPiscina() {
        System.out.println(Thread.currentThread().getName() + " sale de la piscina");
        semCapacidad.release();
    }

}
