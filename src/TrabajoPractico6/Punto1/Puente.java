package TrabajoPractico6.Punto1;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore semNorte= new Semaphore(1);
    private Semaphore semSur = new Semaphore(0);

    public void entrarNorte() throws InterruptedException{
        semNorte.acquire();
        System.out.println(Thread.currentThread().getName()+" entro al norte");
        
    }

    public void salirNorte() throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" sale del norte");
        semSur.release();
    }

    public void entrarSur() throws InterruptedException{
        semSur.acquire();
        System.out.println(Thread.currentThread().getName()+" entrar del sur");

    }
    public void salirSur() throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" sale del sur");
        semNorte.release();
    }

}
