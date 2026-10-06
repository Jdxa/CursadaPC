package TrabajoPractico4.Punto5;

public class Cliente implements Runnable {
    private final String tipo;
    private final GestorImpresora gestor;

    public Cliente(String tipo, GestorImpresora gestor) {
        this.tipo = tipo;
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try {
            switch (tipo) {
                case "A":
                    gestor.usartTipoA();
                    break;
                case "B":
                    gestor.usartTipoB();
                    break;
                case "X":
                    gestor.usarImpresoraX();
                    break;
                default:
                    System.out.println(Thread.currentThread().getName() + " no especifico");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
    }

}
