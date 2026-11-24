package TrabajoPractico8.Punto5bis;

import java.util.concurrent.Semaphore;

public class Ollabis {
    private int n;
    private boolean avisado = false;

    public Ollabis(int n) {
        this.n = n;
        semRac = new Semaphore(n, true);
    }

    private Semaphore mutex = new Semaphore(1);
    private Semaphore semRac;
    private Semaphore semCocinar = new Semaphore(0);

    public void comer() throws InterruptedException {
        mutex.acquire();
        // toma racion si hay, sino avisa y espera a que haya
        if (!semRac.tryAcquire()) {
            // si no hay raciones...
            if (!avisado) {
                // hay que avisarle
                semCocinar.release(); // despierto al cocinero
                System.out.println(Thread.currentThread().getName() + " avisa");
                avisado = true; // si entro aca fue avisado ya

            }
            System.out.println(Thread.currentThread().getName() + " espera");
            mutex.release(); // suelto el mutex asi no me lo quedo
            semRac.acquire(); // lo mando a dormir
            mutex.acquire(); // se desperto tomo el mutex
        }
        // si hay raciones
        System.out.println(Thread.currentThread().getName() + " comio una racion");
        mutex.release(); // ya comio suelto el mutex

    }

    public void cocinar() throws InterruptedException {
        semCocinar.acquire(); // me duermo si no me pidieron que cocine
        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " cocina " + n + " raciones");
        avisado = false; // ya cocine y ya me avisaron
        semRac.release(n); // libero las n raciones
        mutex.release();
    }

}
