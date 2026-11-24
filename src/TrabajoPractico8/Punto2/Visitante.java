package TrabajoPractico8.Punto2;

public class Visitante implements Runnable {
    private Observatorio observatorio;
    private boolean silla;

    public Visitante(Observatorio obs, boolean s) {
        this.observatorio = obs;
        this.silla = s;
    }

    @Override
    public void run() {
        try {
            if (silla) {
                observatorio.ingresarVistSilla();
                Thread.sleep(1000);
                observatorio.salirVistSilla();
            } else {
                observatorio.ingresarVistNormal();
                Thread.sleep(1000);
                observatorio.salirVistNormal();
            }
        } catch (InterruptedException e) {
        }
    }

}
