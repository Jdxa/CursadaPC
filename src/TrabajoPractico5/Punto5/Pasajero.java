package TrabajoPractico5.Punto5;

public class Pasajero implements Runnable {
    private final Tren estacion;

    public Pasajero(Tren estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {
            estacion.comprarTicket();
            int vuelta = estacion.subirTren();
            estacion.esperarFinViaje(vuelta);
            estacion.bajarTren(vuelta);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
