package TrabajoPractico2.Punto5;

public class UsoHilos {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando. ");
        // Construyo un objeto unHilo
        HiloContador hc = new HiloContador("#1");
        // construyo un hilo con ese objeto
        Thread nuevoHilo = new Thread(hc);
        // inicio el hilo
        nuevoHilo.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(" .");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido ");
        }

        System.out.println("Hilo principal finalizado ");

    }
    // Si quitamos TODOS los sleep() del programa: Los bucles se ejecutarán a la
    // máxima velocidad que la CPU permita. El bucle de 50 puntos del main y las 10
    // iteraciones de HiloContador se intercalarán de manera extremadamente rápida y
    // caótica, dependiendo exclusivamente del planificador del sistema operativo.

    // Si quitamos solo el de main: El hilo principal imprimirá los 50 puntos
    // instantáneamente, hará una pausa de 100ms y llegará a su fin rápidamente.
    // Mientras tanto, HiloContador seguirá imprimiendo su conteo cada 400ms (por lo
    // que verás el mensaje de fin del main antes de que termine el hilo contador).

    // Si quitamos solo el de HiloContador: El hilo secundario imprimirá los 10
    // números de golpe de forma casi instantánea y sin pausas, mientras el main
    // realiza sus puntos y su propia pausa.
}
