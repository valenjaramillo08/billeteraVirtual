package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaReporteGastos implements EstrategiaEstadistica{

    @Override
    public String getTitulo() {
        return "Gastos personales";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        List<EstadisticaCategoria> resultado = new ArrayList<>();

        Usuario usuario = usuarios.get(0); // Solo se espera un usuario
        double totalGastos = 0;

        for (Transaccion t : usuario.getListaTransacciones()) {
            if (t.getTipoTransaccion() == TipoTransaccion.RETIRO) {
                totalGastos += t.getMonto();
            }
        }

        resultado.add(new EstadisticaCategoria("Total gastos", totalGastos));
        return resultado;
    }
}
