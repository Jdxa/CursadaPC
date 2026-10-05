package TrabajoPractico3.Punto3;

public class mainTDM {
    public static void main(String[] args) {
        // Se instancian los recursos únicos compartidos en la jaula
        Recurso plato = new Recurso("Plato de comida");
        Recurso rueda = new Recurso("Rueda de ejercicio");
        Recurso hamaca = new Recurso("Hamaca de descanso");

        int cantidadHamsters = 5;

        // Se crean e inician los hilos para cada hámster
        for (int i = 1; i <= cantidadHamsters; i++) {
            Thread t = new Thread(new Hamster("Hámster " + i, plato, rueda, hamaca));
            t.start();
        }
    }
}
