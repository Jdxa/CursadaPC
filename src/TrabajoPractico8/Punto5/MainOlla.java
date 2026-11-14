package TrabajoPractico8.Punto5;

public class MainOlla {
    public static void main(String[] args) {
        Olla olla = new Olla(3);

        new Thread(new Cocinero(olla), "cocinero1").start();
        for (int i = 0; i < 8; i++) {
            new Thread(new Canibal(olla), "canibal" + (i + 1)).start();
        }
    }
}
