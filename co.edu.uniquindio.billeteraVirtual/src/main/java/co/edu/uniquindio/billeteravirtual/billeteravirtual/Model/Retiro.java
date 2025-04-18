package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Retiro extends Transaccion{
    public Retiro(double monto) { this.monto = monto; }

    @Override
    public void procesar() {
        System.out.println("Retiro de $" + monto);
    }
}
