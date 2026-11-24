package TrabajoPractico8.Punto3;

public class Reponedor implements Runnable {
    private final Pasteleria estacion;

    public Reponedor(Pasteleria estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {

            // c) Toma el pastel más cercano del mostrador
            int pesoPastel = estacion.tomarPastel();
            // d) Intenta depositar el pastel en la caja del área de relleno
            estacion.soltarPastel(pesoPastel);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[Reponedor] Hilo interrumpido.");
        }
    }
}
