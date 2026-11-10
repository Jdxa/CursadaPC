package TrabajoPractico4.Punto8;
//sem binario

import java.util.concurrent.Semaphore;

public class Confiteria {
    private Semaphore semLugar = new Semaphore(1, true);
    private Semaphore semElegir = new Semaphore(0);
    private Semaphore semAtender = new Semaphore(0);
    private Semaphore semComer = new Semaphore(0);
    private Semaphore semSalir = new Semaphore(0);

    // Empleado
    public void ingresar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " intenta entrar");
        if (!semLugar.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " no pudo entrar");
            semLugar.acquire();
        }
        System.out.println(Thread.currentThread().getName() + " tomo la silla");
        semElegir.release();
    }

    public void elegir() throws InterruptedException {
        semElegir.acquire();
        System.out.println(Thread.currentThread().getName() + " elige un producto");
        semAtender.release();
    }

    public void servir() throws InterruptedException {
        semAtender.acquire();
        System.out.println(Thread.currentThread().getName() + " le sirve al empleado el producto que eligio");
        semComer.release();
    }

    public void comer() throws InterruptedException {
        semComer.acquire();
        System.out.println(Thread.currentThread().getName() + " come");
        semSalir.release();
    }

    public void salir() throws InterruptedException {
        semSalir.acquire();
        System.out.println(
                Thread.currentThread().getName() + " ya comio y sale de la confiteria");
        semLugar.release();
    }

}
