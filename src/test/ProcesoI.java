package test;

public class ProcesoI implements Runnable {
    private Datos unDato;

    public ProcesoI(Datos unD) {
        unDato = unD;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                unDato.incrementar();
            } catch (InterruptedException e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        System.out.println("termino el hilo: " + Thread.currentThread().getName());
    }
}
