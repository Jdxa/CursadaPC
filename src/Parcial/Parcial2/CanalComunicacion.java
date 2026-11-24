package Parcial.Parcial2;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CanalComunicacion {
    private Lock cerrojo = new ReentrantLock();
    private Condition condRecep = cerrojo.newCondition(); // duerme al recepcionista
    private Condition condAlba = cerrojo.newCondition(); // duerme a los albaniles
    private int[] hab = new int[10]; // buffer de habitaciones
    private boolean lleno = false;// indica si esta lleno el buffer para devolverlo
    // albañiles construyen sobre el arreglo
    private Random r = new Random();

    public void construir() throws InterruptedException {
        cerrojo.lock();
        try {
            while (lleno) {
                condAlba.await();
            }
            // busco la posicion
            int i = 0;
            while (i < hab.length && hab[i] != 0) {
                i++;
            }
            if (i < hab.length) {
                hab[i] = r.nextInt(5) + 1;

                // Si acabamos de ocupar la última celda (índice 9), el buffer quedó lleno
                if (i == hab.length - 1) {
                    lleno = true;
                    condRecep.signal();
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public int[] tomar() throws InterruptedException {
        cerrojo.lock();
        int[] buff;
        try {
            // espera a que se llene el buffer para retirarlo
            while (!lleno) {
                condRecep.await();
            }
            // copia el buffer
            buff = hab;
            // cambia el buffer actual por uno vacio
            hab = new int[10];
            // despierto a los albaniles para que lo llenen
            lleno = false;
            condAlba.signalAll();
            // devuelvo el buffer para usarlo en el hall de entrada
            return buff;
        } finally {
            cerrojo.unlock();
        }

    }
}
