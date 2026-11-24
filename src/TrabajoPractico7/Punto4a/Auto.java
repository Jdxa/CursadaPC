package TrabajoPractico7.Punto4a;

public class Auto implements Runnable {
    private Puente puente;
    private String lado;

    public Auto(Puente p, String l) {
        this.puente = p;
        this.lado = l;
    }

    @Override
    public void run() {
        try {
            if (lado.equals("S")) {
                puente.pasarSurNorte();
                Thread.sleep(1000);
                puente.salirSurNorte();
            } else {
                puente.pasarNorteSur();
                Thread.sleep(1000);
                puente.salirNorteSur();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
