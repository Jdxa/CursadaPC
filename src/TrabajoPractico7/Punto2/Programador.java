package TrabajoPractico7.Punto2;

public class Programador implements Runnable {
    Recursos recursos;

    public Programador(Recursos rec) {
        this.recursos = rec;
    }

    @Override
    public void run() {
        try {
            recursos.tomarComputadora();
            recursos.tomarLibro();
            Thread.sleep(2000);
            System.out.println("... " + Thread.currentThread().getName() + " termino el desarrollo...");
            recursos.liberarComputadora();
            recursos.liberarLibro();
        } catch (InterruptedException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
