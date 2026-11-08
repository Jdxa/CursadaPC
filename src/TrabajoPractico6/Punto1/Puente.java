package TrabajoPractico6.Punto1;

public class Puente {

    private int cantCoches = 0;
    private boolean puedeSur = false;
    private boolean puedeNorte = true;

    private int esperandoNorte = 0;
    private int esperandoSur = 0;

    // ENTRADA NORTE
    public synchronized void entrarNorte() throws InterruptedException {
        esperandoNorte++;
        // lo hace esperar y comprueba que pueda pasar o no
        try {
            while (cantCoches >= 10 || !puedeNorte) {
                this.wait(); // espera si pasaron 10 o si vienen del sentido contrario
            }
        } finally {
            // si no cumplio la condicion del while continua para entrar
            esperandoNorte--;
        }
        cantCoches++; // aumento la cantidad q estan pasando
        System.out.println(Thread.currentThread().getName() + " entró por el norte");
    }

    public synchronized void salirNorte() {
        cantCoches--; // reduzco la cantidad de autos en el puente
        System.out.println(Thread.currentThread().getName() + " salió por el norte");
        // si salio el ultimo despierto a los demas y cambio el sentido solo si hay
        // alguno esperando del otro lado
        if (cantCoches == 0) {
            System.out.println("salio el ultimo del norte");
            if (esperandoSur > 0) {
                // solo cambia de direccion si hay del otro lado esperando
                puedeNorte = false;
                puedeSur = true;
            }
            this.notifyAll();
        }
    }

    // ENTRADA SUR
    public synchronized void entrarSur() throws InterruptedException {
        esperandoSur++;
        try {
            while (cantCoches >= 10 || !puedeSur) {
                this.wait(); // espera si pasaron 10 o si vienen del sentido contrario
            }
        } finally {
            esperandoSur--;
        }

        cantCoches++;
        System.out.println(Thread.currentThread().getName() + " entró por el sur");
    }

    public synchronized void salirSur() {
        cantCoches--;
        System.out.println(Thread.currentThread().getName() + " salió por el sur");
        if (cantCoches == 0) {
            System.out.println("salio el ultimo del sur");
            if (esperandoNorte > 0) {
                // solo cambia de direccion si hay del otro lado esperando
                puedeSur = false;
                puedeNorte = true;
            }
            this.notifyAll();
        }

    }
}
