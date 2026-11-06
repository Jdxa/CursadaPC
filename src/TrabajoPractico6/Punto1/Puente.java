package TrabajoPractico6.Punto1;

public class Puente {
    private boolean puenteOcupado = false;

    // --- ENTRADA NORTE ---
    public synchronized void entrarNorte() throws InterruptedException {
        while (puenteOcupado) {
            this.wait(); // Espera si ya hay un coche en el puente
        }
        puenteOcupado = true;
        System.out.println(Thread.currentThread().getName() + " entró por el norte");
    }

    public synchronized void salirNorte() {
        puenteOcupado = false;
        System.out.println(Thread.currentThread().getName() + " salió por el norte");
        this.notifyAll(); // Despierta a los demás hilos en espera
    }

    // --- ENTRADA SUR (Hace exactamente lo mismo) ---
    public synchronized void entrarSur() throws InterruptedException {
        while (puenteOcupado) {
            this.wait();
        }
        puenteOcupado = true;
        System.out.println(Thread.currentThread().getName() + " entró por el sur");
    }

    public synchronized void salirSur() {
        puenteOcupado = false;
        System.out.println(Thread.currentThread().getName() + " salió por el sur");
        this.notifyAll();
    }
}
