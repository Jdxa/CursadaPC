package TrabajoPractico7.Punto3;

public class CocineroCarne implements Runnable {
    // tiene la carne de antemano
    private Cocina coc;

    public CocineroCarne(Cocina coc) {
        this.coc = coc;
    }

    @Override
    public void run() {
        try {
            coc.tomarVerdura();
            coc.tomarPasta();
            Thread.sleep(2000);
            System.out.println("---- " + Thread.currentThread().getName() + " termino el plato -----");
            coc.reponerVerdura();
            coc.reponerPasta();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
