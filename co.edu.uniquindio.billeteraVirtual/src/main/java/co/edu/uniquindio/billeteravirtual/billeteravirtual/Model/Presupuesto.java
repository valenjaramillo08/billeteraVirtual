package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorMetodos;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Presupuesto implements IVisitable, ObservadorMetodos {
    private String idPresupuesto;
    private double montoPresupuesto;
    private double montoPresupuestoGastado;
    private List<Categoria> listaCategorias = new ArrayList<>();
    public List<Observador> observadores = new ArrayList<>();


    public Presupuesto(String idPresupuesto,  double montoPresupuesto, double montoPresupuestoGastado) {
        this.idPresupuesto = idPresupuesto;
        this.montoPresupuesto = montoPresupuesto;
        this.montoPresupuestoGastado = montoPresupuestoGastado;
    }

    public void gastar(double monto) {
        this.montoPresupuestoGastado += monto;
        notificarObservers();
    }

    
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

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<Observador> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observador> observadores) {
        this.observadores = observadores;
    }
}
