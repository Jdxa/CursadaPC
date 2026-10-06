package TrabajoPractico4.Punto4;

import java.util.concurrent.Semaphore;

public class GestorImpresora {
    private final Semaphore impresorasDisponibles;
    private final int totalImpresoras;

    public GestorImpresora(int cantImpresoras) {
        this.totalImpresoras = cantImpresoras;
        this.impresorasDisponibles = new Semaphore(cantImpresoras, true);
    }

    public void usar() throws InterruptedException {
        // solicita el permiso y hay sino se bloquea
        System.out.println(Thread.currentThread().getName() + " llego y solicito una impresora");
        impresorasDisponibles.acquire();
        try {
            System.out.println("--> " + Thread.currentThread().getName() + " comenzó a imprimir.");

            // Simula el tiempo que lleva realizar la impresión
            Thread.sleep(1000);

            System.out
                    .println("<-- " + Thread.currentThread().getName() + " terminó de imprimir y libera la impresora.");
        } finally {

        }
        impresorasDisponibles.release();
    }
}
