package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

public abstract class CategoriaDecorator extends Categoria {
    protected Categoria categoriaDecorada;

    public CategoriaDecorator(Categoria categoriaDecorada) {
        this.categoriaDecorada = categoriaDecorada;
    }

    @Override
    public String getIdCategoria() {
        return categoriaDecorada.getIdCategoria();
    }

    @Override
    public String getNombreCategoria() {
        return categoriaDecorada.getNombreCategoria();
    }

    @Override
    public String getDescripcionCategoria() {
        return categoriaDecorada.getDescripcionCategoria();
    }

    @Override
    public Presupuesto getPresupuestoAsociado() {
        return categoriaDecorada.getPresupuestoAsociado();
    }

    @Override
    public Usuario getUsuarioAsociado() {
        return categoriaDecorada.getUsuarioAsociado();
    }

    @Override
    public Transaccion getTransaccionAsociada() {
        return categoriaDecorada.getTransaccionAsociada();
    }

    @Override
    public void setNombreCategoria(String nuevoNombre) {
        categoriaDecorada.setNombreCategoria(nuevoNombre);
    }
}
