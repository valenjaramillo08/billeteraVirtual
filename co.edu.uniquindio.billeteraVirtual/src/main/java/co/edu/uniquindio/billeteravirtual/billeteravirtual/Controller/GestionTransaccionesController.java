package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.DatosTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;

import java.time.LocalDate;
import java.util.UUID;

public class GestionTransaccionesController {
    ModelFactory modelFactory;

    public GestionTransaccionesController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public boolean crearTransaccion(
        Cuenta cuentaOrigen, // puede ser null
        Cuenta cuentaDestino, // puede ser null
        double monto,
        String descripcion,
        TipoTransaccion tipoTransaccion
) {
    String idTransaccion = UUID.randomUUID().toString();
    LocalDate fecha = LocalDate.now();

    // Validación según tipo de transacción
    if (tipoTransaccion == TipoTransaccion.RETIRO && cuentaOrigen == null) {
        System.out.println("Error: Cuenta origen requerida para retiro.");
        return false;
    }

    if (tipoTransaccion == TipoTransaccion.DEPOSITO && cuentaDestino == null) {
        System.out.println("Error: Cuenta destino requerida para depósito.");
        return false;
    }

    if (tipoTransaccion == TipoTransaccion.TRANSFERENCIA && (cuentaOrigen == null || cuentaDestino == null)) {
        System.out.println("Error: Ambas cuentas requeridas para transferencia.");
        return false;
    }

    DatosTransaccion datos = new DatosTransaccion(
        idTransaccion,
        cuentaOrigen,
        fecha,
        monto,
        descripcion,
        cuentaDestino,
        tipoTransaccion,
        null
    );

    return modelFactory.getAdministrador().registrarTransaccion(datos);
}

}
