package TrabajoPractico3.Punto1;

public class MainMod {
    public static void main(String[] args) {
        CuentaBancoMod cb = new CuentaBancoMod(50);
        VerificarCuentaMod vcL = new VerificarCuentaMod(cb);
        VerificarCuentaMod vcJ = new VerificarCuentaMod(cb);
        Thread lucas = new Thread(vcL, "Lucas");
        Thread jeremias = new Thread(vcJ, "Jeremias");
        lucas.start();
        jeremias.start();
    }
}
