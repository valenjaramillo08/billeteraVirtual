package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaccion implements IVisitable {
    public String idTransaccion;
    public TipoCuentaOrigen tipoCuentaOrigen;
    public LocalDate fechaTransaccion;
    public String descripcion;
    public TipoCuenta tipoCuenta;
    public Usuario usuarioAsociado;
    public String tipoTransaccion;
    public Administrador administradorAsociado;
    List<CategoriaBase> listaCategorias = new ArrayList<>();
    protected double monto;
    public abstract void procesar();

    public Transaccion(){}

    public Transaccion(
            String idTransaccion,
            TipoCuentaOrigen tipoCuentaOrigen,
            LocalDate fechaTransaccion,
            double monto,
            String descripcion,
            TipoCuenta tipoCuenta
    ){
        this.idTransaccion = idTransaccion;
        this.tipoCuentaOrigen = tipoCuentaOrigen;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipoCuenta = tipoCuenta;

    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public TipoCuentaOrigen getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    public void setTipoCuentaOrigen(TipoCuentaOrigen tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }


    public Administrador getAdministradorAsociado() {
        return administradorAsociado;
    }

    public void setAdministradorAsociado(Administrador administradorAsociado) {
        this.administradorAsociado = administradorAsociado;
    }

    public List<CategoriaBase> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaBase> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }
}
