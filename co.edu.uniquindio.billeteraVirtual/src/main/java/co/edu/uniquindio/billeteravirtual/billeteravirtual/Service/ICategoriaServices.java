package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Categoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public interface ICategoriaServices {
    public List<Categoria> getListaCategorias();

    boolean agregarCategoria(String nombre);

    Categoria obtenerCategoria(String idUsuario);

    boolean eliminarUsuario(String idUsuario);

    boolean actualizarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuarioActual, String idUsuario, String direccion);
    boolean autorizarLoginUsuario(String idUsuario, String pass);
    boolean actualizarUsuarioPerfil(String nombre, String apellido, String correo, String idUsuarioActual);
}
