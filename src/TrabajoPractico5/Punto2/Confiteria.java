package TrabajoPractico5.Punto2;

import java.util.concurrent.Semaphore;

public class Confiteria {
    private Semaphore semLugar = new Semaphore(2, true);

    private Semaphore semPedirBebida = new Semaphore(0);
    private Semaphore semBebidaLista = new Semaphore(0);

    private Semaphore semPedirComida = new Semaphore(0);
    private Semaphore semComidaLista = new Semaphore(0);

    private boolean mozoHobby = false;
    private boolean cocineroHobby = false;

    public void ingresar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " intenta entrar...");
        semLugar.acquire();
        System.out.println(Thread.currentThread().getName() + " entro al lugar");

    }

    // empleado:opciones
    public void pedirBebida() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " pide bebida al mozo");
        semPedirBebida.release();
        semBebidaLista.acquire();
        System.out.println(Thread.currentThread().getName() + " recibe la bebida");
    }

    public void pedirComida() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " pide comida al cocinero");
        semPedirComida.release();
        semComidaLista.acquire();
        System.out.println(Thread.currentThread().getName() + " recibe la comida");

    }

    public void comerySalir() {
        System.out.println(Thread.currentThread().getName() + " se pone a comer");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("error de interrupcion");
        }
        System.out.println(Thread.currentThread().getName() + " comio y sale");
        semLugar.release();
    }

    // mozo

    public void servirBebida() throws InterruptedException {
        // espera pedidos
        semPedirBebida.acquire();
        mozoHobby = false; // adquirio un pedido, no hace su hobby
        System.out.println("Mozo sirve la bebida...");
        Thread.sleep(500);
        semBebidaLista.release(); // entrega bebida al empleado
        // si no hay pedidos, puedo ir a hacer mi hobby
        if (semPedirBebida.availablePermits() == 0) {
            if (!mozoHobby) {
                System.out.println("Mozo al hobby (no hay más pedidos pendientes)");
                mozoHobby = true;
            }
            Thread.sleep(1000); // Tiempo haciendo su hobby
        }
    }

    // cocinero
    public void prepararComida() throws InterruptedException {
        // espera pedidos
        semPedirComida.acquire();
        cocineroHobby = false; // tiene un pedido, no hace su hobby
        System.out.println("Cocinero preparando comida...");
        Thread.sleep(800);
        semComidaLista.release(); // entrega la comida al empleado que espero

        // si no hay pedido, hace su hobby
        if (semPedirComida.availablePermits() == 0) {
            if (!cocineroHobby) {
                System.out.println("Cocinero al hobby (no hay más pedidos pendientes)");
                cocineroHobby = true;
            }
            Thread.sleep(1000); // Tiempo haciendo su hobby
        }
    }
}
