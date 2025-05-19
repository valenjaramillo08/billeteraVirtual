package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaReporteIngresos implements EstrategiaEstadistica {

    @Override
    public String getTitulo() {
        return "Ingresos personales";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        List<EstadisticaCategoria> resultado = new ArrayList<>();
        Usuario usuario = usuarios.get(0); // Solo se espera uno

        double totalIngresos = usuario.getListaTransacciones().stream()
                .filter(t -> t.getTipoTransaccion() == TipoTransaccion.DEPOSITO)
                .mapToDouble(Transaccion::getMonto)
                .sum();

        resultado.add(new EstadisticaCategoria("Total ingresos", totalIngresos));
        return resultado;
    }
}
