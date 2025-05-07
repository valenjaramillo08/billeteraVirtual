package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transaccion implements IVisitable {
    public String idTransaccion;
    public TipoCuentaOrigen tipoCuentaOrigen;
    public LocalDate fechaTransaccion;
    public double monto;
    public String descripcion;
    public TipoCuenta tipoCuentaDestino;
    public TipoTransaccion tipoTransaccion;
    public Presupuesto presupuesto;

    public Transaccion(){}

    public Transaccion(
            String idTransaccion,
            TipoCuentaOrigen tipoCuentaOrigen,
            LocalDate fechaTransaccion,
            double monto,
            String descripcion,
            TipoCuenta tipoCuentaDestino,
            TipoTransaccion tipoTransaccion
    ){
        this.idTransaccion = idTransaccion;
        this.tipoCuentaOrigen = tipoCuentaOrigen;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipoCuentaDestino = tipoCuentaDestino;
        this.tipoTransaccion = tipoTransaccion;

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


    public TipoCuenta getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    public void setTipoCuentaDestino(TipoCuenta tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }
}
