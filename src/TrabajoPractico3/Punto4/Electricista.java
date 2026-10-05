package TrabajoPractico3.Punto4;

public class Electricista implements Runnable{
    private CajaHerramientas caja;

    public Electricista(CajaHerramientas caja){
        this.caja = caja;
    }
    
    
    @Override
    public void run() {
        try {
            caja.usarCinta();
            Thread.sleep(200);
            caja.usarBuscapolo();

        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());        
        }
    }
}
