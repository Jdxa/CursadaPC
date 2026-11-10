package TrabajoPractico7.Punto1;

public class Normal implements Runnable {

    GestorSala gestor;

    public Normal(GestorSala ge) {
        this.gestor = ge;
    }

    @Override
    public void run() {
        try {
            gestor.entrarSala();
            Thread.sleep(1000);
            gestor.salirSala();
        } catch (InterruptedException e) {
        }
    }

}
