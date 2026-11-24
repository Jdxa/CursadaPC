package Parcial.Parcial2;

public class Albanil implements Runnable {
    private CanalComunicacion canalComun;

    public Albanil(CanalComunicacion canal) {
        this.canalComun = canal;
    }

    @Override
    public void run() {
        try {
            canalComun.construir();
            Thread.sleep(500);
        } catch (Exception e) {

        }
    }
}
