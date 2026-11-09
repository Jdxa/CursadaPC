package TrabajoPractico7.Punto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorSala {
    private int tempActual = 20;
    private final Lock cerrojo = new ReentrantLock();
    private final Condition condPersona = cerrojo.newCondition();
    private final Condition condJubilado = cerrojo.newCondition();

    private int cantPersonas = 0;
    private int cantJubiladosEsperando = 0;

    private static final int MAX_NORMAL = 5;
    private static final int MAX_CALOR = 3;
    private static final int UMBRAL_TEMP = 30;

    public void entrarSala() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantPersonas >= obtenerLimiteActual() || cantJubiladosEsperando > 0) {
                System.out.println(Thread.currentThread().getName() + " espera en la cola");
                condPersona.await();
            }
            cantPersonas++;
            System.out.println(Thread.currentThread().getName() + " entro a la sala. CantidadActual: " + cantPersonas);

        } finally {
            cerrojo.unlock();
        }
    }

    public void entrarSalaJubilado() throws InterruptedException {
        cerrojo.lock();
        try {
            cantJubiladosEsperando++;
            try {
                while (cantPersonas >= obtenerLimiteActual()) {
                    // no hay capacidad
                    System.out.println(Thread.currentThread().getName() + " espera en la cola, no hay capacidad");
                    condJubilado.await();
                }
            } finally {
                cantJubiladosEsperando--;
            }
            System.out.println(Thread.currentThread().getName() + " entro por prioridad");
        } finally {
            cerrojo.unlock();
        }
    }

    public void salirSala() throws InterruptedException {
        cerrojo.lock();
        try {
            cantPersonas--;
            if (cantJubiladosEsperando > 0) {
                condJubilado.signal();
            } else {
                condPersona.signal();
            }

            System.out.println(Thread.currentThread().getName() + " salio de la sala");
        } finally {
            cerrojo.unlock();
        }

    }

    public void notificarTemperatura(int temperatura) {
        cerrojo.lock();
        try {
            int tempAnterior = tempActual;
            tempActual = temperatura;
            if (tempActual <= UMBRAL_TEMP && tempAnterior > UMBRAL_TEMP) {
                condJubilado.signalAll();
                condPersona.signalAll();
            }
            System.out.println("temperatura actual: " + tempActual);
        } finally {
            cerrojo.unlock();
        }
    }

    private int obtenerLimiteActual() {
        int cap;
        if (tempActual > UMBRAL_TEMP) {
            cap = MAX_CALOR; // 35
        } else {
            cap = MAX_NORMAL; // 50
        }
        return cap;
    }
}
