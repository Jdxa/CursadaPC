package TrabajoPractico8.Punto5;

public class Cocinero implements Runnable {
    private Olla olla;

    public Cocinero(Olla o) {
        this.olla = o;
    }

    @Override
    public void run() {
        try {
            olla.cocinar();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

}
