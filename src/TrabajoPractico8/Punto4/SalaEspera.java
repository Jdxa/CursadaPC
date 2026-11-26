package TrabajoPractico8.Punto4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaEspera {
    private int camillas = 4;
    private int revistas = 9;
    private Lock cerrojo = new ReentrantLock();
    private Condition conCamilla = cerrojo.newCondition();

    public void entrar() throws InterruptedException {
        cerrojo.lock();
        try {
            // entra un donante
            while (camillas == 0) {
                esperar();
            }
            System.out.println(Thread.currentThread().getName() + " usa una camilla...");
            camillas--;
        } finally {
            cerrojo.unlock();
        }
    }

    public void esperar() throws InterruptedException {
        cerrojo.lock();
        try {
            if (revistas == 0) {
                System.out.println(Thread.currentThread().getName() + " mira la tv. Revistas: " + revistas);
                conCamilla.await();
            } else {
                revistas--;
                System.out.println(Thread.currentThread().getName() + " toma una revista. Revistas: " + revistas);
                conCamilla.await();
                System.out.println(Thread.currentThread().getName() + " lo llaman. Revistas: " + revistas);
                revistas++;
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void salir() {
        cerrojo.lock();
        try {
            camillas++;
            System.out.println(Thread.currentThread().getName() + " libera una camilla. Camillas: " + camillas);
            conCamilla.signal();
        } finally {
            cerrojo.unlock();
        }
    }
}
