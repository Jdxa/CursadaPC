package TrabajoPractico3.Punto4;

import java.util.concurrent.Semaphore;

public class CajaHerramientas {
    Semaphore nivel = new Semaphore(1);
    Semaphore cinta = new Semaphore(1);
    Semaphore buscapolos = new Semaphore(1);
    Semaphore cuchara = new Semaphore(1);
    Semaphore llave = new Semaphore(1);

    public void usarNivel() throws InterruptedException{
        nivel.acquire();
        try{
            System.out.println(Thread.currentThread().getName()+" agarro el nivel");
            Thread.sleep(500);
        }finally{
            nivel.release();
            System.out.println(Thread.currentThread().getName()+" solto el nivel");
        }
    }

    
    public void usarCuchara() throws InterruptedException{
        cuchara.acquire();
        try{
            System.out.println(Thread.currentThread().getName()+" agarro la cuchara");
            Thread.sleep(500);
        }finally{
            cuchara.release();
            System.out.println(Thread.currentThread().getName()+" solto la cuchara");
        }
    }

    public void usarBuscapolo() throws InterruptedException{
        buscapolos.acquire();
        try{
            System.out.println(Thread.currentThread().getName()+" agarro el buscapolos");
            Thread.sleep(500);
        }finally{
            buscapolos.release();
            System.out.println(Thread.currentThread().getName()+" solto el buscapolos");
        }
    }

    public void usarCinta() throws InterruptedException{
        cinta.acquire();
        try{
            System.out.println(Thread.currentThread().getName()+" agarro la cinta");
            Thread.sleep(500);
        }finally{
            cinta.release();
            System.out.println(Thread.currentThread().getName()+" solto la cinta");
        }
    }

    public void usarLlave() throws InterruptedException{
        llave.acquire();
        try{
            System.out.println(Thread.currentThread().getName()+" agarro la llave");
            Thread.sleep(500);
        }finally{
            llave.release();
            System.out.println(Thread.currentThread().getName()+" solto la llave");
        }
    }

}
