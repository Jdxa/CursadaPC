package TrabajoPractico4.Punto8;
//sem binario

import java.util.concurrent.Semaphore;

public class Confiteria {
    private Semaphore semLugar = new Semaphore(1);

    // Empleado
    public void ingresar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " intenta entrar");
        if (!semLugar.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " no pudo entrar");
            semLugar.acquire();
        }
        System.out.println(Thread.currentThread().getName() + " pudo entrar");
    }
}
