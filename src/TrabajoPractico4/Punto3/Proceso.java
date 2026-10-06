package TrabajoPractico4.Punto3;

import java.util.concurrent.Semaphore;

public class Proceso {
    private final Semaphore sem1_3 = new Semaphore(0);
    private final Semaphore sem3_2 = new Semaphore(0);
    private final Semaphore sem2_1 = new Semaphore(1);

    public void procesarUno() throws InterruptedException {
        sem2_1.acquire();
        System.out.println("procesando p1");
        Thread.sleep(1000);
        sem1_3.release();
    }

    public void procesarTres() throws InterruptedException {
        sem1_3.acquire();
        System.out.println("procesando p3");
        Thread.sleep(1000);
        sem3_2.release();
    }

    public void procesarDos() throws InterruptedException {
        sem3_2.acquire();
        System.out.println("procesando p2");
        Thread.sleep(1000);
        sem2_1.release();
    }
}
