package TrabajoPractico7.Punto4a;

public class MainPuente {
    public static void main(String[] args) {
        Puente p = new Puente();

        for (int i = 0; i < 3; i++) {
            new Thread(new Auto(p, "S"), "sur" + (i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Auto(p, "N"), "norte" + (i + 1)).start();
        }
    }
}
