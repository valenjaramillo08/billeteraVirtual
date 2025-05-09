package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaGastosPorCategoria implements EstrategiaEstadistica {
    @Override
    public String getTitulo() {
        return "Gastos más comunes por categoría";
    }

    @Override
    public List<EstadisticaCategoria> calcular(List<Usuario> usuarios) {
        List<EstadisticaCategoria> resultado = new ArrayList<>();
        List<String> categoriasRegistradas = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            for (Cuenta cuenta : usuario.getListaCuentas()) {
                Presupuesto presupuesto = cuenta.getPresupuesto();
               if (presupuesto != null ) {
                   String nombreCategoria = "presupuesto.getCategoria().getNombre()";
                    double gasto = presupuesto.getMontoPresupuestoGastado();

                    boolean encontrada = false;
                    for (EstadisticaCategoria ec : resultado) {
                       if (ec.getNombre().equals(nombreCategoria)) {
                            ec.sumarValor(gasto);
                            encontrada = true;
                            break;
                        }
                    }

                   if (!encontrada) {
                       resultado.add(new EstadisticaCategoria(nombreCategoria, gasto));
                    }
                }
            }
        }
        return resultado;
    }
}
