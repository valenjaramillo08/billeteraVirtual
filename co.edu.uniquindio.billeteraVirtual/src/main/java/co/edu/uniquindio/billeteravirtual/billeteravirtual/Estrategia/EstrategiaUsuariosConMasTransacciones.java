package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaUsuariosConMasTransacciones implements EstrategiaEstadistica {
    @Override
    public String getTitulo() {
        return "Usuarios con más transacciones";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        List<EstadisticaCategoria> resultado = new ArrayList<>();

        for (Usuario u : usuarios) {
            int total = 0;
            for (Cuenta cuenta : u.getListaCuentas()) {
                total += cuenta.getListaTransacciones().size();
            }
            resultado.add(new EstadisticaCategoria(u.getNombre(), total));
        }

        return resultado;
    }
}
