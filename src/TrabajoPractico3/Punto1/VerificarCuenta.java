package TrabajoPractico3.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificarCuenta implements Runnable {
    private final CuentaBanco cb;

    // constructor
    public VerificarCuenta(CuentaBanco unaCuenta) {
        this.cb = unaCuenta;
    }

    private void hacerRetiro(int cantidad) throws InterruptedException {
        synchronized (cb) {
            // zona de mutex
            if (cb.getBalance() >= cantidad) {
                System.out.println(Thread.currentThread().getName() + " está realizando un retiro de: " + cantidad);
                Thread.sleep(1000); // un segundo
                cb.retiroBancario(cantidad);
                System.out.println(Thread.currentThread().getName() + ": Retiro Realizado");
                System.out.println(Thread.currentThread().getName() + ": Los fondos son de: " + cb.getBalance());
            } else {
                System.out.println("No hay suficiente dinero en la cuenta para realizar el retiro Sr. "
                        + Thread.currentThread().getName());
                System.out.println("Su saldo actual es de: " + cb.getBalance());
                Thread.sleep(1000);
            }
        }

    }

    @Override
    public void run() {
        for (int i = 0; i <= 3; i++) {
            try {
                this.hacerRetiro(10);
                if (cb.getBalance() < 0) {
                    System.out.println("Cuenta está sobregirada");
                }
            } catch (InterruptedException e) {
                Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
