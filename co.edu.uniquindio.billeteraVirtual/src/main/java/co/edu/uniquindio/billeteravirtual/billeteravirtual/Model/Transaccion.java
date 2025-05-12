package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Visitor.IVisitor;

import java.time.LocalDate;

public class Transaccion implements IVisitable {
    public String idTransaccion;
    public Cuenta CuentaOrigen;
    public LocalDate fechaTransaccion;
    public double monto;
    public String descripcion;
    public Cuenta CuentaDestino;
    public TipoTransaccion tipoTransaccion;
    public Presupuesto presupuesto;

    public Transaccion(){}

    public Transaccion(
            String idTransaccion,
            Cuenta CuentaOrigen,
            LocalDate fechaTransaccion,
            double monto,
            String descripcion,
            Cuenta CuentaDestino,
            TipoTransaccion tipoTransaccion
    ){
        this.idTransaccion = idTransaccion;
        this.CuentaOrigen = CuentaOrigen;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.CuentaDestino = CuentaDestino;
        this.tipoTransaccion = tipoTransaccion;

    }

    public void procesar(NombreCategoria nombreCategoria) {
        if (presupuesto != null && nombreCategoria != null) {
            for (Categoria categoria : presupuesto.getListaCategorias()) {
                if (categoria.getNombreCategoria() == nombreCategoria) {
                    if (categoria.getSaldo() >= monto) {
                        categoria.setSaldo(categoria.getSaldo() - monto);
                        presupuesto.notificarObservers(); // Para actualizar la UI
                        return;
                    } else {
                        throw new IllegalArgumentException("Saldo insuficiente en la categoría.");
                    }
                }
            }
            throw new IllegalArgumentException("Categoría no encontrada en el presupuesto.");
        } else if (presupuesto != null) {
            // Si no se usa categoría, se descuenta del presupuesto general
            if (presupuesto.tieneSaldoDisponible(monto)) {
                presupuesto.gastar(monto);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente en el presupuesto.");
            }
        }
    }



    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
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

    public Cuenta getCuentaOrigen() {
        return CuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        CuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return CuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        CuentaDestino = cuentaDestino;
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
