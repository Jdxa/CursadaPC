package TrabajoPractico2.Punto5;

public class UsoHilosv2 {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando. ");
        HiloContadorv2 hc1 = new HiloContadorv2("#1");
        HiloContadorv2 hc2 = new HiloContadorv2("#2");
        HiloContadorv2 hc3 = new HiloContadorv2("#3");
        hc1.start();
        hc2.start();
        hc3.start();

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
}
