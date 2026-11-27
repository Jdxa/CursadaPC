package Parcial.Parcial2;

public class Albanil implements Runnable {
    private CanalComunicacion canalComun;

    public Albanil(CanalComunicacion canal) {
        this.canalComun = canal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                canalComun.construir();
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        }
    }
}
