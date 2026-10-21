package TrabajoPractico6.Punto1;

public class CocheNorte implements Runnable {
    private Puente puente1;

    public CocheNorte(Puente puente1){
        this.puente1 = puente1;
    }

    @Override
    public void run() {
        try {
            puente1.entrarNorte();
            Thread.sleep(2000);
            puente1.salirNorte();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
