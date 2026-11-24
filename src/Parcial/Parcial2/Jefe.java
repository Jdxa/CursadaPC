package Parcial.Parcial2;

public class Jefe implements Runnable {
    private HallEntrada hall;
    private int vacantes;
    private int paciencia;

    public Jefe(HallEntrada hall, int vacantes, int paciencia) {
        this.hall = hall;
        this.vacantes = vacantes;
        this.paciencia = paciencia;
    }

    @Override
    public void run() {
        try {
            hall.consultar(vacantes, paciencia);
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
