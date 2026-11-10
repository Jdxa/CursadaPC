package TrabajoPractico4.Punto8;

public class MainConfiteria {
    public static void main(String[] args) {
        Confiteria conf = new Confiteria();

        new Thread(new Mozo(conf), "mozo").start();
        new Thread(new Empleado(conf), "emp1").start();
        new Thread(new Empleado(conf), "emp2").start();
        new Thread(new Empleado(conf), "emp3").start();

    }
}
