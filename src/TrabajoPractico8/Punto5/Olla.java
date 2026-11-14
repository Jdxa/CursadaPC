package TrabajoPractico8.Punto5;

import java.util.concurrent.Semaphore;

public class Olla {
    private int raciones;
    private int cant;
    private boolean cocineroAvisado = false;
    private int esperando = 0;
    // semaforos
    private Semaphore mutex = new Semaphore(1); // exclusion mutua
    private Semaphore semCocinar = new Semaphore(0); // avisa al cocinero de cocinar
    private Semaphore semComer = new Semaphore(0, true); // bloquea al hilo xq no hay raciones

    public Olla(int n) {
        this.raciones = n;
        this.cant = n;
    }

    // el canibal
    public void comer() throws InterruptedException {
        // no hay raciones
        mutex.acquire(); // 1. Tomamos el mutex ANTES de revisar cuántas raciones quedan
        while (raciones == 0) { // Usamos while por seguridad al despertar
            if (!cocineroAvisado) {
                System.out.println(Thread.currentThread().getName() + " no hay raciones, le avisa al cocinero");
                cocineroAvisado = true;

                semCocinar.release();
            } else {
                System.out.println(Thread.currentThread().getName() + " olla vacia, espera al cocinero");

            }
            esperando++;
            mutex.release(); // Soltamos el mutex antes de dormirnos (evita deadlock)
            semComer.acquire(); // Nos dormimos esperando que el cocinero llene la olla
            mutex.acquire(); // Al despertar, retomamos el mutex inmediatamente
        }

        // 2. Si hay raciones, comemos de forma segura
        if (esperando > 0) {
            esperando--;
        }
        raciones--;
        System.out.println(Thread.currentThread().getName() + " come una racion. Raciones restantes: " + raciones);
        mutex.release();
    }

    // cocinero
    public void cocinar() throws InterruptedException {
        while (true) {
            semCocinar.acquire(); // si tiene que cocinar agarra el permiso
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " cocinar las raciones y la pone en la olla");
            raciones = cant;
            cocineroAvisado = false;
            System.out.println(Thread.currentThread().getName() + " le aviso al canibal que me pidio que coma");

            semComer.release(esperando);

            // los habilito a comer
            mutex.release();
        }
    }

}
