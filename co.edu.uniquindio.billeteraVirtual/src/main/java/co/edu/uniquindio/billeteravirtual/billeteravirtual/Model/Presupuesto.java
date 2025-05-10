package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorMetodos;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ICategoriaServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Presupuesto implements IVisitable, ObservadorMetodos, ICategoriaServices {
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

    public boolean tieneSaldoDisponible(double saldo) {
        return (montoPresupuesto - montoPresupuestoGastado) >= saldo;
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

    @Override
    public boolean agregarCategoria(NombreCategoria nombreCategoria, String idCategoria, double saldo) {
        Categoria categoria = obtenerCategoria(idCategoria);
        if (categoria == null) {
            categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setNombreCategoria(nombreCategoria);
            categoria.setSaldo(saldo);
            getListaCategorias().add(categoria);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Categoria obtenerCategoria(String idCategoria) {
        Categoria categoriaEncontrada = null;
        for(Categoria categoria : getListaCategorias()){
            if(categoria.getIdCategoria().equals(idCategoria)){
                categoriaEncontrada = categoria;
                break;
            }
        }
        return categoriaEncontrada;
    }

    @Override
    public boolean eliminarCategoria(String idCategoria) {
        Categoria categoria= obtenerCategoria(idCategoria);
        if(categoria != null){
            getListaCategorias().remove(categoria);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarCategoria(NombreCategoria nombreCategoria, String idCategoriaActual, String idCategoria, String telefono) {
        return false;
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
