package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import java.util.List;

public interface IModelFactoryServices {
    public List<UsuarioDto> obtenerUsuarios();

    boolean agregarUsuario(UsuarioDto usuarioDto);
    boolean actualizarUsuario(String idUsuario,UsuarioDto usuarioDto);
    boolean eliminarUsuario(UsuarioDto usuarioDto);

}
