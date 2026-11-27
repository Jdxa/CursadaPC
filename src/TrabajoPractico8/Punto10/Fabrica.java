package TrabajoPractico8.Punto10;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Fabrica {
    private final int[] cap = new int[3]; // capacidad max de la caja
    private int[] cant = new int[3]; // cant actual
    private Lock cerrojo = new ReentrantLock();
    private Condition[] conProd;
    private Condition conEns = cerrojo.newCondition();

    public Fabrica(int capR, int capP, int capC, int equipos) {
        this.cap[0] = capR; // ruedas max
        this.cap[1] = capP; // puertas max
        this.cap[2] = capC; // carroceria max
        this.conProd = new Condition[equipos];
        for (int i = 0; i < equipos; i++) {
            this.conProd[i] = cerrojo.newCondition();
        }
    }

    public void producir(int i) throws InterruptedException {
        cerrojo.lock();
        try {
            while (cant[i] == cap[i]) {
                conProd[i].await();
            }
            cant[i]++;
            System.out.println(Thread.currentThread().getName() + " produjo. Su caja: " + cant[i]);
            conEns.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public void ensamblar() throws InterruptedException {
        cerrojo.lock();
        try {
            while (!consultar()) {
                conEns.await();
            }
            cant[0] = cant[0] - 4;
            cant[1] = cant[1] - 2;
            cant[2] = cant[2] - 1;
            System.out.println(Thread.currentThread().getName() + " ensamblo un auto");
            System.out.println("Ruedas: " + cant[0]);
            System.out.println("Puertas: " + cant[1]);
            System.out.println("Carrocerias: " + cant[2]);
            for (int i = 0; i < 3; i++) {
                conProd[i].signalAll();
            }
        } finally {
            cerrojo.unlock();
        }
    }

    private boolean consultar() {
        boolean puede = false;
        puede = (cant[0] >= 4 && cant[1] >= 2 && cant[2] >= 1);
        return puede;
    }
}
