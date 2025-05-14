package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public interface CreadorTransaccion {
    Transaccion crearTransaccion(DatosTransaccion datos);
}
