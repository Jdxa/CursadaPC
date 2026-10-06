package TrabajoPractico4.Punto4;

public class Cliente implements Runnable {
    GestorImpresora gestor;

    public Cliente(GestorImpresora gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try {
            gestor.usar();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

}
