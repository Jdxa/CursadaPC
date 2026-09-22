package TrabajoPractico2.Punto7;

public class CompraCliente {
    private String nombre;
    private int[] productos;

    public CompraCliente(String n, int[] prod) {
        this.nombre = n;
        this.productos = prod;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public int[] getProductos() {
        return this.productos;
    }

    public void setProductos(int[] prod) {
        this.productos = prod;
    }

}
