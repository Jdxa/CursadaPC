package TrabajoPractico7.Punto3;

public class CocineroEnsalada implements Runnable {
    // ya tiene la ensalada, necesita carne y pasta
    private Cocina coc;

    public CocineroEnsalada(Cocina coc) {
        this.coc = coc;
    }

    @Override
    public void run() {
        try {
            coc.tomarPasta();
            coc.tomarCarne();
            Thread.sleep(2000);
            System.out.println("---- " + Thread.currentThread().getName() + " termino el plato -----");
            coc.reponerPasta();
            coc.reponerCarne();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
