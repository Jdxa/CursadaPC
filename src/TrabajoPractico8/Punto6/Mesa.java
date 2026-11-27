package TrabajoPractico8.Punto6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Mesa {
    private String genero = "";
    private int duracionR;
    private int duracionF;
    private int duracionC;
    private int duracionB;

    private boolean[] producir;
    private Lock cerrojo = new ReentrantLock();
    private Condition conGen = cerrojo.newCondition();
    private Condition[] conProd;

    public Mesa(int r, int f, int c, int b, int tipos) {
        this.duracionR = r;
        this.duracionF = f;
        this.duracionC = c;
        this.duracionB = b;
        this.conProd = new Condition[tipos];
        for (int i = 0; i < tipos; i++) {
            this.conProd[i] = cerrojo.newCondition();
        }
        this.producir = new boolean[tipos];
        for (int i = 0; i < tipos; i++) {
            this.producir[i] = false;
        }
    }

    // generador
    public void generador(int i) throws InterruptedException {
        cerrojo.lock();
        try {
            switch (i) {
                case 0:
                    System.out.println(Thread.currentThread().getName() + " genero rock");
                    this.genero = "rock";
                    producir[i] = true;
                    conProd[i].signalAll();
                    break;
                case 1:
                    // folk
                    System.out.println(Thread.currentThread().getName() + " genero folk");
                    this.genero = "folk";
                    producir[i] = true;
                    conProd[i].signalAll();
                    break;
                case 2:
                    // country
                    System.out.println(Thread.currentThread().getName() + " genero country");
                    this.genero = "country";
                    producir[i] = true;
                    conProd[i].signalAll();
                    break;

                case 3:
                    System.out.println(Thread.currentThread().getName() + " genero blues");
                    this.genero = "blues";
                    producir[i] = true;
                    conProd[i].signalAll();
                    break;
                default:
                    break;
            }
        } finally {
            cerrojo.unlock();
        }
    }

    //
    public int producir(int tipo) throws InterruptedException {
        cerrojo.lock();
        int dur = 0;
        try {
            while (!producir[tipo]) {
                conProd[tipo].await();
            }
            switch (tipo) {
                case 0:
                    System.out.println(Thread.currentThread().getName() + " produjo rock");
                    dur = duracionR;
                    break;
                case 1:
                    // folk
                    System.out.println(Thread.currentThread().getName() + " produjo folk");
                    dur = duracionF;
                    break;
                case 2:
                    // country
                    System.out.println(Thread.currentThread().getName() + " produjo country");
                    dur = duracionC;
                    break;
                case 3:
                    System.out.println(Thread.currentThread().getName() + " produjo blues");
                    dur = duracionB;
                    break;
                default:
                    System.out.println(Thread.currentThread().getName() + " no existe este tipo de productor");
                    break;
            }
            producir[tipo] = false;
            return dur;
        } finally {
            cerrojo.unlock();
        }

    }

}
