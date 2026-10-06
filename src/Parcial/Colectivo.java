package Parcial;

import java.util.concurrent.Semaphore;

public class Colectivo {
    private final int capacidadK;
    private final int cantR;

    // Semáforos para la subida
    private final Semaphore puertaSubida = new Semaphore(0);
    private final Semaphore subirPasajero = new Semaphore(0);
    private final Semaphore pasajeroSubio = new Semaphore(0);

    // Semáforos para la bajada en cadena
    private final Semaphore puedeBajar = new Semaphore(0);
    private final Semaphore todosBajaron = new Semaphore(0);

    private int pasajerosActuales = 0;

    public Colectivo(int capacidadK, int cantR) {
        this.capacidadK = capacidadK;
        this.cantR = cantR;
    }

    public void iniciarRecorridos() {
        try {
            int recorridoActual = 1;
            while (recorridoActual <= cantR) {
                System.out.println(
                        "\n--- COLECTIVO: Iniciando recorrido " + recorridoActual + " en parada de salida. ---");

                pasajerosActuales = 0;
                // El chofer espera exactamente a que el colectivo se llene (llegue a la
                // capacidad K)
                while (pasajerosActuales < capacidadK) {
                    puertaSubida.release(); // Habilita la puerta delantera
                    subirPasajero.acquire(); // Espera a que suba un pasajero

                    pasajerosActuales++;
                    System.out.println(
                            "Chofer: Pasajero subió. Pasajeros a bordo: " + pasajerosActuales + "/" + capacidadK);

                    pasajeroSubio.release(); // Le avisa al pasajero que ya se le cobró/verificó
                }

                System.out.println("--> Colectivo lleno. Iniciando viaje hacia destino...");
                Thread.sleep(2000); // Simula el recorrido del colectivo
                System.out.println("<-- Colectivo llegó a destino. Habilitando puerta trasera.");

                // Habilita la bajada del primer pasajero para iniciar la cadena
                puedeBajar.release();

                // El chofer espera a que el último pasajero le avise que bajaron todos
                todosBajaron.acquire();

                System.out.println("Colectivo vacío. Regresando a la parada de salida...\n");
                Thread.sleep(1500); // Simula el regreso
                recorridoActual++;
            }
            System.out.println("=== Fin de todos los recorridos (" + cantR + "). ===");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void subir() throws InterruptedException {
        puertaSubida.acquire();
        subirPasajero.release();
        pasajeroSubio.acquire();
        System.out.println(Thread.currentThread().getName() + " subió exitosamente al colectivo.");
    }

    public void bajar(boolean esElUltimo) throws InterruptedException {
        puedeBajar.acquire(); // Espera su turno para bajar
        System.out.println(Thread.currentThread().getName() + " bajó del colectivo.");

        if (!esElUltimo) {
            puedeBajar.release(); // Pasa el turno al siguiente pasajero en la fila de bajada
        } else {
            todosBajaron.release(); // Si es el último, le avisa al chofer que ya no queda nadie
        }
    }
}
