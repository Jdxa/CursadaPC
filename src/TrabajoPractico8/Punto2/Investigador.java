package TrabajoPractico8.Punto2;

public class Investigador implements Runnable {
    private final Observatorio observatorio;

    public Investigador(Observatorio obs) {
        this.observatorio = obs;

    }

    @Override
    public void run() {
        try {
            observatorio.ingresarInvestigador();
            Thread.sleep(2000);
            observatorio.salirInvestigador();
        } catch (InterruptedException e) {
        }
    }
}
