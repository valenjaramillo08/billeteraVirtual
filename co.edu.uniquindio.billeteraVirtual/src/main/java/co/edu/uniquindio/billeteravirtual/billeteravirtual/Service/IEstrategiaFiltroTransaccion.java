package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public interface IEstrategiaFiltroTransaccion {
    boolean aplicar(Transaccion transaccion);
}
