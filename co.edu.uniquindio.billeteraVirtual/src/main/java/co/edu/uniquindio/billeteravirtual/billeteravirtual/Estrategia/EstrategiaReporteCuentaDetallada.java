package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaReporteCuentaDetallada implements EstrategiaCuenta {
    @Override
    public String getTitulo() {
        return "Reporte Detallado de Cuenta";
    }

    @Override
    public List<String[]> generarContenido(Cuenta cuenta) {
        List<String[]> contenido = new ArrayList<>();

        contenido.add(new String[]{"Información Cuenta"});
        contenido.add(new String[]{"Número de Cuenta:", cuenta.getNumeroCuenta()});
        contenido.add(new String[]{"Banco:", cuenta.getNombreBanco()});
        contenido.add(new String[]{"Saldo Actual:", "$" + String.format("%,.2f", cuenta.getPresupuesto().getMontoPresupuesto())});
        contenido.add(new String[]{""});

        contenido.add(new String[]{"Fecha", "Descripción", "Tipo", "Monto"});

        for (Transaccion t : cuenta.getListaTransacciones()) {
            contenido.add(new String[]{
                    t.getFechaTransaccion().toString(),
                    t.getDescripcion(),
                    t.getTipoTransaccion().toString(),
                    "$" + String.format("%,.2f", t.getMonto())
            });
        }

        return contenido;
    }
}

