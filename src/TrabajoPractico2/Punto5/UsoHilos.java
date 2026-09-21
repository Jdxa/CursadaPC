package TrabajoPractico2.Punto5;

public class UsoHilos {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando. ");
        //Construyo un objeto unHilo
        HiloContador hc = new HiloContador("#1");
        //construyo un hilo con ese objeto
        Thread nuevoHilo = new Thread(hc);
        //inicio el hilo
        nuevoHilo.start();
 
        for(int i = 0; i < 50; i++){
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
