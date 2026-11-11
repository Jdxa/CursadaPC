package TrabajoPractico5.Punto5;

public class MainTren {
    public static void main(String[] args) {
        // n > c
        int cantidadEspacios = 2;

        Tren tren = new Tren(cantidadEspacios);

        new Thread(new ControlTren(tren), "tren").start();
        new Thread(new VendedorTickets(tren), "vendedor").start();
        new Thread(new Pasajero(tren), "pasajero1").start();
        new Thread(new Pasajero(tren), "pasajero2").start();
        new Thread(new Pasajero(tren), "pasajero3").start();
        new Thread(new Pasajero(tren), "pasajero4").start();

    }
}
