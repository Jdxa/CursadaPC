package TrabajoPractico2.Punto4;
public class MainThreadEJemplo4 {
    public static void main(String[] args) {
        ThreadEjemplo alfa = new ThreadEjemplo();
        ThreadEjemplo beta = new ThreadEjemplo();

        new Thread (alfa, "Maria Jose").start();
        new Thread (beta,"Jose Maria").start();

    }



}
