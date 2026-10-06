package TrabajoPractico3.Punto4;

public class MainConstruccion {
    public static void main(String[] args) {
        CajaHerramientas caja = new CajaHerramientas();
        
        new Thread(new Albanil(caja),"Albanil 1").start();
        new Thread(new Albanil(caja),"Albanil 2").start();
        new Thread(new Electricista(caja),"Electricista").start();
        new Thread(new Gasista(caja),"Gasista").start();


    }
}
