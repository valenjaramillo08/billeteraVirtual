package co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

public class ReporteVisitor implements IVisitor {
    @Override
    public void visitar(Usuario usuario) {
        System.out.println("Usuario: " + usuario.getIdUsuario() + " | Saldo: $" + usuario.getSaldoDisponible());
    }

    @Override
    public void visitar(Presupuesto presupuesto) {
        System.out.println("Presupuesto: " + presupuesto.getListaCategorias() + " | Total: $" + presupuesto.getMontoPresupuesto());
    }

    @Override
    public void visitar(Transaccion transaccion) {
        System.out.println("Transacción: " + transaccion.getDescripcion() + " | Monto: $" + transaccion.getMonto());

    }
}
