package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorMetodos;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Presupuesto implements IVisitable, ObservadorMetodos {
    private String idPresupuesto;
    private String nombrePresupuesto;
    private double montoPresupuesto;
    private double montoPresupuestoGastado;
    private Categoria categoria;
    public List<Observador> observadores = new ArrayList<>();


    public Presupuesto(String idPresupuesto, String nombrePresupuesto, double montoPresupuesto, double montoPresupuestoGastado, Categoria categoria) {
        this.idPresupuesto = idPresupuesto;
        this.nombrePresupuesto = nombrePresupuesto;
        this.montoPresupuesto = montoPresupuesto;
        this.montoPresupuestoGastado = montoPresupuestoGastado;
        this.categoria= categoria;
    }

     /*/public void gastar(double monto) {
        this.montoPresupuestoGastado += monto;
        notificarObservers();
    }/*/

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public void agregarObserver(Observador observador) {
        observadores.add(observador);

    }

    @Override
    public void eliminarObserver(Observador observador) {
        observadores.remove(observador);

    }

    @Override
    public void notificarObservers() {
        for (Observador observador : observadores){
            observador.actualizar(this);
        }

    }

    public String getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(String idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getMontoPresupuestoGastado() {
        return montoPresupuestoGastado;
    }

    public void setMontoPresupuestoGastado(double montoPresupuestoGastado) {
        this.montoPresupuestoGastado = montoPresupuestoGastado;
    }

    public double getMontoPresupuesto() {
        return montoPresupuesto;
    }

    public void setMontoPresupuesto(double montoPresupuesto) {
        this.montoPresupuesto = montoPresupuesto;
    }

    public String getNombrePresupuesto() {
        return nombrePresupuesto;
    }

    public void setNombrePresupuesto(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }

    public List<Observador> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observador> observadores) {
        this.observadores = observadores;
    }
}
