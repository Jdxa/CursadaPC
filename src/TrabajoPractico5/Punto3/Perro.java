package TrabajoPractico5.Punto3;

public class Perro implements Runnable {
    private Comedor comedor;

    public Perro(Comedor com) {
        this.comedor = com;
    }

    @Override
    public void run() {
        try {
            comedor.ingresarPerros();
            Thread.sleep(2000);
            comedor.salirPerros();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
