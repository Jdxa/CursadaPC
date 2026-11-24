package TrabajoPractico7.Punto3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cocina {
    private int cantVerduras;
    private int cantCarne;
    private int cantPasta;

    private final Lock cerrojo = new ReentrantLock();
    private final Condition condVerdura = cerrojo.newCondition();
    private final Condition condCarne = cerrojo.newCondition();
    private final Condition condPasta = cerrojo.newCondition();

    public Cocina(int verd, int carne, int pasta) {
        this.cantVerduras = verd;
        this.cantCarne = carne;
        this.cantPasta = pasta;
    }

    public void tomarVerdura() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantVerduras == 0) {
                System.out
                        .println(Thread.currentThread().getName() + " no hay verdura, debe esperar a que se repongan");
                condVerdura.await();
            }
            cantVerduras--;
            System.out.println(Thread.currentThread().getName() + " tomo una verdura. CantVerduras: " + cantVerduras);
        } finally {
            cerrojo.unlock();
        }
    }

    public void reponerVerdura() {
        cerrojo.lock();
        try {
            cantVerduras++;
            System.out.println(Thread.currentThread().getName()
                    + " termino de usar la verdura, la repuso. CantVerdura: " + cantVerduras);
            condVerdura.signal();
        } finally {
            cerrojo.unlock();
        }
    }

    public void tomarCarne() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantCarne == 0) {
                System.out
                        .println(Thread.currentThread().getName() + " no hay carne, debe esperar a que se repongan");
                condCarne.await();
            }
            cantCarne--;
            System.out.println(Thread.currentThread().getName() + " tomo una carne. CantCarne: " + cantCarne);
        } finally {
            cerrojo.unlock();
        }
    }

    public void reponerCarne() {
        cerrojo.lock();
        try {
            cantCarne++;
            System.out.println(
                    Thread.currentThread().getName() + " termino de usar la carne, la repuso. CantCarne: " + cantCarne);
            condCarne.signal();
        } finally {
            cerrojo.unlock();
        }
    }

    public void tomarPasta() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantPasta == 0) {
                System.out
                        .println(Thread.currentThread().getName() + " no hay pasta, debe esperar a que se repongan");
                condPasta.await();
            }
            cantPasta--;
            System.out.println(Thread.currentThread().getName() + " tomo una pasta. CantPasta: " + cantPasta);
        } finally {
            cerrojo.unlock();
        }
    }

    public void reponerPasta() {
        cerrojo.lock();
        try {
            cantPasta++;
            System.out.println(
                    Thread.currentThread().getName() + " termino de usar la pasta, la repuso. CantPasta: " + cantPasta);
            condPasta.signal();
        } finally {
            cerrojo.unlock();
        }
    }
}
