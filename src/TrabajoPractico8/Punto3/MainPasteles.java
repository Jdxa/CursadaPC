package TrabajoPractico8.Punto3;

import java.util.LinkedList;
import java.util.Queue;

public class MainPasteles {

    public static void main(String[] args) {
        Queue<Integer> lista = new LinkedList<>();

        lista.add(3);
        lista.add(2);
        lista.add(3);
        lista.add(2);
        lista.add(10);

        Pasteleria estacion = new Pasteleria(10, lista); // Peso máximo 10.0kg, mostrador
                                                         // tamaño 5

        // Crear hilos de robots empaquetadores (por ejemplo, 3 robots)
        for (int i = 0; i < 5; i++) {
            new Thread(new Reponedor(estacion), "reponerdor_" + (i + 1)).start();
        }

        // Crear el hilo del brazo mecánico
        new Thread(new BrazoMec(estacion), "brazo").start();

        // (Los hornos se encargarían de llamar a estacion.producirPastel(peso))
    }

}
