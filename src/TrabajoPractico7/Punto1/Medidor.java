package TrabajoPractico7.Punto1;

public class Medidor implements Runnable {
    GestorSala gestor;
    int temp;

    public Medidor(GestorSala ge, int temp) {
        this.gestor = ge;
        this.temp = temp;
    }

    @Override
    public void run() {
        try {
            gestor.notificarTemperatura(temp);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
