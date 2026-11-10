package TrabajoPractico5.Punto2;

public class Cocinero implements Runnable {
    private Confiteria confiteria;

    public Cocinero(Confiteria conf) {
        this.confiteria = conf;
    }

    @Override
    public void run() {
        try {
            while (true) {
                confiteria.prepararComida();
            }
        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }
}
