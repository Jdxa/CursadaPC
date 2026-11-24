package Parcial.Parcial2;

import java.util.Random;

public class MainHotel {
    public static void main(String[] args) {
        HallEntrada hall = new HallEntrada(5);
        Random r = new Random();
        CanalComunicacion canal = new CanalComunicacion();

        new Thread(new Recepcionista(canal, hall), "recepcionista").start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Albanil(canal), "albanil-" + (i + 1)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Jefe(hall, r.nextInt(1, 5), r.nextInt(1, 3)), "jefe-" + (i + 1)).start();
        }
    }
}
