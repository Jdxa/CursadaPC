package TrabajoPractico6.Punto4;

public class Productor implements Runnable {
    private final Almacen buffer;

    public Productor(Almacen buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.producir();
                Thread.sleep((long) (Math.random() * 500));
            }

        } catch (InterruptedException e) {
            System.out.println("mensaje: " + e.getMessage());
        }
    }

}
