package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import java.time.LocalDate;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;

public class DatosTransaccion {
    public String idTransaccion;
    public Cuenta CuentaOrigen;
    public LocalDate fechaTransaccion;
    public double monto;
    public String descripcion;
    public Cuenta CuentaDestino;
    public TipoTransaccion tipoTransaccion;
    public Presupuesto presupuesto;
    public Categoria categoria;

    public DatosTransaccion(String idTransaccion, Cuenta CuentaOrigen, LocalDate fechaTransaccion,
                            double monto, String descripcion, Cuenta CuentaDestino,
                            TipoTransaccion tipoTransaccion, Presupuesto presupuesto) {
        this.idTransaccion = idTransaccion;
        this.CuentaOrigen = CuentaOrigen;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.CuentaDestino = CuentaDestino;
        this.tipoTransaccion = tipoTransaccion;
        this.presupuesto = presupuesto;

    }
}
