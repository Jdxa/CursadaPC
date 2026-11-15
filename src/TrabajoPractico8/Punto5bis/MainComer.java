package TrabajoPractico8.Punto5bis;

public class MainComer {
    public static void main(String[] args) {
        Ollabis olla = new Ollabis(3);

        new Thread(new Cocinerobis(olla), "cocinero").start();

        for (int i = 0; i < 5; i++) {
            new Thread(new Canibalbis(olla), "canibal_" + (i + 1)).start();
        }

    }
}
