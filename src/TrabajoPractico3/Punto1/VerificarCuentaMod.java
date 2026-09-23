package TrabajoPractico3.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificarCuentaMod implements Runnable {
    private final CuentaBancoMod cb;

    public VerificarCuentaMod(CuentaBancoMod unaCuenta) {
        this.cb = unaCuenta;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 3; i++) {
            try {
                if (!cb.hacerRetiro(10)) {
                    System.out.println("Cuenta está sobregirada");
                }
            } catch (InterruptedException e) {
                Logger.getLogger(VerificarCuentaMod.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
