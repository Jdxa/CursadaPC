package TrabajoPractico4.Punto8;

public class Empleado implements Runnable {
    Confiteria conf;

    public Empleado(Confiteria con) {
        this.conf = con;
    }

    @Override
    public void run() {
        try {
            conf.ingresar();
            Thread.sleep(500);
            conf.elegir();
            Thread.sleep(1000);
            conf.comer();
            Thread.sleep(2000);
            conf.salir();
        } catch (InterruptedException e) {
            System.out.println("hubo un error");
        }
    }
}
