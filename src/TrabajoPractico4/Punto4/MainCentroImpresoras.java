package TrabajoPractico4.Punto4;

public class MainCentroImpresoras {
    public static void main(String[] args) {
        int cantidadImpresoras = 2;
        GestorImpresora gestor = new GestorImpresora(cantidadImpresoras);

        // Simulamos la llegada de 5 clientes distintos
        int totalClientes = 5;
        Thread[] clientes = new Thread[totalClientes];

        for (int i = 1; i <= totalClientes; i++) {
            clientes[i - 1] = new Thread(new Cliente(gestor), "Cliente " + i);
            clientes[i - 1].start();

            // Pequeña pausa escalonada en la llegada de los clientes
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
