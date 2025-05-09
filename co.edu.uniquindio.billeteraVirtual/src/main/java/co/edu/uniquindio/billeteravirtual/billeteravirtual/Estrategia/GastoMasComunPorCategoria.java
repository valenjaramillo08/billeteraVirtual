package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public class GastoMasComunPorCategoria implements EstrategiaEstadistica{

    @Override
    public String getTitulo() {
        return "";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        return List.of();
    }
}
