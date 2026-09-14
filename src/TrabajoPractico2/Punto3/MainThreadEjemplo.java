package TrabajoPractico2.Punto3;

public class MainThreadEjemplo {
    public static void main(String[] args) {
        new ThreadEjemplo("Maria Jose").start();
        new ThreadEjemplo("Jose Maria").start();

        System.out.println("Termina thread main");

        //en varias ejecuciones se ve que nunca es deterministico siempre cambia como intercala cada hilo el uso de la cpu
    }
}
