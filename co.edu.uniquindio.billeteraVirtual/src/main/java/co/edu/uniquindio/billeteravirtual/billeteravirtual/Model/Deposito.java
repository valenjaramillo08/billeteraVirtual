package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Deposito extends Transaccion{
    public Deposito(double monto) { this.monto = monto; }

    @Override
    public void procesar() {
        System.out.println("Depósito de $" + monto);

    }
}
