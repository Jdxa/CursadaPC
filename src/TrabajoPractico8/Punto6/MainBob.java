package TrabajoPractico8.Punto6;

public class MainBob {
    public static void main(String[] args) {
        Mesa mesa = new Mesa(5, 6, 7, 8, 4);
        Album album = new Album(15);

        new Thread(new GeneradorCanciones(mesa), "bob").start();
        new Thread(new Manager(album), "manager").start();

        for (int i = 0; i < 7; i++) {
            new Thread(new Productor(mesa, album), "prod-" + (i + 1)).start();
        }
    }
}
