package TrabajoPractico3.Punto4;

public class Gasista implements Runnable {
    private CajaHerramientas caja;
    
    public Gasista(CajaHerramientas caja){
        this.caja = caja;
    }
    public void run(){
        try {
            caja.usarLlave();
            Thread.sleep(200);
            caja.usarNivel();

        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }
}
