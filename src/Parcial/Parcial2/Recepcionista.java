package Parcial.Parcial2;

public class Recepcionista implements Runnable {
    private CanalComunicacion canalCom;
    private HallEntrada hallEntr;

    public Recepcionista(CanalComunicacion can, HallEntrada hall) {
        this.canalCom = can;
        this.hallEntr = hall;
    }

    @Override
    public void run() {
        try {
            int[] buffer;
            buffer = canalCom.tomar();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
