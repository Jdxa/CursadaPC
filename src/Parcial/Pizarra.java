package Parcial;

public class Pizarra {
    private String texto;
    private boolean señal;

    public Pizarra(boolean señal) {
        this.señal = señal;
    }

    public synchronized void usar(String text) {
        this.señal = true;
        try {
            if (this.señal) {
                System.out.println("Ocupada por " + Thread.currentThread().getName());
                this.texto = text;
                System.out.println(Thread.currentThread().getName() + " escribe el texto: " + this.texto);
                Thread.sleep(1000);
                this.señal = false;
                System.out.println("Se libero la pizarra");
            }
        } catch (InterruptedException e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}
