package TrabajoPractico2.Punto7;

public class CajeroRunnable implements Runnable {
    private String nombre;
    private CompraCliente compraCliente;
    private long initialTime;

    public CajeroRunnable(String nombre, CompraCliente unacompraCliente, long inicial) {
        this.nombre = nombre;
        this.compraCliente = unacompraCliente;
        this.initialTime = inicial;
    }

    @Override
    public void run() {
        System.out.println(
                "El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                        + this.compraCliente.getNombre()
                        + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");

        for (int i = 0; i < compraCliente.getProductos().length; i++) {
            this.esperarXsegundos(compraCliente.getProductos()[i]);
            System.out.println("Procesando el producto " + (i + 1) + " -> Tiempo: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");
        }
        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + this.compraCliente.getNombre()
                + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");
    }

    public void esperarXsegundos(int producto) {
        try {
            Thread.sleep(producto * 1000);
        } catch (InterruptedException e) {
            System.out.println("La espera fue interrumpida.");
        }
    }
}
