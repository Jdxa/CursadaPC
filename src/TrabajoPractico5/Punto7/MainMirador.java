package TrabajoPractico5.Punto7;

public class MainMirador {
    public static void main(String[] args) {
        Mirador mir = new Mirador(4);
        new Thread(new Encargado(mir), "encargado").start();
        new Thread(new Visitante(mir), "vis1").start();
        new Thread(new Visitante(mir), "vis2").start();
        new Thread(new Visitante(mir), "vis3").start();
        new Thread(new Visitante(mir), "vis4").start();
        new Thread(new Visitante(mir), "vis5").start();
        new Thread(new Visitante(mir), "vis6").start();

    }
}
