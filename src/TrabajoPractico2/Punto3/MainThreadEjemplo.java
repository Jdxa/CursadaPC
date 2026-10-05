package TrabajoPractico2.Punto3;

public class MainThreadEjemplo {
    public static void main(String[] args) {
        Thread h1 = new ThreadEjemplo("Maria Jose");
        Thread h2 = new ThreadEjemplo("Jose Maria");
        h1.start();
        h2.start();

        System.out.println("Termina thread main");

        // en varias ejecuciones se ve que nunca es deterministico siempre cambia como
        // intercala cada hilo el uso de la cpu
    }
}
