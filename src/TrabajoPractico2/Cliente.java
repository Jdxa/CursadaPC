package TrabajoPractico2;
public class Cliente extends Thread {
    private Recurso miRecurso;

    public Cliente(Recurso miRecurso){
        this.miRecurso = miRecurso;
    }

    public void run(){
        System.out.println("soy: "+Thread.currentThread().getName());
        this.miRecurso.uso();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println("Error");
        }
    }

}
