package TrabajoPractico5.Punto3;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore semPlatos;
    private Semaphore semGatos = new Semaphore(1); // primero entrar los gatos
    private Semaphore semPerros = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private int contAnimales = 0;
    private int cantMax;

    public Comedor(int cant) {
        this.semPlatos = new Semaphore(cant);
        this.cantMax = cant;
    }

    public void ingresarGatos() throws InterruptedException {
        semGatos.acquire(); // si esta habilitado puede entrar
        semPlatos.acquire(); // si hay lugar entra

        mutex.acquire(); // zona critica solo un hilo puede acceder
        contAnimales++; // cuento los que entraron
        if (contAnimales < cantMax) {
            semGatos.release(); // solo si no es el ultimo devuelvo el permiso para otro gato
        }
        mutex.release(); // libero zona critica

        System.out.println(Thread.currentThread().getName() + " ingreso a comer (gato)");
    }

    public void ingresarPerros() throws InterruptedException {
        semPerros.acquire(); // entra si le toca
        semPlatos.acquire(); // entra si hay lugar

        mutex.acquire(); // zona critica
        contAnimales++;
        if (contAnimales < cantMax) {
            semPerros.release(); // mientras no sea el ultimo permito que entren mas
        }
        mutex.release(); // fin zona critica

        System.out.println(Thread.currentThread().getName() + " ingreso a comer (perro)");
    }

    public void salirGatos() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " salio del comedor (gato)");
        semPlatos.release(); // plato liberado

        mutex.acquire(); // zona critica
        contAnimales--; // descuento los animales dentro del comedor
        // si era el ultimo gato en salir, cambiamos las tornas
        if (contAnimales == 0) {
            semPerros.release(); // ahora entran los perros
        }
        mutex.release(); // fin zona critica
    }

    public void salirPerros() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " salio del comedor (perro)");
        semPlatos.release(); // libero un plato

        mutex.acquire(); // zona critica
        contAnimales--; // reduzco los animales dentro del comedor
        // si era el ultimo animal cambio las tornas
        if (contAnimales == 0) {
            semGatos.release(); // ahora entran los gatos
        }
        mutex.release(); // fin zona critica
    }

}
