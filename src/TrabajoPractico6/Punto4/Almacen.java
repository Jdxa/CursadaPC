package TrabajoPractico6.Punto4;

public class Almacen {
    private final int capacidad;
    private int stockActual = 0;

    public Almacen(int buffer) {
        this.capacidad = buffer; // can
    }

    public synchronized void producir() throws InterruptedException {

        while (stockActual == capacidad) {
            System.out.println(Thread.currentThread().getName()
                    + " en espera para producir, capacidad alcanzada. Capacidad: " + (capacidad - stockActual));
            this.wait();
        }
        stockActual++;
        System.out
                .println(
                        Thread.currentThread().getName() + " produjo y lo agrego al buffer. Capacidad: " +
                                (capacidad - stockActual));
        this.notifyAll();
    }

    public synchronized void consumir() throws InterruptedException {

        while (stockActual == 0) {
            System.out.println(
                    Thread.currentThread().getName() + " nada que consumir, espera. Capacidad: "
                            + (capacidad - stockActual));
            this.wait();
        }
        stockActual--;
        System.out.println(Thread.currentThread().getName() + " consumio. Capacidad: " + (capacidad - stockActual));
        this.notifyAll();

    }

    public void getCapacidad() {
        System.out.println("Capacidad de: " + this.capacidad);
    }
}
