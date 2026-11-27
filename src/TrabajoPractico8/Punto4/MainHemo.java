package TrabajoPractico8.Punto4;

public class MainHemo {
    public static void main(String[] args) {
        SalaEspera sala = new SalaEspera();

        for (int i = 0; i < 15; i++) {
            new Thread(new Donante(sala), "donante-" + (i + 1)).start();
        }

    }
}
