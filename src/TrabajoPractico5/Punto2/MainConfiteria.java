package TrabajoPractico5.Punto2;

public class MainConfiteria {
    public static void main(String[] args) {
        Confiteria confiteria = new Confiteria();

        new Thread(new Mozo(confiteria), "mozo").start();
        new Thread(new Cocinero(confiteria), "cocinero").start();

        new Thread(new Empleado(confiteria, true, true), "emp1").start();
        new Thread(new Empleado(confiteria, true, false), "emp2").start();
        new Thread(new Empleado(confiteria, false, true), "emp3").start();
    }
}
