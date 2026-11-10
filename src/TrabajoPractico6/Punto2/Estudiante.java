package TrabajoPractico6.Punto2;

public class Estudiante implements Runnable {
    private final SalaEstudio salon;

    public Estudiante(SalaEstudio salon) {
        this.salon = salon;
    }

    @Override
    public void run() {
        try {
            salon.ocuparMesa();
            Thread.sleep((long) (Math.random() * 500));
            salon.desocuparMesa();
        } catch (InterruptedException e) {
            System.out.println("mensaje: " + e.getMessage());
        }
    }

}
