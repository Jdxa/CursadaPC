package TrabajoPractico3.Punto2;

public class Energia {
    private int poder = 10;

    public int getPoder() {
        return this.poder;
    }

    public synchronized void modificarPoder(int valor) {
        int actual = this.poder;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.poder = actual + valor;
        System.out.println(Thread.currentThread().getName() + " aplico " + valor + ". Energia actual: " + this.poder);
    }
}
