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
            while (true) {
                int[] buffer;
                buffer = canalCom.tomar();
                hallEntr.poner(buffer);
                hallEntr.avisar();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
