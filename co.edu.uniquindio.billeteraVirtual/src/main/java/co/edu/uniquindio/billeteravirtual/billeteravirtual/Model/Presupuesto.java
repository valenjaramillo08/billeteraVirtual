package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorMetodos;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ICategoriaServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto implements IVisitable, ObservadorMetodos, ICategoriaServices {
    private String idPresupuesto;
    private double montoPresupuesto;
    private double montoPresupuestoGastado;
    private List<Categoria> listaCategorias = new ArrayList<>();
    public List<Observador> observadores = new ArrayList<>();
    public List<RegistroPresupuesto> listaRegistros = new ArrayList<>();

    public Presupuesto( double montoPresupuesto, double montoPresupuestoGastado) {
        this.idPresupuesto = java.util.UUID.randomUUID().toString();
        this.montoPresupuesto = montoPresupuesto;
        this.montoPresupuestoGastado = montoPresupuestoGastado;
    }

    public boolean tieneSaldoDisponible(double saldo) {
        return (montoPresupuesto - montoPresupuestoGastado) >= saldo;
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
        listaRegistros.add(new RegistroPresupuesto(LocalDate.now(), montoPresupuesto));
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    @Override
    public boolean agregarCategoria(NombreCategoria nombreCategoria, String idCategoria, double saldo) {
        Categoria existente = obtenerCategoriaPorNombre(nombreCategoria);
        if (existente == null && tieneSaldoDisponible(saldo)) {
            Categoria nueva = new Categoria();
            nueva.setIdCategoria(idCategoria);
            nueva.setNombreCategoria(nombreCategoria);
            nueva.setSaldo(saldo);
            listaCategorias.add(nueva);
            gastar(saldo); 
            return true;
        }
        return false;
    }

    @Override
    public Categoria obtenerCategoria(String idCategoria) {
        for (Categoria categoria : listaCategorias) {
            if (categoria.getIdCategoria().equals(idCategoria)) {
                return categoria;
            }
        }
        return null;
    }

    public Categoria obtenerCategoriaPorNombre(NombreCategoria nombreCategoria) {
        for (Categoria categoria : listaCategorias) {
            if (categoria.getNombreCategoria().equals(nombreCategoria)) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCategoria(String idCategoria) {
        Categoria categoria = obtenerCategoria(idCategoria);
        if (categoria != null) {
            listaCategorias.remove(categoria);
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

    public double getMontoEnFecha(LocalDate fecha) {
        double monto = 0;
        for (RegistroPresupuesto registro : listaRegistros) {
            if (!registro.getFecha().isEqual(fecha)) {
                monto = registro.getMonto();
            }else{
                break;
            }
        }
        return monto;
    }

    @Override
    public String toString() {
        return "Presupuesto [montoPresupuesto=" + montoPresupuesto + ", montoPresupuestoGastado="
                + montoPresupuestoGastado + "]";
    }


    
}
