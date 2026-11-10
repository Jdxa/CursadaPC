package TrabajoPractico7.Punto2;

import java.util.concurrent.locks.*;

public class Recursos {
    private int cantComputadoras; // cantidad del recurso
    private int cantLibros;

    // defino lock
    private final Lock cerrojo = new ReentrantLock();
    private final Condition condCompu = cerrojo.newCondition();
    private final Condition condLibro = cerrojo.newCondition();

    public Recursos(int cantcompu, int cantlibros) {
        this.cantComputadoras = cantcompu;
        this.cantLibros = cantlibros;
    }

    public void tomarComputadora() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantComputadoras == 0) {
                System.out.println(Thread.currentThread().getName() + " debe esperar a una computadora");
                condCompu.await();
            }
            cantComputadoras--;
            System.out.println(Thread.currentThread().getName() + " tomo una compu. CantCompus: " + cantComputadoras);
        } finally {
            cerrojo.unlock();
        }

    }

    public void liberarComputadora() {
        cerrojo.lock();
        try {
            cantComputadoras++;
            System.out.println(
                    Thread.currentThread().getName() + " libero una computadora. CantCompus: " + cantComputadoras);
            condCompu.signal();
        } finally {
            cerrojo.unlock();
        }
    }

    public void tomarLibro() throws InterruptedException {
        cerrojo.lock();
        try {
            while (cantLibros == 0) {
                System.out.println(Thread.currentThread().getName() + " debe esperar a un libro");
                condLibro.await();
            }
            cantLibros--;
            System.out.println(Thread.currentThread().getName() + " tomo un libro. CantLibros: " + cantLibros);
        } finally {
            cerrojo.unlock();
        }
    }

    public void liberarLibro() {
        cerrojo.lock();
        try {
            cantLibros++;
            System.out.println(Thread.currentThread().getName() + " libero un libro. CantLibros: " + cantLibros);
            condLibro.signal();
        } finally {
            cerrojo.unlock();
        }
    }

}
