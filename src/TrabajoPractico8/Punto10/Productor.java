package TrabajoPractico8.Punto10;

public class Productor implements Runnable {
    private Fabrica fabrica;
    private int tipo;

    public Productor(Fabrica fab, int t) {
        this.fabrica = fab;
        this.tipo = t;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            int n = cantIteraciones();
            while (i < n) {
                fabrica.producir(tipo);
                Thread.sleep(1000);
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private int cantIteraciones() {
        int n;
        if (this.tipo == 0) {
            n = 4;
        } else if (this.tipo == 1) {
            n = 2;
        } else {
            n = 1;
        }
        return n;
    }
}
