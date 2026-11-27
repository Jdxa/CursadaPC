package TrabajoPractico8.Punto6;

import java.util.Random;

public class GeneradorCanciones implements Runnable {
    private Mesa mesa;

    public GeneradorCanciones(Mesa m) {
        this.mesa = m;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            while (true) {
                mesa.generador(r.nextInt(4)); // entre 0 y 3
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
