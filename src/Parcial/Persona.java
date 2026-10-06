package Parcial;

public class Persona implements Runnable {

    private final Colectivo colectivo;
    private final boolean esElUltimo; // Bandera para saber si es el último en bajar

    public Persona(Colectivo colectivo, boolean esElUltimo) {

        this.colectivo = colectivo;
        this.esElUltimo = esElUltimo;
    }

    @Override
    public void run() {
        try {
            // 1. El pasajero llega a la parada e intenta subir
            colectivo.subir();

            // 2. Durante el viaje el pasajero permanece a bordo

            // 3. Al llegar a destino, desciende utilizando la cadena
            colectivo.bajar(esElUltimo);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
    }

}
