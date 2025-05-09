package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaSaldoPromedio implements EstrategiaEstadistica{

    @Override
    public String getTitulo() {
        return "Saldo promedio de usuarios";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        double suma = 0;
        int contador = 0;

        for (Usuario usuario : usuarios) {
            for (Cuenta cuenta : usuario.getListaCuentas()) {
                if (cuenta.getPresupuesto() != null) {
                    double saldo = cuenta.getPresupuesto().getMontoPresupuesto()
                            - cuenta.getPresupuesto().getMontoPresupuestoGastado();
                    suma += saldo;
                    contador++;
                }
            }
        }

        double promedio = (contador > 0) ? suma / contador : 0;

        List<EstadisticaCategoria> resultado = new ArrayList<>();
        resultado.add(new EstadisticaCategoria("Saldo Promedio", promedio));
        return resultado;
    }
}
