package test;

//suma 10 por cada hilo usando un mismo contador
public class PrincipalTEST {
    public static void main(String[] args) throws Exception {

        Datos dato1 = new Datos(0); // recurso
        ProcesoI proc = new ProcesoI(dato1); // interfaz1
        ProcesoI proc2 = new ProcesoI(dato1);
        Thread t1 = new Thread(proc, "proceso1");
        Thread t2 = new Thread(proc2, "proceso2");

        t1.start();
        t2.start();
    }
}
