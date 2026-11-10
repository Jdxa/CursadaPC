package TrabajoPractico4.Punto8;

import java.nio.channels.Pipe.SourceChannel;

public class Mozo implements Runnable {
    private Confiteria confiteria;

    public Mozo(Confiteria conf) {
        this.confiteria = conf;
    }

    @Override
    public void run() {
        try {
            while (true) {
                confiteria.servir();
                System.out.println("el mozo hace su hobby");
                Thread.sleep(500);
                // se queda en espera activa xq no hay mas empleados que atender
            }
        } catch (InterruptedException e) {
            System.out.println("error de interrupcion");
        }
    }
}
