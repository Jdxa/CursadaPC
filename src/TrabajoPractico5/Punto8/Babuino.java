package TrabajoPractico5.Punto8;

public class Babuino implements Runnable {
    ControlCuerda cuerda;
    String lado; // izquierda o derecha

    public Babuino(ControlCuerda cue, String l) {
        this.cuerda = cue;
        this.lado = l;
    }

    @Override
    public void run() {
        try {
            cuerda.acercarceBorde(lado);
            cuerda.subir(lado);
            Thread.sleep(500);
            cuerda.bajar(lado);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
