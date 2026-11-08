package TrabajoPractico6.Punto2;

public class SalaEstudio {
    private int mesasDisponibles; // me dice cuantas mesas hay para ocupar

    public SalaEstudio(int mesas) {
        this.mesasDisponibles = mesas;
    }

    public synchronized void ocuparMesa() throws InterruptedException {

        // si no hay mesas...
        while (mesasDisponibles == 0) {
            // se va al CE de la saladeestudio y cada vez que intenta y no puede entrar sout
            System.out.println(Thread.currentThread().getName() + " debe esperar");
            this.wait();
        }
        // si hay mesas
        // ocupa una mesa solamente si hay por eso despues del while
        mesasDisponibles--;
        System.out.println(Thread.currentThread().getName() + " entro y ocupo una mesa");

    }

    // libera una mesa
    public synchronized void desocuparMesa() {
        // libera una mesa
        mesasDisponibles++;

        this.notifyAll();

        System.out.println(Thread.currentThread().getName() + " salio y desocupo una mesa. Mesas disponibles: "
                + mesasDisponibles);

    }
}
