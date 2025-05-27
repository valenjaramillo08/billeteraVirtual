package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.TransaccionD;

import java.time.LocalDate;

public class Transaccion implements TransaccionD {
    private String idTransaccion;
    private Cuenta cuentaOrigen;
    private LocalDate fechaTransaccion;
    private double monto;
    private String descripcion;
    private Cuenta cuentaDestino;
    private TipoTransaccion tipoTransaccion;
    private Presupuesto presupuesto;
    private NombreCategoria categoriaProcesada;
    

    public Transaccion() {}

    public Transaccion(
            String idTransaccion,
            Cuenta cuentaOrigen,
            LocalDate fechaTransaccion,
            double monto,
            String descripcion,
            Cuenta cuentaDestino,
            TipoTransaccion tipoTransaccion
          
    ) {
        this.idTransaccion = idTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cuentaDestino = cuentaDestino;
        this.tipoTransaccion = tipoTransaccion;
        
    }
    public Transaccion(Transaccion otra){
       this.idTransaccion = otra.idTransaccion;
        this.cuentaOrigen = otra.cuentaOrigen;
        this.cuentaDestino = otra.cuentaDestino;
        this.fechaTransaccion = otra.fechaTransaccion;
        this.monto = otra.monto;
        this.descripcion = otra.descripcion;
        this.tipoTransaccion = otra.tipoTransaccion; 

    }

    public void procesar(NombreCategoria nombreCategoria) {
        this.categoriaProcesada = nombreCategoria;

        if (presupuesto != null && nombreCategoria != null) {
            for (Categoria categoria : presupuesto.getListaCategorias()) {
                if (categoria.getNombreCategoria().equals(nombreCategoria)) {
                    if (categoria.getSaldo() >= monto) {
                        categoria.setSaldo(categoria.getSaldo() - monto);
                        presupuesto.notificarObservers(); 
                        return;
                    } else {
                        throw new IllegalArgumentException("Saldo insuficiente en la categoría.");
                    }
                }
            }
            throw new IllegalArgumentException("Categoría no encontrada en el presupuesto.");
        } else if (presupuesto != null) {
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
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
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

    public NombreCategoria getCategoriaProcesada() {
        return categoriaProcesada;
    }

    public void setCategoriaProcesada(NombreCategoria categoriaProcesada) {
        this.categoriaProcesada = categoriaProcesada;
    }


    @Override
    public void ejecutar() {
        this.procesar(this.categoriaProcesada);
        System.out.println("Transaccion ejecutada correctamente.");
    }

}
