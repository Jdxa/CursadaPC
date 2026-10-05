
package test;

import java.util.concurrent.Semaphore;

public class Datos {
    private int dato;
    private Semaphore mutex;

    public Datos(int nro) {
        dato = nro;
        mutex = new Semaphore(1);
    }

    public int getDato() {
        return dato;
    }

    public void incrementar() throws InterruptedException {
        // me aseguro que suma y guarda uno con ese hilo y no interrumpe el otro la
        // operacion
        mutex.acquire(); // adquiero el sem permisos-1
        dato++;
        System.out.println("el hilo " + Thread.currentThread().getName() + " sumo " + dato);
        // Thread.sleep(500);
        mutex.release(); // lo libero al sem permisos+1
    }
}
