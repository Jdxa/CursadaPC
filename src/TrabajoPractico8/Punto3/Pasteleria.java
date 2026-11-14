package TrabajoPractico8.Punto3;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pasteleria {
    private final Lock lock = new ReentrantLock();

    // Variables de condición
    private final Condition conHayCaja = lock.newCondition(); // Robots esperan caja
    private final Condition conNoCaja = lock.newCondition(); // Brazo auxiliar espera para reponer
    private final Condition condPeso = lock.newCondition();// Robots esperan espacio en la caja
    private final Condition mostradorCond = lock.newCondition(); // Sincronización del mostrador

    // Mostrador: una cola que almacena directamente el peso (double) de cada pastel
    private final Queue<Integer> mostrador;

    // Estado de la caja
    private int pesoActual = 0;
    private final int pesoMaximo;
    private boolean hayCaja = true;

    public Pasteleria(int pesoMaximo, Queue<Integer> m) {
        this.pesoMaximo = pesoMaximo;
        this.mostrador = m;

    }

    public int tomarPastel() throws InterruptedException {
        lock.lock();
        try {
            while (mostrador.isEmpty()) {
                mostradorCond.await(); // Espera a que haya pasteles en el mostrador
            }
            int pesoPastel = mostrador.poll(); // Extrae el peso de la posición más cercana

            // Notifica por si el mostrador estaba lleno y un horno esperaba
            mostradorCond.signalAll();
            return pesoPastel;
        } finally {
            lock.unlock();
        }
    }

    /**
     * d) Soltar Pastel: introduce el pastel en la caja si hay caja disponible y
     * no se sobrepasa el peso máximo permitido.
     */
    public void soltarPastel(int pesoPastel) throws InterruptedException {
        lock.lock();
        try {
            // Condición compuesta: Debe haber caja Y el peso acumulado + el nuevo pastel no
            // debe superar el máximo
            while (!hayCaja || (pesoActual + pesoPastel > pesoMaximo)) {
                if (!hayCaja) {
                    conHayCaja.await();
                } else {
                    condPeso.await(); // Espera a que se retire la caja actual para que entre una nueva vacía
                }
            }

            // Inclusión del pastel en la caja
            pesoActual += pesoPastel;
            System.out.println(Thread.currentThread().getName() + " coloco Pastel de " + pesoPastel
                    + " kg incluido. Peso actual de la caja: " + pesoActual
                    + " / " + pesoMaximo);

        } finally {
            lock.unlock();
        }
    }

    public void retirarCaja() throws InterruptedException {
        lock.lock();
        try {
            while (!hayCaja) {
                conHayCaja.await();
            }

            System.out.println(
                    Thread.currentThread().getName() + " retiro caja llena. Peso final: " + pesoActual + " kg");
            hayCaja = false;

            // Notifica al brazo de reposición que ya no hay caja
            conNoCaja.signal();
        } finally {
            lock.unlock();
        }
    }

    public void reponerCaja() throws InterruptedException {
        lock.lock();
        try {
            while (hayCaja) {
                conNoCaja.await();
            }

            System.out.println(Thread.currentThread().getName() + " colocando caja vacía...");
            hayCaja = true;
            pesoActual = 0;

            // Despierta a los robots que esperaban caja o espacio disponible
            conHayCaja.signalAll();
            condPeso.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
