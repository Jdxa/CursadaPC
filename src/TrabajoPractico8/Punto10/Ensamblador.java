package TrabajoPractico8.Punto10;

public class Ensamblador implements Runnable {
    private Fabrica fabrica;

    public Ensamblador(Fabrica fab) {
        this.fabrica = fab;

    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 5) {
                fabrica.ensamblar();
                Thread.sleep(1000);
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
