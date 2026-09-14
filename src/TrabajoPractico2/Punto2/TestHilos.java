package TrabajoPractico2.Punto2;

public class TestHilos {
    public static void main(String[] args) {
        Hilo miHilo = new Hilo();
        miHilo.start();
        try{
            //obliga a miHilo a terminar para q continuee main
            //fuerzo la ejecucion para que se comporte de manera deterministica
            miHilo.join();
        }catch (InterruptedException e){
            System.out.println("Error");
        }
        System.out.println("En el main");
    }
}
