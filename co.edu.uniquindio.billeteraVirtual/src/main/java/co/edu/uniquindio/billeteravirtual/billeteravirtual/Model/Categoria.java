package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public abstract class Categoria {
    public abstract String getIdCategoria();
    public abstract String getNombreCategoria();
    public abstract String getDescripcionCategoria();
    public abstract Presupuesto getPresupuestoAsociado();
    public abstract Usuario getUsuarioAsociado();
    public abstract Transaccion getTransaccionAsociada();

    public abstract void setNombreCategoria(String nuevoNombre);
}
