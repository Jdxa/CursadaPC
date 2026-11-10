package TrabajoPractico6.Punto4;

public class MainGestionAlmacen {
    public static void main(String[] args) {
        Almacen buffer = new Almacen(5);
        buffer.getCapacidad();
        new Thread(new Productor(buffer), "productor1").start();
        new Thread(new Productor(buffer), "productor2").start();
        new Thread(new Productor(buffer), "productor3").start();
        new Thread(new Consumidor(buffer), "consumidor1").start();
        new Thread(new Consumidor(buffer), "consumidor2").start();
        new Thread(new Consumidor(buffer), "consumidor3").start();
    }
}
