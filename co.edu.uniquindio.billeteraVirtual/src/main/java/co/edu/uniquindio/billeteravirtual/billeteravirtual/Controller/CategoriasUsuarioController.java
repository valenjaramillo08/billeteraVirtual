package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.NombreCategoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

import java.util.List;

public class CategoriasUsuarioController {
    ModelFactory modelFactory;

    public CategoriasUsuarioController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public Categoria obtenerCategoria(String idCategoria) {
        return  modelFactory.obtenerCategoria(idCategoria);
    }

    public boolean eliminarCategoria(String idCategoria) {
        return modelFactory.eliminarCategoria(idCategoria);
    }

    public boolean actualizarCategoria(NombreCategoria nombreCategoria, String idCategoriaActual, String idCategoria, String telefono) {
        return modelFactory.actualizarCategoria(nombreCategoria, idCategoria, idCategoriaActual, telefono);
    }

}
