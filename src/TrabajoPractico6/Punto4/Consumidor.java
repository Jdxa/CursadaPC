package TrabajoPractico6.Punto4;

public class Consumidor implements Runnable {
    private final Almacen buffer;

    public Consumidor(Almacen buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.consumir();
                Thread.sleep((long) (Math.random() * 500));
            }

        } catch (InterruptedException e) {
            System.out.println("mensaje: " + e.getMessage());
        }
    }
}
