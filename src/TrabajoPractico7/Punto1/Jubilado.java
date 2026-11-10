package TrabajoPractico7.Punto1;

public class Jubilado implements Runnable {
    GestorSala gestor;

    public Jubilado(GestorSala ge) {
        this.gestor = ge;
    }

    @Override
    public void run() {
        try {
            gestor.entrarSalaJubilado();
            Thread.sleep(1000);
            gestor.salirSala();
        } catch (InterruptedException e) {
        }
    }

}
