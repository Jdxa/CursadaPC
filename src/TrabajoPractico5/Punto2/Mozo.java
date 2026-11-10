package TrabajoPractico5.Punto2;

public class Mozo implements Runnable {
    private Confiteria confiteria;

    public Mozo(Confiteria conf) {
        this.confiteria = conf;
    }

    @Override
    public void run() {
        try {
            while (true) {
                confiteria.servirBebida();
            }

        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }
}
