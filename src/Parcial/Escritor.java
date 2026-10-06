package Parcial;

public class Escritor implements Runnable {
    private Pizarra pi;
    private String texto;

    public Escritor(String texto, Pizarra pi) {
        this.pi = pi;
        this.texto = texto;
    }

    @Override
    public void run() {
        try {
            pi.usar(texto);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("error " + e.getMessage());
        }
    }

}
