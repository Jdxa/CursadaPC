package TrabajoPractico5.Punto7;

public class Encargado implements Runnable {
    private Mirador mir;

    public Encargado(Mirador mir) {
        this.mir = mir;
    }

    @Override
    public void run() {
        try {
            while (true) {
                mir.habilitarBajada();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
