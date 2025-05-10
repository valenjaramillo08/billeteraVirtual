package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.NombreCategoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public interface ICategoriaServices {
    public List<Categoria> getListaCategorias();

    boolean agregarCategoria(NombreCategoria nombreCategoria, String idCategoria, double saldo);

    Categoria obtenerCategoria(String idCategoria);

    boolean eliminarCategoria(String idCategoria);

    boolean actualizarCategoria(NombreCategoria nombreCategoria, String idCategoriaActual, String idCategoria, String telefono);

}
