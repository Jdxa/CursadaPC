package TrabajoPractico6.Punto1;

public class CocheSur implements Runnable{
    private Puente puente1;
    public CocheSur(Puente puente1){
        this.puente1= puente1;
    }

    public void run(){
       try {
        puente1.entrarSur();
        Thread.sleep(1500);
        puente1.salirSur();
       } catch (Exception e) {
        // 
       }
    }
}
