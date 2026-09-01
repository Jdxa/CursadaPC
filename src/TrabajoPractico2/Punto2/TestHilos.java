package TrabajoPractico2.Punto2;

public class TestHilos {
    public static void main(String[] args) {
        Hilo miHilo = new Hilo();
        miHilo.start();
        
        System.out.println("En el main");
    }
}
