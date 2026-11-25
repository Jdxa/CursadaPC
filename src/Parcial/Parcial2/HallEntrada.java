package Parcial.Parcial2;

import java.util.concurrent.locks.*;

public class HallEntrada {

    private Lock cerrojo = new ReentrantLock();
    private int[] esperando;
    private Condition[] fila;
    private int prio;
    private int[] hab = new int[10];

    public HallEntrada(int n) {
        this.prio = n;
        this.esperando = new int[n];
        this.fila = new Condition[n];
        for (int i = 0; i < n; i++) {
            this.fila[i] = cerrojo.newCondition();
        }
    }

    public void avisar() throws InterruptedException {
        cerrojo.lock();
        try {

            int i = this.prio - 1;// n-1 es la ultima pos
            // busco la posicion con mayor prioridad
            while (i > 0 && esperando[i] == 0) {
                i--;
            }
            // si no hay nadie, solo despierto si hay alguien
            if (esperando[i] > 0) {
                System.out.println(Thread.currentThread().getName() + " despierto la fila " + i);
                fila[i].signalAll();
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void poner(int[] buf) throws InterruptedException {
        cerrojo.lock();
        try {
            this.hab = buf;

            System.out.println(Thread.currentThread().getName() + " cambio el buffer");
        } finally {
            cerrojo.unlock();
        }
    }

    // el jefe comprueba la hab
    public boolean comprobarHab(int cant) {
        cerrojo.lock();
        try {
            boolean exito = false;
            int i = 0;
            // compruebo todas las habitaciones
            while (!exito && i < hab.length) {
                // si encontre lugar salgo y limpio esa habitacion
                if (hab[i] >= cant) {
                    System.out.println(Thread.currentThread().getName() + " encontro lugar");
                    exito = true;
                    hab[i] = 0;
                } else {
                    // sino sigo buscando
                    i++;
                }
            }
            return exito;
        } finally {
            cerrojo.unlock();
        }
    }

    // hago esperar al jefe
    public void esperar(int i) throws InterruptedException {
        cerrojo.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " espera en fila " + i);
            esperando[i]++;
            fila[i].await();
            esperando[i]--;
        } finally {
            cerrojo.unlock();
        }
    }

    // esta al pedo
    // public void consultar(int cant, int paciencia) throws InterruptedException {
    // cerrojo.lock();
    // int p = paciencia;
    // int i = 0;
    // boolean encontrado = false;
    // try {
    // // repite hasta que encuentre lugar
    // System.out.println(Thread.currentThread().getName() + " tiene una paciencia
    // inicial de " + paciencia
    // + " y busca: " + cant + " lugares");
    // while (!encontrado) {
    // // si encontro lugar sale
    // if (comprobarHab(cant)) {
    // encontrado = true;
    // } else {
    // // le toca esperar
    // esperando[i]++;
    // System.out.println(
    // Thread.currentThread().getName() + " no encontro lugar. Espera en la fila " +
    // i);
    // fila[i].await();
    // // lo despertaron
    // esperando[i]--;
    // // reduce su paciencia
    // p--;
    // System.out.println(Thread.currentThread().getName() + " se baja su paciencia
    // a " + p);
    // if (p <= 0) {
    // // aumento su prioridad mientras que este dentro del rango
    // if (i < this.prio - 1) {
    // i++;
    // System.out.println(Thread.currentThread().getName() + " aumenta su prioridad
    // a " + i);
    // }
    // // reseteo la paciencia
    // p = paciencia;
    // }
    // }
    // }
    // System.out.println(Thread.currentThread().getName() + " encontro lugar");
    // } finally {
    // cerrojo.unlock();
    // }
    // }
}
