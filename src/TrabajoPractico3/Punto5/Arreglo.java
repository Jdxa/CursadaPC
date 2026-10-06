package TrabajoPractico3.Punto5;

public class Arreglo {
    private int sumatotal= 0;

    public synchronized void acumular(int parcial){
        sumatotal += parcial;
        
    }

    public synchronized int getSumaTotal(){
        return this.sumatotal;
    }
}
