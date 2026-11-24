package TrabajoPractico5.Punto5;

public class VendedorTickets implements Runnable {
    private final Tren estacion;

    public VendedorTickets(Tren estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                estacion.atenderCliente();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
