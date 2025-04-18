package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class CategoriaBase extends Categoria{
    private String idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Presupuesto presupuestoAsociado;
    private Usuario usuarioAsociado;
    private Transaccion transaccionAsociada;

    public CategoriaBase(String idCategoria, String nombreCategoria, String descripcionCategoria,
                         Presupuesto presupuestoAsociado, Usuario usuarioAsociado, Transaccion transaccionAsociada) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.presupuestoAsociado = presupuestoAsociado;
        this.usuarioAsociado = usuarioAsociado;
        this.transaccionAsociada = transaccionAsociada;
    }

    @Override
    public String getIdCategoria() {
        return idCategoria;
    }

    @Override
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    @Override
    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    @Override
    public Presupuesto getPresupuestoAsociado() {
        return presupuestoAsociado;
    }

    @Override
    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    @Override
    public Transaccion getTransaccionAsociada() {
        return transaccionAsociada;
    }

    @Override
    public void setNombreCategoria(String nuevoNombre) {
        this.nombreCategoria = nuevoNombre;
    }
}
