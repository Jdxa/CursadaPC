package TrabajoPractico6.Punto1;

public class CocheNorte implements Runnable {
    private final Puente puente1;

    public CocheNorte(Puente puente1) {
        this.puente1 = puente1;
    }

    @Override
    public void run() {
        try {
            puente1.entrarNorte();
            Thread.sleep(1500);
            puente1.salirNorte();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
