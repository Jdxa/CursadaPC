package TrabajoPractico5.Punto5;

public class ControlTren implements Runnable {
    private final Tren estacion;

    public ControlTren(Tren estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                estacion.esperarTrenLleno();

                System.out.println(">>> El tren está lleno. ¡Comienza el recorrido!");
                Thread.sleep(2000); // Simular el recorrido
                System.out.println(">>> El tren ha llegado a destino.");

                estacion.notificarFinViaje();
                estacion.esperarTodosBajaron();

                System.out.println(">>> Todos los pasajeros bajaron. Preparando próxima vuelta.\n");
                estacion.habilitarSiguienteVuelta();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
