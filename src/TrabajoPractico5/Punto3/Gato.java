package TrabajoPractico5.Punto3;

public class Gato implements Runnable {
    private Comedor comedor;

    public Gato(Comedor com) {
        this.comedor = com;
    }

    @Override
    public void run() {
        try {
            comedor.ingresarGatos();
            Thread.sleep(2000);
            comedor.salirGatos();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
