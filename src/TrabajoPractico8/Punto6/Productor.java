package TrabajoPractico8.Punto6;

import java.util.Random;

public class Productor implements Runnable {
    private Mesa mesa;
    private Album album;

    public Productor(Mesa m, Album a) {
        this.mesa = m;
        this.album = a;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            int i = 0;
            while (i < 2) {
                System.out.println(Thread.currentThread().getName() + " produccion: " + (i + 1));
                int dur;
                dur = mesa.producir(r.nextInt(4));
                Thread.sleep(1000);
                album.agregar(dur);
                i++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
