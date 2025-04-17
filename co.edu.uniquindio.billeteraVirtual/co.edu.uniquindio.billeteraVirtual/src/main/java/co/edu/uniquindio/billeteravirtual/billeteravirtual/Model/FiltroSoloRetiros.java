package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IEstrategiaFiltroTransaccion;

public class FiltroSoloRetiros implements IEstrategiaFiltroTransaccion {


    @Override
    public boolean aplicar(Transaccion transaccion) {
        return transaccion instanceof Retiro;
    }


}
