package TrabajoPractico4.Punto1;

public class ContadorSincronico {
    private int valor = 0;

    public synchronized void incrementar() {
        valor++;
    }

    public synchronized void decrementar() { // agregue synchronized
        valor--;
    }

    public synchronized int getValor() {
        return valor;
    }
}
