package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IPresupuestoComponent;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitor;

public class Presupuesto implements IPresupuestoComponent, IVisitable {
    private String idPresupuesto;
    private String nombrePresupuesto;
    private double montoPresupuesto;
    private double montoPresupuestoGastado;
    private Usuario usuarioAsociado;

    public Presupuesto(String idPresupuesto, String nombrePresupuesto, double montoPresupuesto, double montoPresupuestoGastado) {
        this.idPresupuesto = idPresupuesto;
        this.nombrePresupuesto = nombrePresupuesto;
        this.montoPresupuesto = montoPresupuesto;
        this.montoPresupuestoGastado = montoPresupuestoGastado;
    }
    @Override
    public double getMontoPresupuesto() {
        return montoPresupuesto;
    }

    @Override
    public double getMontoPresupuestoGastado() {
        return montoPresupuestoGastado;
    }

    @Override
    public String getNombrePresupuesto() {
        return nombrePresupuesto;
    }

    @Override
    public void mostrar() {
        System.out.println("Presupuesto: " + nombrePresupuesto + " | Total: $" + montoPresupuesto + " | Gastado: $" + montoPresupuestoGastado);
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }
}
