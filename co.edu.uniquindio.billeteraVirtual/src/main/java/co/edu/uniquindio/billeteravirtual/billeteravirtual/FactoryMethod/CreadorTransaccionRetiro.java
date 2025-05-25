package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class CreadorTransaccionRetiro implements CreadorTransaccion {
    @Override
    public Transaccion crearTransaccion(DatosTransaccion datos) {
        return new Transaccion(
            datos.idTransaccion,
            datos.CuentaOrigen,
            datos.fechaTransaccion,
            datos.monto,
            datos.descripcion,
            null, 
            TipoTransaccion.RETIRO
        );
    }
}
