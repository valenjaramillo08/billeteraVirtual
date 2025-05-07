package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    public String idCuenta;
    public String nombreBanco;
    public String numeroCuenta;
    public TipoCuenta tipoCuenta;
    public Usuario usuarioAsociado;
    public Administrador administradorAsociado;
    public Presupuesto presupuesto;
    public List<Transaccion> listaTransacciones = new ArrayList<>();

    public Cuenta(){}

    public Cuenta(String idCuenta,
                  String nombreBanco,
                  String numeroCuenta,
                  TipoCuenta tipoCuenta,
                  Usuario usuarioAsociado,
                  Administrador administradorAsociado) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.usuarioAsociado = usuarioAsociado;
        this.administradorAsociado = administradorAsociado;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    public Administrador getAdministradorAsociado() {
        return administradorAsociado;
    }

    public void setAdministradorAsociado(Administrador administradorAsociado) {
        this.administradorAsociado = administradorAsociado;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(List<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
}
