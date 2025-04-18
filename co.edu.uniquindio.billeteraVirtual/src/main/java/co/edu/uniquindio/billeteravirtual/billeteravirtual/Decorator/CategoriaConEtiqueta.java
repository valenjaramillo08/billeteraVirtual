package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Categoria;

public class CategoriaConEtiqueta extends CategoriaDecorator{
    public CategoriaConEtiqueta(Categoria categoriaDecorada) {
        super(categoriaDecorada);
    }
    @Override
    public String getNombreCategoria() {
        return super.getNombreCategoria() + " [Etiqueta Especial]";
    }
}
