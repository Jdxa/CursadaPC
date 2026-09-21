package TrabajoPractico2.Punto4;
public class MainThreadEJemplo4 {
    public static void main(String[] args) {

        new Thread (new ThreadEjemplo(), "Maria Jose").start();
        new Thread (new ThreadEjemplo(),"Jose Maria").start();

    }



}
