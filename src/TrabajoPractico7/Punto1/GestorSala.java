package TrabajoPractico7.Punto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorSala {
    private int tempActual;
    private final Lock cerrojo = new ReentrantLock();
    private final Condition condPersona = cerrojo.newCondition();
    private final Condition condJubilado = cerrojo.newCondition();

    private int cantPersonas = 0;
    private int cantJubiladosEsperando = 0;

    private int MAX_NORMAL;
    private int MAX_CALOR;
    private int UMBRAL_TEMP;

    public GestorSala(int tUmbral, int capNormal, int capCalor, int tempActual){
        this.UMBRAL_TEMP = tUmbral;
        this.MAX_NORMAL = capNormal;
        this.MAX_CALOR = capCalor;
        this.tempActual = tempActual;
    }
    public void entrarSala() throws InterruptedException {
        cerrojo.lock(); //cierro el lock
        try {
            //si esta lleno debe esperar, si hay un jubilado esperando debe esperar
            while (cantPersonas >= obtenerLimiteActual() || cantJubiladosEsperando > 0) {
                System.out.println(Thread.currentThread().getName() + " espera en la cola");
                condPersona.await(); //va al CE de "persona"
            }
            cantPersonas++;
            System.out.println(Thread.currentThread().getName() + " entro a la sala. CantidadActual: " + cantPersonas);

        } finally {
            cerrojo.unlock(); //libero el lock
        }
    }

    public void entrarSalaJubilado() throws InterruptedException {
        cerrojo.lock();
        try {
            cantJubiladosEsperando++;
            try {
                while (cantPersonas >= obtenerLimiteActual()) {
                    // no hay capacidad
                    System.out.println(Thread.currentThread().getName() + " espera en la cola, no hay capacidad");
                    condJubilado.await(); //se va al CE del jubilado
                }
                cantPersonas++;
            } finally {
                //llega aqui tras el while
                cantJubiladosEsperando--;
            }
            System.out.println(Thread.currentThread().getName() + " entro por prioridad. CantidadActual: "+cantPersonas);
        } finally {
            cerrojo.unlock();
        }
    }

    public void salirSala() throws InterruptedException {
        cerrojo.lock();
        try {
            cantPersonas--; //decresco la cantidad de personas en la sala
            //libero antes a los jubilados esperando si es que hay
            if (cantJubiladosEsperando > 0) {
                condJubilado.signal(); //le avisa a uno del CE de jubilado
            } else {
                condPersona.signal(); //le avisa a uno del CE de persona
            }

            System.out.println(Thread.currentThread().getName() + " salio de la sala");
        } finally {
            cerrojo.unlock();
        }

    }
    //metodo para hilo medidor
    public void notificarTemperatura(int temperatura) {
        cerrojo.lock();
        try {
            int tempAnterior = tempActual;
            tempActual = temperatura;
            //si bajo la temperatura aviso porque habra mas lugar
            if (tempActual <= UMBRAL_TEMP && tempAnterior > UMBRAL_TEMP) {
                condJubilado.signalAll();
                condPersona.signalAll();
            }
            System.out.println("temperatura actual: " + tempActual);
        } finally {
            cerrojo.unlock();
        }
    }

    private int obtenerLimiteActual() {
        int cap;
        if (tempActual > UMBRAL_TEMP) {
            cap = MAX_CALOR; // 35
        } else {
            cap = MAX_NORMAL; // 50
        }
        return cap;
    }
}
