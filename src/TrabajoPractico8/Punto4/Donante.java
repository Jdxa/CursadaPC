package TrabajoPractico8.Punto4;

public class Donante implements Runnable {
    private SalaEspera sala;

    public Donante(SalaEspera s) {
        this.sala = s;
    }

    @Override
    public void run() {
        try {
            sala.entrar();
            Thread.sleep(2000);
            sala.salir();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
