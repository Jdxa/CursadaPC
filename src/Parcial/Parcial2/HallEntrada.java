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

    public void avisar() {
        cerrojo.lock();
        try {
            int i = this.prio - 1;// n-1 es la ultima pos
            // busco la posicion con mayor prioridad
            while (i > 0 && esperando[i] == 0) {
                i--;
            }
            // si no hay nadie, solo despierto si hay alguien
            if (esperando[i] > 0) {
                fila[i].signalAll();
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void poner(int[] buf) {
        cerrojo.lock();
        try {
            this.hab = buf;
        } finally {
            cerrojo.unlock();
        }
    }

    private boolean comprobarHab(int cant) {
        boolean exito = false;
        int i = 0;
        // compruebo todas las habitaciones
        while (!exito && i < hab.length) {
            // si encontre lugar salgo y limpio esa habitacion
            if (hab[i] >= cant) {
                exito = true;
                hab[i] = 0;
            } else {
                // sino sigo buscando
                i++;
            }
        }
        return exito;
    }

    public boolean consultar(int cant, int paciencia) throws InterruptedException {
        cerrojo.lock();
        int p = paciencia;
        int i = 0;
        boolean encontrado = false;
        try {
            // repite hasta que encuentre lugar
            while (!encontrado) {
                // si encontro lugar sale
                if (comprobarHab(cant)) {
                    encontrado = true;
                } else {
                    // le toca esperar
                    esperando[i]++;
                    try {
                        fila[i].await();
                    } finally {
                        // lo despertaron
                        esperando[i]--;
                    }
                    // reduce su paciencia
                    p--;
                    if (p <= 0) {
                        // aumento su prioridad mientras que este dentro del rango
                        if (i < this.prio - 1) {
                            i++;
                        }
                        // reseteo la paciencia
                        p = paciencia;
                    }
                }
            }
            return encontrado;
        } finally {
            cerrojo.unlock();
        }
    }
}
