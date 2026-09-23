package TrabajoPractico3.Punto1;

public class CuentaBancoMod {
    private int balance;

    public CuentaBancoMod(int bal) {
        this.balance = bal;
    }

    public int getBalance() {
        return this.balance;
    }

    public synchronized boolean hacerRetiro(int cantidad) throws InterruptedException {
        boolean puedeRetirar = this.balance >= cantidad;
        if (puedeRetirar) {
            System.out.println(Thread.currentThread().getName() + " está realizando un retiro de: " + cantidad);
            Thread.sleep(1000); // un segundo
            this.balance -= cantidad;
            System.out.println(Thread.currentThread().getName() + ": Retiro Realizado");
            System.out.println(Thread.currentThread().getName() + ": Los fondos son de: " + this.balance);
        } else {
            System.out.println("No hay suficiente dinero en la cuenta para realizar el retiro Sr. "
                    + Thread.currentThread().getName());
            System.out.println("Su saldo actual es de: " + this.balance);
            Thread.sleep(1000);
        }
        return puedeRetirar;
    }
}
