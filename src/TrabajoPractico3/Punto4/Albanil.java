package TrabajoPractico3.Punto4;

public class Albanil implements Runnable {
    private CajaHerramientas caja;
    
    public Albanil(CajaHerramientas caja){
        this.caja = caja;
    }
    @Override
    public void run() {
        try {
            caja.usarNivel();
            Thread.sleep(200);
            caja.usarCinta();
            Thread.sleep(200);
            caja.usarCuchara();

        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }
}
