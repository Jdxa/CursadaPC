package TrabajoPractico7.Punto3;

public class MainCocina {
    public static void main(String[] args) {
        Cocina coc = new Cocina(1, 1, 1);

        new Thread(new CocineroEnsalada(coc), "cocEnsalada").start();
        new Thread(new CocineroPasta(coc), "cocPasta").start();
        new Thread(new CocineroCarne(coc), "cocCarne").start();
    }
}
