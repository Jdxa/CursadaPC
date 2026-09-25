package TrabajoPractico3.Punto2;

public class MainEnergia {
    public static void main(String[] args) {
        Energia recurso = new Energia();
        Thread h1 = new Thread(new CriaturaOscura(recurso), "CriaturaOscura");
        Thread h2 = new Thread(new Sanador(recurso), "Sanador");
        // el orden no importa siempre va a partir en 10 y va a llegar a 10
        h2.start();
        h1.start();
        try {
            h2.join();
            h1.join();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Energia resultante: " + recurso.getPoder());
    }
}
