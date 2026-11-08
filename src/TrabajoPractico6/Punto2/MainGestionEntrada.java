package TrabajoPractico6.Punto2;

public class MainGestionEntrada {
    public static void main(String[] args) {
        SalaEstudio salon = new SalaEstudio(10);
        new Thread(new Estudiante(salon), "estudiante1").start();
        new Thread(new Estudiante(salon), "estudiante2").start();
        new Thread(new Estudiante(salon), "estudiante3").start();
        new Thread(new Estudiante(salon), "estudiante4").start();
        new Thread(new Estudiante(salon), "estudiante5").start();
        new Thread(new Estudiante(salon), "estudiante6").start();
        new Thread(new Estudiante(salon), "estudiante7").start();
        new Thread(new Estudiante(salon), "estudiante8").start();
        new Thread(new Estudiante(salon), "estudiante9").start();
        new Thread(new Estudiante(salon), "estudiante10").start();
        new Thread(new Estudiante(salon), "estudiante11").start();
        new Thread(new Estudiante(salon), "estudiante12").start();
        new Thread(new Estudiante(salon), "estudiante13").start();
    }
}
