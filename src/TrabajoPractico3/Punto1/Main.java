package TrabajoPractico3.Punto1;

public class Main {
    public static void main(String[] args) {
        CuentaBanco cb = new CuentaBanco(50);
        VerificarCuenta vcL = new VerificarCuenta(cb); // lucas
        VerificarCuenta vcJ = new VerificarCuenta(cb); // jeremias
        Thread lucas = new Thread(vcL, "Lucas");
        Thread jeremias = new Thread(vcJ, "Jeremias");
        lucas.start();
        jeremias.start();

    }
}
