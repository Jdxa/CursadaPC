package TrabajoPractico2.Punto6;

import java.util.Random;

public class Corredor implements Runnable {
    private final String nombre;
    private int distanciaRecorrida;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    private void sumarDistancia(int distancia) {
        this.distanciaRecorrida = this.distanciaRecorrida + distancia;
    }

    @Override
    public void run() {
        Random aleatorio = new Random();
        System.out.println("Comenzo: " + this.nombre);
        try {
            for (int i = 0; i < 100; i++) {
                int numero = aleatorio.nextInt(10) + 1;
                sumarDistancia(numero);
                System.out.println("Corredor: " + this.nombre + " Distancia: " + numero);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Termino el corredor: " + this.nombre + " con: " + this.distanciaRecorrida + " distancia");
    }

    public int getDistanciaRecorrida() {
        return this.distanciaRecorrida;
    }

    public String getNombre() {
        return this.nombre;
    }
}
