package TrabajoPractico7.Punto1;

public class Medidor implements Runnable {
    GestorSala gestor = new GestorSala();

    public Medidor(GestorSala ge) {
        this.gestor = ge;
    }

    @Override
    public void run() {
        try {
            gestor.notificarTemperatura(35);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
