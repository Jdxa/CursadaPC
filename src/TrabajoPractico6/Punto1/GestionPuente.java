package TrabajoPractico6.Punto1;

public class GestionPuente {
    public static void main(String[] args) {
        Puente puente1 = new Puente();
        new Thread(new CocheNorte(puente1), "Coche norte1").start();
        new Thread(new CocheNorte(puente1), "Coche norte2").start();
        new Thread(new CocheNorte(puente1), "Coche norte3").start();
        new Thread(new CocheNorte(puente1), "Coche norte4").start();
        new Thread(new CocheNorte(puente1), "Coche norte5").start();
        new Thread(new CocheNorte(puente1), "Coche norte6").start();
        new Thread(new CocheNorte(puente1), "Coche norte7").start();
        new Thread(new CocheNorte(puente1), "Coche norte8").start();
        new Thread(new CocheNorte(puente1), "Coche norte9").start();
        new Thread(new CocheNorte(puente1), "Coche norte10").start();
        new Thread(new CocheNorte(puente1), "Coche norte11").start();

        new Thread(new CocheSur(puente1), "Coche sur1").start();
        new Thread(new CocheSur(puente1), "Coche sur2").start();
        new Thread(new CocheSur(puente1), "Coche sur3").start();
        new Thread(new CocheSur(puente1), "Coche sur4").start();
        new Thread(new CocheSur(puente1), "Coche sur5").start();
        new Thread(new CocheSur(puente1), "Coche sur6").start();
        new Thread(new CocheSur(puente1), "Coche sur7").start();
        new Thread(new CocheSur(puente1), "Coche sur8").start();
        new Thread(new CocheSur(puente1), "Coche sur9").start();
        new Thread(new CocheSur(puente1), "Coche sur10").start();
        new Thread(new CocheSur(puente1), "Coche sur11").start();

    }
}
