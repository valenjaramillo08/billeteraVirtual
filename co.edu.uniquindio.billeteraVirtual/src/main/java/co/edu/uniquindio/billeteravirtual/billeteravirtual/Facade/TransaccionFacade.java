package co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class TransaccionFacade {

    public void procesarTransaccion(Transaccion t) {
        if (t.getPresupuesto() != null) {
            //t.getPresupuesto().gastar(t.getMonto());
        }
            // Aquí guardarías la transacción o registrarías en logs
        System.out.println("Transacción procesada: $" + t.getMonto());
    }
}


