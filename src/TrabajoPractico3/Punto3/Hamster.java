package TrabajoPractico3.Punto3;
public class Hamster implements Runnable {
    private String nombre;
    private Recurso plato;
    private Recurso rueda;
    private Recurso hamaca;

    public Hamster(String nombre, Recurso plato, Recurso rueda, Recurso hamaca) {
        this.nombre = nombre;
        this.plato = plato;
        this.rueda = rueda;
        this.hamaca = hamaca;
    }

    @Override
    public void run() {
        try {
            // 1. Plato
            synchronized (plato) {
                System.out.println(nombre + " ha comenzado a usar: " + plato.getNombre());
                Thread.sleep(1500);
                System.out.println(nombre + " ha terminado de usar: " + plato.getNombre());
            }

            Thread.sleep(500);

            // 2. Rueda
            synchronized (rueda) {
                System.out.println(nombre + " ha comenzado a usar: " + rueda.getNombre());
                Thread.sleep(2000);
                System.out.println(nombre + " ha terminado de usar: " + rueda.getNombre());
            }

            Thread.sleep(500);

            // 3. Hamaca
            synchronized (hamaca) {
                System.out.println(nombre + " ha comenzado a usar: " + hamaca.getNombre());
                Thread.sleep(2500);
                System.out.println(nombre + " ha terminado de usar: " + hamaca.getNombre());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Buena práctica para restaurar la bandera de interrupción
        }
    }
}
