package TrabajoPractico5.Punto6;

public class Avion implements Runnable {
    private TorreControl torre;
    private boolean esAterrizaje;

    public Avion(TorreControl tor, boolean es) {
        this.torre = tor;
        this.esAterrizaje = es;
    }

    @Override
    public void run() {
        try {
            if (esAterrizaje) {
                torre.aterrizar();
            } else {
                torre.despegar();
            }
        } catch (InterruptedException e) {
            System.out.println("error en avion");
        }
    }
}
