package TrabajoPractico8.Punto2;

public class MainObs {
    public static void main(String[] args) {
        Observatorio obs = new Observatorio();
        new Thread(new Visitante(obs, true), "visSIlla").start();
        for (int i = 0; i < 50; i++) {
            new Thread(new Visitante(obs, false), "vis" + (i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Mantenimiento(obs), "mant" + (i + 1)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Investigador(obs), "inv" + (i + 1)).start();
        }

    }
}
