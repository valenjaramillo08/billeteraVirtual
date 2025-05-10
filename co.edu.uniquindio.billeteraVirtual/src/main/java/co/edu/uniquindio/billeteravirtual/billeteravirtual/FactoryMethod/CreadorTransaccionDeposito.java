package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class CreadorTransaccionDeposito implements CreadorTransaccion {
    @Override
    public Transaccion crearTransaccion(DatosTransaccion datos) {
        return new Transaccion(
            datos.idTransaccion,
            null,
            datos.fechaTransaccion,
            datos.monto,
            datos.descripcion,
            datos.CuentaDestino,
            TipoTransaccion.DEPOSITO
        );
    }
}

