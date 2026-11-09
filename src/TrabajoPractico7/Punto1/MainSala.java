package TrabajoPractico7.Punto1;

public class MainSala {
    public static void main(String[] args) {
        GestorSala ge = new GestorSala();

        new Thread(new Normal(ge), "normal1").start();
        new Thread(new Normal(ge), "normal2").start();
        new Thread(new Jubilado(ge), "jubilado1").start();

        new Thread(new Medidor(ge), "medidor1").start();

        new Thread(new Jubilado(ge), "jubilado2").start();
        new Thread(new Normal(ge), "normal3").start();
        new Thread(new Jubilado(ge), "jubilado3").start();

    }
}
