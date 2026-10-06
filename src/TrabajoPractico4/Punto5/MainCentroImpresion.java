package TrabajoPractico4.Punto5;

public class MainCentroImpresion {
    public static void main(String[] args) {
        int numA = 1;
        int numB = 1;
        GestorImpresora gestor = new GestorImpresora(numA, numB);

        // Creamos un arreglo con varios clientes de distintos tipos
        Thread[] clientes = {
                new Thread(new Cliente("A", gestor), "Cliente 1"),
                new Thread(new Cliente("B", gestor), "Cliente 2"),
                new Thread(new Cliente("X", gestor), "Cliente 3"), //
                new Thread(new Cliente("A", gestor), "Cliente 4"),
                new Thread(new Cliente("X", gestor), "Cliente 5") // [cite: 4]
        };

        // Iniciamos todos los hilos de manera concurrente
        for (Thread cliente : clientes) {
            cliente.start();
            try {
                Thread.sleep(200); // Pequeño desfase en la llegada de los clientes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
