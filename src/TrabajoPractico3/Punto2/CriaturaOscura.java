package TrabajoPractico3.Punto2;

public class CriaturaOscura implements Runnable {
    private Energia recurso;

    public CriaturaOscura(Energia recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            recurso.modificarPoder(-3);
        }
    }
}
