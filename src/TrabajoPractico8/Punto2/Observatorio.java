package TrabajoPractico8.Punto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observatorio {
    private int capacidad = 50;
    private int visitantes = 0;
    private int mantenimiento = 0;
    private int investigadores = 0;

    private final Lock cerrojo = new ReentrantLock(true);
    private Condition conVis = cerrojo.newCondition();
    private Condition conMan = cerrojo.newCondition();
    private Condition conInv = cerrojo.newCondition();

    public void ingresarVistNormal() throws InterruptedException {
        cerrojo.lock();
        try {
            while (capacidad == 0 || mantenimiento > 0 || investigadores > 0) {
                conVis.await();
            }
            System.out.println(Thread.currentThread().getName() + " entro al observatorio");
            capacidad--;
            visitantes++;
        } finally {
            cerrojo.unlock();
        }
    }

    public void salirVistNormal() {
        cerrojo.lock();
        try {
            visitantes--;
            capacidad++;
            System.out.println(Thread.currentThread().getName() + " salio del observatorio");
            if (visitantes == 0) {
                conMan.signalAll();
                conInv.signalAll();
            }
            conVis.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public void ingresarVistSilla() throws InterruptedException {
        cerrojo.lock();
        try {
            while (capacidad < 20 || mantenimiento > 0 || investigadores > 0) {
                conVis.await();
            }
            System.out.println(Thread.currentThread().getName() + " entro al observatorio");
            visitantes++;
            capacidad = capacidad - 20;
        } finally {
            cerrojo.unlock();
        }
    }

    public void salirVistSilla() {
        cerrojo.lock();
        try {
            visitantes--;
            capacidad = 20 + capacidad;
            System.out.println(Thread.currentThread().getName() + " salio del observatorio");
            if (visitantes == 0) {
                conMan.signalAll();
                conInv.signalAll();
            }
            conVis.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public void ingresarMant() throws InterruptedException {
        cerrojo.lock();
        try {
            while (capacidad == 0 || visitantes > 0 || investigadores > 0) {
                conMan.await();
            }
            capacidad--;
            mantenimiento++;
            System.out.println(Thread.currentThread().getName() + " entro al observatorio");
        } finally {
            cerrojo.unlock();
        }
    }

    public void salirMant() {
        cerrojo.lock();
        try {
            mantenimiento--;
            capacidad++;
            System.out.println(Thread.currentThread().getName() + " salio del observatorio");
            if (mantenimiento == 0) {
                conInv.signalAll();
                conVis.signalAll();
            }
            conMan.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    // los investigadores estan solos, entiendo que uno por vez
    public void ingresarInvestigador() throws InterruptedException {
        cerrojo.lock();
        try {
            while (capacidad == 0 || visitantes > 0 || mantenimiento > 0) {
                conInv.await();
            }
            investigadores++;
            capacidad--;
            System.out.println(Thread.currentThread().getName() + " entro al observatorio");
        } finally {

        }
    }

    public void salirInvestigador() {

        try {
            investigadores--;
            capacidad++;
            System.out.println(Thread.currentThread().getName() + " salio del observatorio");
            if (investigadores == 0) {
                conVis.signalAll();
                conMan.signalAll();
            }
            conInv.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
}
