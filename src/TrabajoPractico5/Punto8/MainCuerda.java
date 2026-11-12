package TrabajoPractico5.Punto8;

public class MainCuerda {
    public static void main(String[] args) {
        ControlCuerda cuerda = new ControlCuerda();

        for (int i = 0; i < 3; i++) {
            new Thread(new Babuino(cuerda, "Izq"), "izq " + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Babuino(cuerda, "Der"), "Der " + i).start();
        }
    }
}
