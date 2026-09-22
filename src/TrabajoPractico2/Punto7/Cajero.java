package TrabajoPractico2.Punto7;

public class Cajero {
    private String nombre;

    public Cajero(String unNombre) {
        this.nombre = unNombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public void esperarXsegundos(int producto) {
        try {
            Thread.sleep(producto * 1000);
        } catch (InterruptedException e) {
            System.out.println("La espera fue interrumpida.");
        }
    }

    public void procesarCompra(CompraCliente compraCliente, long timeStamp) {
        System.out.println(
                "El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + compraCliente.getNombre()
                        + " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + " seg");

        for (int i = 0; i < compraCliente.getProductos().length; i++) {
            this.esperarXsegundos(compraCliente.getProductos()[i]);
            System.out.println("Procesando el producto " + (i + 1) + " -> Tiempo: "
                    + (System.currentTimeMillis() - timeStamp) / 1000 + " seg");
        }
        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + compraCliente.getNombre()
                + " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + " seg");

    }

}
