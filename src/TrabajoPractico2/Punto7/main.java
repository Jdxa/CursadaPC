package TrabajoPractico2.Punto7;

public class main {
    public static void main(String[] args) {
        CompraCliente compra1 = new CompraCliente("CompraCliente1", new int[] { 2, 2, 1, 5, 2, 3 });
        CompraCliente compra2 = new CompraCliente("CompraCliente2", new int[] { 1, 3, 5, 1, 1 });
        long initialTime = System.currentTimeMillis();
        // CajeroThread cajero1 = new CajeroThread("Cajero 1", compra1, initialTime);
        // CajeroThread cajero2 = new CajeroThread("Cajero 2", compra2, initialTime);
        // cajero1.start();
        // cajero2.start();

        CajeroRunnable cajeroA = new CajeroRunnable("Cajero1 ", compra1, initialTime);
        CajeroRunnable cajeroB = new CajeroRunnable("Cajero2", compra2, initialTime);
        Thread h1 = new Thread(cajeroA);
        Thread h2 = new Thread(cajeroB);
        h1.start();
        h2.start();

    }
}
