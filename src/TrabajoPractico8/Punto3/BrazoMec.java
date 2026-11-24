package TrabajoPractico8.Punto3;

/**
 * Hilo que representa al brazo mecánico auxiliar.
 * Se encarga de retirar la caja cuando ya no entran más pasteles y colocar una
 * caja vacía.
 */
public class BrazoMec implements Runnable {
    private final Pasteleria estacion;

    public BrazoMec(Pasteleria estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (i < 2) {

                // a) Retira la caja actual del área de relleno
                estacion.retirarCaja();

                // b) Coloca una caja vacía en el área de relleno
                estacion.reponerCaja();
                Thread.sleep(2000);
                i++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[BrazoMec] Hilo interrumpido.");
        }
    }
}