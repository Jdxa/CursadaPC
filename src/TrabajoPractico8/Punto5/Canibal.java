package TrabajoPractico8.Punto5;

public class Canibal implements Runnable {
    private Olla olla;

    public Canibal(Olla o) {
        this.olla = o;
    }

    @Override
    public void run() {
        try {
            olla.comer();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
