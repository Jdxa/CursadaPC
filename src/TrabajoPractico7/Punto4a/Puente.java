package TrabajoPractico7.Punto4a;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Puente {
    private Lock cerrojo = new ReentrantLock(true);
    // colas de entrada
    private Condition colaSur = cerrojo.newCondition();
    private Condition colaNorte = cerrojo.newCondition();
    // colas de salida
    private Condition salidaSur = cerrojo.newCondition();
    private Condition salidaNorte = cerrojo.newCondition();
    // cola que registra el orden de entrada
    private Queue<String> cola = new LinkedList<>();
    private int contAutos = 0;

    private boolean puedeSur = true;
    private boolean puedeNorte = false;

    public void pasarSurNorte() throws InterruptedException {
        cerrojo.lock();
        try {

            while (contAutos >= 10 || !puedeSur) {
                colaSur.await(); // lo hago esperar en la cola del sur
            }
            System.out.println(Thread.currentThread().getName() + " entro al puente");
            contAutos++;
            cola.add(Thread.currentThread().getName());// agrego el hilo a la cola

        } finally {
            cerrojo.unlock();
        }
    }

    public void salirSurNorte() throws InterruptedException {
        cerrojo.lock();
        try {
            // lo hago esperar si no es su turno para salir
            while (!cola.peek().equals(Thread.currentThread().getName())) {
                salidaSur.await();
            }
            // es su turno, lo saco de la cola
            cola.poll();
            contAutos--;
            System.out.println(Thread.currentThread().getName() + " salio del puente");
            if (contAutos == 0) {
                colaNorte.signalAll();
                puedeSur = false;
                puedeNorte = true;

            }
            salidaSur.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public void pasarNorteSur() throws InterruptedException {
        cerrojo.lock();
        try {

            while (contAutos >= 10 || !puedeNorte) {
                colaNorte.await();
            }
            System.out.println(Thread.currentThread().getName() + " entro al puente");
            contAutos++;
            cola.add(Thread.currentThread().getName());// agrego el hilo a la cola

        } finally {
            cerrojo.unlock();
        }
    }

    public void salirNorteSur() throws InterruptedException {
        cerrojo.lock();
        try {
            // lo hago esperar si no es su turno para salir
            while (!cola.peek().equals(Thread.currentThread().getName())) {
                salidaNorte.await();
            }
            cola.poll();
            contAutos--;
            System.out.println(Thread.currentThread().getName() + " salio del puente");
            if (contAutos == 0) {
                colaSur.signalAll();
                puedeNorte = false;
                puedeSur = true;

            }
            salidaNorte.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
}
