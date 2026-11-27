package TrabajoPractico8.Punto10;

public class MainAuto {
    public static void main(String[] args) {
        Fabrica fab = new Fabrica(20, 10, 5, 3);

        new Thread(new Ensamblador(fab), "ensamblador").start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Productor(fab, 0), "prod-ruedas-" + (i + 1)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Productor(fab, 1), "prod-puertas-" + (i + 1)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Productor(fab, 2), "prod-carro-" + (i + 1)).start();
        }
    }
}
