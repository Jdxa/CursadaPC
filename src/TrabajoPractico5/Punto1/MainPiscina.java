package TrabajoPractico5.Punto1;

public class MainPiscina {
    public static void main(String[] args) {
        GestionPiscina piscina = new GestionPiscina(3);

        new Thread(new Persona(piscina), "persona1").start();
        new Thread(new Persona(piscina), "persona2").start();
        new Thread(new Persona(piscina), "persona3").start();
        new Thread(new Persona(piscina), "persona4").start();
        new Thread(new Persona(piscina), "persona5").start();

    }

}
