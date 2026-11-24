package TrabajoPractico5.Punto7;

import java.util.concurrent.Semaphore;

public class Mirador {
    private Semaphore semEscalera;
    private Semaphore semBajar = new Semaphore(2);
    private Semaphore quieroBajar = new Semaphore(0);
    private Semaphore habilita = new Semaphore(0);

    public Mirador(int cantPersonas) {
        this.semEscalera = new Semaphore(cantPersonas);
    }

    public void subirEscalera() throws InterruptedException {
        semEscalera.acquire();
        System.out.println(Thread.currentThread().getName() + " sube por la escalera");
        Thread.sleep(1500);
        quieroBajar.release();
    }

    // encargado
    public void habilitarBajada() throws InterruptedException {
        quieroBajar.acquire();
        System.out.println(Thread.currentThread().getName() + " permite bajar");
        habilita.release();
    }

    public void bajarTobogan() throws InterruptedException {
        habilita.acquire();
        semBajar.acquire();
        System.out.println(Thread.currentThread().getName() + " bajo por el tobogan");
        habilita.release();
        semEscalera.release();
        semBajar.release();
    }
}
