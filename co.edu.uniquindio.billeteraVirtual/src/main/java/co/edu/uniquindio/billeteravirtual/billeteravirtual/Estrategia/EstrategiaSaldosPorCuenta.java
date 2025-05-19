package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaSaldosPorCuenta implements EstrategiaEstadistica{
    @Override
    public String getTitulo() {
        return "Saldos por cuenta";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        List<EstadisticaCategoria> resultado = new ArrayList<>();

        Usuario usuario = usuarios.get(0); // Solo se espera un usuario

        for (Cuenta cuenta : usuario.getListaCuentas()) {
            if (cuenta.getPresupuesto() != null) {
                double saldo = cuenta.getPresupuesto().getMontoPresupuesto()
                        - cuenta.getPresupuesto().getMontoPresupuestoGastado();
                resultado.add(new EstadisticaCategoria("Cuenta " + cuenta.getNumeroCuenta(), saldo));
            }
        }

        return resultado;
    }
}
