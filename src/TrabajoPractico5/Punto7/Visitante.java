package TrabajoPractico5.Punto7;

public class Visitante implements Runnable {
    private Mirador mir;

    public Visitante(Mirador mir) {
        this.mir = mir;
    }

    @Override
    public void run() {
        try {
            mir.subirEscalera();
            Thread.sleep(500);
            mir.bajarTobogan();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
