package TrabajoPractico5.Punto5;

import java.util.concurrent.Semaphore;

public class Tren {
    /*
     * comprar pasaje (pasajero)
     * entrar tren (pasajero)
     * verificarticket (control)
     * hay n hilos pasajeros donde n > c y c es la cantidad de asientos
     * el tren siempre se llena
     */

    private final int C;
    private int cantAbordo = 0;
    private int cantBajaron = 0;
    private int nroVueltaSubida = 0;
    private int nroVueltaBajada = 0;

    // Semáforos para la compra de tickets
    private final Semaphore mutexComprar = new Semaphore(1);
    private final Semaphore clienteEsperando = new Semaphore(0);
    private final Semaphore ticketListo = new Semaphore(0);

    // Semáforos para el control del tren y embarque
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore puedeSubir;
    private final Semaphore trenLleno = new Semaphore(0);
    private final Semaphore viajeTerminado = new Semaphore(0);
    private final Semaphore todosBajaron = new Semaphore(0);

    public Tren(int capacidadTren) {
        this.C = capacidadTren;
        this.puedeSubir = new Semaphore(capacidadTren, true);
    }

    public void comprarTicket() throws InterruptedException {
        mutexComprar.acquire();
        clienteEsperando.release();
        ticketListo.acquire();
        mutexComprar.release();
        System.out.println(Thread.currentThread().getName() + " compro un ticket");
    }

    public void atenderCliente() throws InterruptedException {
        clienteEsperando.acquire();
        Thread.sleep(100);
        ticketListo.release();
        System.out.println("vendedor atiende un cliente");
    }

    public int subirTren() throws InterruptedException {
        puedeSubir.acquire();

        mutex.acquire();
        cantAbordo++;
        int vueltaAsignada = nroVueltaSubida;
        if (cantAbordo == C) {
            cantAbordo = 0;
            nroVueltaSubida++; // Preparamos la siguiente tanda para cuando el tren se llene
            trenLleno.release();
        }
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " subio al tren");
        return vueltaAsignada;
    }

    public void esperarFinViaje(int vueltaPasajero) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " espera que termina el viaje..");
        viajeTerminado.acquire();
    }

    public void bajarTren(int vueltaPasajero) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " bajó del tren.");
        mutex.acquire();
        cantBajaron++;
        if (cantBajaron == C) {
            cantBajaron = 0;
            nroVueltaBajada++; // Avanzamos la vuelta de bajada de forma atómica
            todosBajaron.release();
        }
        mutex.release();
    }

    public void esperarTrenLleno() throws InterruptedException {
        trenLleno.acquire();
    }

    public void notificarFinViaje() {
        System.out.println(">>> El tren está lleno. ¡Comienza el recorrido!");
        for (int i = 0; i < C; i++) {
            viajeTerminado.release();
        }
    }

    public void esperarTodosBajaron() throws InterruptedException {
        System.out.println("tren espera a que bajen los demas");
        todosBajaron.acquire();
        System.out.println(Thread.currentThread().getName() + " bajaron todos");
    }

    public void habilitarSiguienteVuelta() {
        System.out.println(">>> Todos los pasajeros bajaron. Preparando próxima vuelta.\n");
        for (int i = 0; i < C; i++) {
            puedeSubir.release();
        }
    }
}

// Hilo Vendedor de Tickets
