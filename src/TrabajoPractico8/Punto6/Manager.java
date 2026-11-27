package TrabajoPractico8.Punto6;

public class Manager implements Runnable {

    private Album album;

    public Manager(Album a) {
        this.album = a;
    }

    @Override
    public void run() {
        try {
            while (true) {
                album.promocionar();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
