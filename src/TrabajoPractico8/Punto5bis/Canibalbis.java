package TrabajoPractico8.Punto5bis;

public class Canibalbis implements Runnable {
    private Ollabis ollaComida;

    public Canibalbis(Ollabis olla) {
        this.ollaComida = olla;
    }

    @Override
    public void run() {
        try {
            ollaComida.comer();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
