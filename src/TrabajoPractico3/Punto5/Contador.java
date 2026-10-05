package TrabajoPractico3.Punto5;

public class Contador implements Runnable{
    private int[] arr;
    private int ini;
    private int fin;
    private Arreglo recurso;
    
    public Contador(int[] arr, int ini, int fin, Arreglo recurso){
        this.arr = arr;
        this.ini = ini;
        this.fin = fin;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        int sumaParcial = 0;
        for (int i= ini; i < fin; i++){
            sumaParcial += arr[i];
        }
        recurso.acumular(sumaParcial);
        System.out.println("termino "+ Thread.currentThread().getName()+" con: "+recurso.getSumaTotal());
    }
}
