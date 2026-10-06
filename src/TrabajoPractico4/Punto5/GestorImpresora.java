package TrabajoPractico4.Punto5;

import java.util.concurrent.Semaphore;

public class GestorImpresora {
    private final Semaphore impresorasDisponiblesTipoA;
    private final Semaphore impresorasDisponiblesTipoB;

    public GestorImpresora(int tipoA, int tipoB) {
        impresorasDisponiblesTipoA = new Semaphore(tipoA);
        impresorasDisponiblesTipoB = new Semaphore(tipoB);
    }

    public void usartTipoA() throws InterruptedException {
        // solicita el permiso y hay sino se bloquea
        System.out.println(Thread.currentThread().getName() + " llego y solicito una impresora tipo A");
        impresorasDisponiblesTipoA.acquire();
        try {
            System.out.println("--> " + Thread.currentThread().getName() + " comenzó a imprimir.");

            // Simula el tiempo que lleva realizar la impresión
            Thread.sleep(1000);

        } finally {
            System.out
                    .println("<-- " + Thread.currentThread().getName()
                            + " terminó de imprimir y libera la impresora tipo A.");
            impresorasDisponiblesTipoA.release();
        }

    }

    public void usartTipoB() throws InterruptedException {
        // solicita el permiso y hay sino se bloquea
        System.out.println(Thread.currentThread().getName() + " llego y solicito una impresora tipo B");
        impresorasDisponiblesTipoB.acquire();
        try {
            System.out.println("--> " + Thread.currentThread().getName() + " comenzó a imprimir.");

            // Simula el tiempo que lleva realizar la impresión
            Thread.sleep(1000);

        } finally {
            System.out
                    .println("<-- " + Thread.currentThread().getName()
                            + " terminó de imprimir y libera la impresora tipo B.");
            impresorasDisponiblesTipoB.release();
        }

    }

    public void usarImpresoraX() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " solicita cualquier impresora (Tipo X).");

        // Intenta primero conseguir tipo A, si no hay disponible de inmediato, intenta
        // con B
        boolean adquirio = false;

        while (!adquirio) {
            if (impresorasDisponiblesTipoA.tryAcquire()) {
                try {
                    System.out.println(
                            "--> " + Thread.currentThread().getName() + " (Tipo X) consiguió impresora tipo A.");
                    Thread.sleep(1500);
                    adquirio = true;
                } finally {
                    impresorasDisponiblesTipoA.release();
                }
            } else if (impresorasDisponiblesTipoB.tryAcquire()) {
                try {
                    System.out.println(
                            "--> " + Thread.currentThread().getName() + " (Tipo X) consiguió impresora tipo B.");
                    Thread.sleep(1500);
                    adquirio = true;
                } finally {
                    impresorasDisponiblesTipoB.release();
                }
            } else {
                // Si ninguna está libre, esperamos un breve momento antes de volver a intentar
                // para evitar consumo excesivo de CPU (o se puede estructurar con colas de
                // condición)
                Thread.sleep(200);
            }
        }
        System.out.println("<-- " + Thread.currentThread().getName() + " (Tipo X) terminó de imprimir.");
    }
}
