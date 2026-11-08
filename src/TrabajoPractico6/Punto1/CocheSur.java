package TrabajoPractico6.Punto1;

public class CocheSur implements Runnable {
    private final Puente puente1;

    public CocheSur(Puente puente1) {
        this.puente1 = puente1;
    }

    @Override
    public void run() {
        try {
            puente1.entrarSur();
            Thread.sleep(1500);
            puente1.salirSur();
        } catch (InterruptedException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
