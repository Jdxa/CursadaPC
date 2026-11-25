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
            boolean exito = false;
            int p = paciencia;
            int i = 0;
            while (!exito) {
                if (!hall.comprobarHab(vacantes)) {
                    p--;
                    if (p == 0) {
                        i++;
                    }
                    hall.esperar(i);
                } else {
                    System.out.println("encontro lugar");
                    exito = true;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
