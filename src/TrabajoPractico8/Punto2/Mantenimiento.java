package TrabajoPractico8.Punto2;

public class Mantenimiento implements Runnable {
    private final Observatorio observatorio;

    public Mantenimiento(Observatorio obs) {
        this.observatorio = obs;

    }

    @Override
    public void run() {
        try {
            observatorio.ingresarMant();
            Thread.sleep(2000);
            observatorio.salirMant();
        } catch (InterruptedException e) {
        }
    }

}
