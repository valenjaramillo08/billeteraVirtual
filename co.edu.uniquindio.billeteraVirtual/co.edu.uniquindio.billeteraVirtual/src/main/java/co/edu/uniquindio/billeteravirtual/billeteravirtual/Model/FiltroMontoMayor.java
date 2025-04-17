package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IEstrategiaFiltroTransaccion;

public class FiltroMontoMayor implements IEstrategiaFiltroTransaccion {
    private double limite;

    public FiltroMontoMayor(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean aplicar(Transaccion transaccion) {
        return transaccion.getMonto() > limite;
    }
}
