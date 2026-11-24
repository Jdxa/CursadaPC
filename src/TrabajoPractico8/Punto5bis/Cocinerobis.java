package TrabajoPractico8.Punto5bis;

public class Cocinerobis implements Runnable {
    private Ollabis ollaComida;

    public Cocinerobis(Ollabis olla) {
        this.ollaComida = olla;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ollaComida.cocinar();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
