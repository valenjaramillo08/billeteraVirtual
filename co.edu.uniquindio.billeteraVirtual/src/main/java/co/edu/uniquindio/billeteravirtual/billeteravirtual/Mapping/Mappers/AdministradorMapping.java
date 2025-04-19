package co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IAdministradorMapping;

import java.util.ArrayList;
import java.util.List;

public class AdministradorMapping implements IAdministradorMapping {


    @Override
    public List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios) {
        if(listaUsuarios == null){
            return null;
        }
        List<UsuarioDto> listaUsuariosDto = new ArrayList<UsuarioDto>(listaUsuarios.size());
        for(Usuario usuario : listaUsuarios){
            listaUsuariosDto.add(usuarioToUsuarioDto(usuario));
        }
        return listaUsuariosDto;
    }

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getContrasenaUsuario());
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        return Usuario.builder()
                .nombre(usuarioDto.nombre())
                .apellido(usuarioDto.apellido())
                .correo(usuarioDto.correo())
                .idUsuario(usuarioDto.idUsuario())
                .contrasenaUsuario(usuarioDto.contrasena())
                .build();


    }
}
