package TrabajoPractico5.Punto1;

public class Persona implements Runnable {
    GestionPiscina piscina;

    public Persona(GestionPiscina pis) {
        this.piscina = pis;
    }

    @Override
    public void run() {
        try {
            piscina.ingresarPiscina();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " disfruto de la piscina.");
            piscina.salirPiscina();
        } catch (InterruptedException e) {
            System.out.println("mensaje: " + e.getMessage());
        }
    }
}
