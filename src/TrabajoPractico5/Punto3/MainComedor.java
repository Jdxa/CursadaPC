package TrabajoPractico5.Punto3;

public class MainComedor {
    public static void main(String[] args) {
        Comedor comedor = new Comedor(4);
        // siempre entran primero los gatos (condicion propia)
        new Thread(new Perro(comedor), "perro1").start();
        new Thread(new Perro(comedor), "perro2").start();
        new Thread(new Gato(comedor), "gato1").start();
        new Thread(new Gato(comedor), "gato2").start();

    }
}
