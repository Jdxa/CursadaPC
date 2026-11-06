package TrabajoPractico6.Punto1;

public class GestionPuente {
    public static void main(String[] args) {
        Puente puente1 = new Puente();
        new Thread(new CocheNorte(puente1), "Coche norte1").start();
        new Thread(new CocheSur(puente1), "Coche sur1").start();
        new Thread(new CocheSur(puente1), "Coche sur2").start();
        new Thread(new CocheSur(puente1), "Coche sur3").start();
        
        new Thread(new CocheNorte(puente1), "Coche norte2").start();
        new Thread(new CocheNorte(puente1), "Coche norte3").start();

    }
}
