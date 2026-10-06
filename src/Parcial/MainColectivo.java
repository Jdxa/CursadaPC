package Parcial;

public class MainColectivo {
    public static void main(String[] args) {
        int capacidadK = 3; // Capacidad máxima del colectivo[cite: 5]
        int cantR = 2; // Cantidad de recorridos[cite: 5]

        Colectivo colectivo = new Colectivo(capacidadK, cantR);

        // Hilo para el chofer / colectivo
        Thread hiloChofer = new Thread(() -> colectivo.iniciarRecorridos(), "Chofer");
        hiloChofer.start();

        // Simulamos la llegada de pasajeros por cada recorrido
        new Thread(() -> {
            try {
                int contadorPersona = 1;
                for (int r = 1; r <= cantR; r++) {
                    // Esperamos un momento entre recorrido y recorrido
                    Thread.sleep(1000);

                    // Creamos exactamente 'capacidadK' pasajeros para este recorrido
                    Thread[] personas = new Thread[capacidadK];
                    for (int i = 0; i < capacidadK; i++) {
                        boolean esUltimo = (i == capacidadK - 1);
                        personas[i] = new Thread(new Persona(colectivo, esUltimo), "Persona " + contadorPersona);
                        personas[i].start();
                        contadorPersona++;
                        Thread.sleep(200); // Desfase en la llegada a la parada
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "GeneradorPasajeros").start();
    }
}
