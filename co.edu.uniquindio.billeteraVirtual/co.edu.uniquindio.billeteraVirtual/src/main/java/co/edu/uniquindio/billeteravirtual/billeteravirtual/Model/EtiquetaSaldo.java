package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IObservadorSaldo;

public class EtiquetaSaldo implements IObservadorSaldo {
    private String nombreEtiqueta;

    public EtiquetaSaldo(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    @Override
    public void actualizar(double nuevoSaldo) {
        System.out.println("[" + nombreEtiqueta + "] El saldo se actualizó: $" + nuevoSaldo);
    }
}
