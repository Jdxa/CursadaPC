package TrabajoPractico3.Punto2;

public class CriaturaOscura implements Runnable {
    private Energia recurso;

    public CriaturaOscura(Energia recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        while(recurso.getPoder()>0&&recurso.getPoder()<20){
            recurso.modificarPoder(-3);
        }
    }
}
