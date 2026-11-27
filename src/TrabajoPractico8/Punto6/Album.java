package TrabajoPractico8.Punto6;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Album {
    private final int durMax;
    private int durActual = 0;
    private boolean promocionar = false;
    private Lock cerrojo = new ReentrantLock();
    private Condition conProd = cerrojo.newCondition();
    private Condition conMan = cerrojo.newCondition();

    public Album(int durMax) {
        this.durMax = durMax;
    }

    // productor
    public void agregar(int dur) throws InterruptedException {
        cerrojo.lock();
        try {
            if (durActual + dur > durMax) {
                promocionar = true;
                conMan.signal();
            }
            while (promocionar) {
                conProd.await();
            }
            System.out.println(Thread.currentThread().getName() + " agrego nueva cancion con dur: " + dur);
            durActual = dur + durActual;
        } finally {
            cerrojo.unlock();
        }
    }

    public void promocionar() throws InterruptedException {
        cerrojo.lock();
        try {
            while (!promocionar) {
                conMan.await();
            }
            System.out.println(
                    Thread.currentThread().getName() + " promociona el album de Bob... (" + durActual + " / " + durMax);
            durActual = 0;
            promocionar = false;
            conProd.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
}
