package TrabajoPractico2.Punto5;

public class HiloContador implements Runnable {
    String nombreHilo;

    public HiloContador(String nombre){
        nombreHilo = nombre;
    }

    public void run(){
        System.out.println("Comenzando "+nombreHilo);
        
     try {
        for (int contar = 0; contar < 10; contar++){
            Thread.sleep(400);
            System.out.println("En "+nombreHilo+", el recuento "+contar);
        }
     } catch (InterruptedException e) {
        System.out.println(nombreHilo+ " Interrumpido");
     }
     System.out.println("Terminado "+nombreHilo);   
    }
}
