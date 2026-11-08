package TrabajoPractico6.Punto1;

import java.util.LinkedList;
import java.util.Queue;

public class Puente {

    private int cantCoches = 0;
    private boolean puedeSur = false;
    private boolean puedeNorte = true;

    private int esperandoNorte = 0;
    private int esperandoSur = 0;

    private int norteCruzadosEnTanda = 0;
    private int surCruzadosEnTanda = 0;

    private final Queue<String> colaSalidaNorte = new LinkedList<>();
    private final Queue<String> colaSalidaSur = new LinkedList<>();

    // ENTRADA NORTE
    public synchronized void entrarNorte() throws InterruptedException {
        String miNombre = Thread.currentThread().getName();
        esperandoNorte++;
        // lo hace esperar y comprueba que pueda pasar o no
        try {
            while (cantCoches >= 10 || !puedeNorte || norteCruzadosEnTanda >= 10) {
                this.wait(); // espera si pasaron 10 o si vienen del sentido contrario
            }
        } finally {
            // si no cumplio la condicion del while continua para entrar
            esperandoNorte--;
        }
        cantCoches++; // aumento la cantidad q estan pasando
        norteCruzadosEnTanda++;
        colaSalidaNorte.add(miNombre);
        System.out.println(miNombre + " entró por el norte");
    }

    public synchronized void salirNorte() throws InterruptedException {
        String miNombre = Thread.currentThread().getName();

        while (!colaSalidaNorte.peek().equals(miNombre)) {
            this.wait();
        }

        colaSalidaNorte.poll();
        cantCoches--; // reduzco la cantidad de autos en el puente

        // si salio el ultimo despierto a los demas y cambio el sentido solo si hay
        // alguno
        System.out.println(
                miNombre + " salió por el norte. cantCoches=" + cantCoches + ", norteCruzados=" + norteCruzadosEnTanda);
        if ((norteCruzadosEnTanda >= 10 || esperandoNorte == 0) && cantCoches == 0) {
            System.out.println("salio el ultimo del norte");
            if (esperandoSur > 0) {
                // solo cambia de direccion si hay del otro lado esperando
                System.out.println("cambio el sentido a sur");
                puedeNorte = false;
                puedeSur = true;
                norteCruzadosEnTanda = 0;
            }

        }
        this.notifyAll();
    }

    // ENTRADA SUR
    public synchronized void entrarSur() throws InterruptedException {
        String miNombre = Thread.currentThread().getName();
        esperandoSur++;
        try {
            while (cantCoches >= 10 || !puedeSur || surCruzadosEnTanda >= 10) {
                this.wait(); // espera si pasaron 10 o si vienen del sentido contrario
            }
        } finally {
            esperandoSur--;
        }

        cantCoches++;
        surCruzadosEnTanda++;
        colaSalidaSur.add(miNombre);
        System.out.println(miNombre + " entró por el sur");
    }

    public synchronized void salirSur() throws InterruptedException {
        String miNombre = Thread.currentThread().getName();

        while (!colaSalidaSur.peek().equals(miNombre)) {
            this.wait();
        }

        colaSalidaSur.poll();
        cantCoches--;
        System.out.println(
                miNombre + " salió por el sur. cantCoches=" + cantCoches + ", surCruzados=" + surCruzadosEnTanda);
        if ((surCruzadosEnTanda >= 10 || esperandoSur == 0) && cantCoches == 0) {
            System.out.println("salio el ultimo del sur");
            if (esperandoNorte > 0) {
                // solo cambia de direccion si hay del otro lado esperando
                System.out.println("cambio el sentido a norte");
                puedeSur = false;
                puedeNorte = true;
                surCruzadosEnTanda = 0;
            }

        }
        this.notifyAll();

    }
}
