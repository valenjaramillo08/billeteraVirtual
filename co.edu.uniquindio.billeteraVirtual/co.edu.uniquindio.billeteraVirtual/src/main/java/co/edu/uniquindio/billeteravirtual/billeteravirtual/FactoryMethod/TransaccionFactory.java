package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Deposito;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Retiro;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class TransaccionFactory {
    public static Transaccion crearTransaccion(String tipo, double monto) {
        switch (tipo.toLowerCase()) {
            case "deposito": return new Deposito(monto);
            case "retiro": return new Retiro(monto);
            default: throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
