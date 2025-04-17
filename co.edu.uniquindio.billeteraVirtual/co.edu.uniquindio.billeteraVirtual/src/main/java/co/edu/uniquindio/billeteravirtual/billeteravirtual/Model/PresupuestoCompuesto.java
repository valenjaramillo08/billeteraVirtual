package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IPresupuestoComponent;

import java.util.ArrayList;
import java.util.List;

public class PresupuestoCompuesto implements IPresupuestoComponent {
    private String nombreGrupo;
    private List<IPresupuestoComponent> componentes = new ArrayList<>();

    public PresupuestoCompuesto(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public void agregar(IPresupuestoComponent componente) {
        componentes.add(componente);
    }

    public void eliminar(IPresupuestoComponent componente) {
        componentes.remove(componente);
    }

    @Override
    public double getMontoPresupuesto() {
        double total = 0;
        for (IPresupuestoComponent componente : componentes) {
            total += componente.getMontoPresupuesto();
        }
        return total;
    }

    @Override
    public double getMontoPresupuestoGastado() {
        double totalGastado = 0;
        for (IPresupuestoComponent componente : componentes) {
            totalGastado += componente.getMontoPresupuestoGastado();
        }
        return totalGastado;
    }

    @Override
    public String getNombrePresupuesto() {
        return nombreGrupo;
    }

    @Override
    public void mostrar() {
        System.out.println("Grupo de Presupuesto: " + nombreGrupo);
        for (IPresupuestoComponent c : componentes) {
            c.mostrar();
        }
        System.out.println("→ Total del grupo: $" + getMontoPresupuesto() + " | Gastado: $" + getMontoPresupuestoGastado());
    }
}
