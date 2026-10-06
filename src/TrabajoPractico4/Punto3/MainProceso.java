package TrabajoPractico4.Punto3;

public class MainProceso {
    public static void main(String[] args) {
        Proceso proceso = new Proceso();

        // Hilo para P1
        Thread t1 = new Thread(() -> {
            try {
                while (true) {
                    proceso.procesarUno();
                    Thread.sleep(500); // Pequeña pausa opcional para visualizar mejor la consola
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Hilo-P1");

        // Hilo para P3
        Thread t3 = new Thread(() -> {
            try {
                while (true) {
                    proceso.procesarTres();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Hilo-P3");

        // Hilo para P2
        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    proceso.procesarDos();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Hilo-P2");

        // Iniciar los hilos
        t1.start();
        t3.start();
        t2.start();
    }

}
