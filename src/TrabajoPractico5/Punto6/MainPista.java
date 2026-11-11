package TrabajoPractico5.Punto6;

public class MainPista {
    public static void main(String[] args) {
        TorreControl torre = new TorreControl();
        new Thread(new Avion(torre, true), "av1").start();
        new Thread(new Avion(torre, true), "av2").start();
        new Thread(new Avion(torre, true), "av3").start();
        new Thread(new Avion(torre, true), "av4").start();
        new Thread(new Avion(torre, true), "av5").start();
        new Thread(new Avion(torre, true), "av6").start();
        new Thread(new Avion(torre, true), "av7").start();
        new Thread(new Avion(torre, true), "av8").start();
        new Thread(new Avion(torre, true), "av9").start();
        new Thread(new Avion(torre, true), "av10").start();
        new Thread(new Avion(torre, false), "av11").start();
    }
}
