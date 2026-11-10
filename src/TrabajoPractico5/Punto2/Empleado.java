package TrabajoPractico5.Punto2;

public class Empleado implements Runnable {
    private boolean quiereComida;
    private boolean quiereBebida;

    private Confiteria confiteria;

    public Empleado(Confiteria conf, boolean beb, boolean com) {
        this.confiteria = conf;
        this.quiereBebida = beb;
        this.quiereComida = com;
    }

    @Override
    public void run() {
        try {
            confiteria.ingresar();
            if (quiereBebida) {
                confiteria.pedirBebida();
            }
            if (quiereComida) {
                confiteria.pedirComida();
            }
            confiteria.comerySalir();
        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }

}
