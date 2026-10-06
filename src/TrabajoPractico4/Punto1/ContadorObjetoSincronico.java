package TrabajoPractico4.Punto1;

public class ContadorObjetoSincronico {
    private int valor = 0;

    // si voy a usar bloques me conviene bloquear la seccion critica donde este
    // objeto sera usado no en el mismo, en ese caso uso metodos sincronizados
    public void incrementar() {
        // synchronized ((Integer) valor) { esto no va
        // valor++;
        // }

    }

    public synchronized void decrementar() {
        // synchronized (this) { esto no va
        // valor--;
        // }

    }

    public synchronized int getValor() {
        return valor;
    }
}
