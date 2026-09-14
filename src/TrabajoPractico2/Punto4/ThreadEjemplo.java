package TrabajoPractico2.Punto4;

public class ThreadEjemplo implements Runnable{
    public ThreadEjemplo(){

    }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            System.out.println(i + " "+Thread.currentThread().getName());
        }
        System.out.println("Termina thread "+ Thread.currentThread().getName());
    }
}
