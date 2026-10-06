package TrabajoPractico3.Punto2;

public class Sanador implements Runnable {
    private Energia recurso;

    public Sanador(Energia recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        while(recurso.getPoder()>0&&recurso.getPoder()<20){
            recurso.modificarPoder(3);
        }
    }
}
