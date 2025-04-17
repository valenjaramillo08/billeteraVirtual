package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IEstrategiaFiltroTransaccion;

import java.util.ArrayList;
import java.util.List;

public class FiltroTransaccionesContexto {
    private IEstrategiaFiltroTransaccion estrategia;

    public void setEstrategia(IEstrategiaFiltroTransaccion estrategia) {
        this.estrategia = estrategia;
    }

    public List<Transaccion> aplicarFiltro(List<Transaccion> transacciones) {
        List<Transaccion> resultado = new ArrayList<>();
        for (Transaccion t : transacciones) {
            if (estrategia.aplicar(t)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}
