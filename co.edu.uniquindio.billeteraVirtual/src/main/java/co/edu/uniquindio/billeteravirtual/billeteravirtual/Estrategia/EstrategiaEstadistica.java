package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public interface EstrategiaEstadistica {
    String getTitulo();
    List<EstadisticaCategoria> calcular(List<Usuario> usuarios);
}
