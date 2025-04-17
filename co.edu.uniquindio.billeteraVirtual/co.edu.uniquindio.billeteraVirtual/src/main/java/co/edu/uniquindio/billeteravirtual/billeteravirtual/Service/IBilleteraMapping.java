package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import java.util.List;

public interface IAdministradorMapping {
    List<UsuarioDto> getUsuariosDto (List<Usuario> listaUsuarios);
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
}
