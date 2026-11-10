package TrabajoPractico7.Punto3;

public class CocineroPasta implements Runnable {
    // ya tiene la pasta hecha, debe hacer carne y verduras
    private Cocina coc;

    public CocineroPasta(Cocina coc) {
        this.coc = coc;
    }

    @Override
    public void run() {
        try {
            coc.tomarVerdura();
            coc.tomarCarne();
            Thread.sleep(2000);
            System.out.println("---- " + Thread.currentThread().getName() + " termino el plato -----");
            coc.reponerVerdura();
            coc.reponerCarne();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
