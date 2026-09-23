package TrabajoPractico3.Punto1;

public class CuentaBanco {
    private int balance;

    public CuentaBanco(int bal) {
        this.balance = bal;
    }

    public int getBalance() {
        return this.balance;
    }

    public void retiroBancario(int retiro) {
        this.balance -= retiro;
    }
}
