package TrabajoPractico7.Punto2;

public class MainDesarrollo {
    public static void main(String[] args) {
        Recursos rec = new Recursos(2, 2);
        new Thread(new Programador(rec), "programador1").start();
        new Thread(new Programador(rec), "programador2").start();
        new Thread(new Programador(rec), "programador3").start();
        new Thread(new Programador(rec), "programador4").start();
        new Thread(new Programador(rec), "programador5").start();

    }
}
