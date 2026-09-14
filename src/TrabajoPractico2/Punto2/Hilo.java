package TrabajoPractico2.Punto2;

public class Hilo extends Thread  {
    
    public void run(){
        ir();
    }   
    public void ir(){
        hacerMas();
    }
    public void hacerMas(){
        
        System.out.println("En la pila");
        
    }  
}
