package Parcial;

public class MainPizarra {
    public static void main(String[] args) {
        Pizarra pi = new Pizarra(false);
        new Thread(new Escritor("hola", pi), "Persona 1").start();
        new Thread(new Escritor("como", pi), "Persona 2").start();
        new Thread(new Escritor("estas", pi), "Persona 3").start();
        new Thread(new Escritor("todo", pi), "Persona 4").start();
        new Thread(new Escritor("bien", pi), "Persona 5").start();
    }
}
