package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

import java.util.List;

public class GestionUsuariosController {
    ModelFactory modelFactory;
    public GestionUsuariosController(){

        modelFactory = ModelFactory.getInstancia();
    }

    public List<UsuarioDto> obtenerUsuarios() {

        return modelFactory.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuarioDto) {

        return modelFactory.agregarUsuario(usuarioDto);
    }
    public boolean actualizarUsuario(String idUsuario, UsuarioDto usuarioDto) {
        return modelFactory.actualizarUsuario(idUsuario ,usuarioDto);
    }
    public boolean eliminarUsuario(UsuarioDto usuarioDto) {
        return modelFactory.eliminarUsuario(usuarioDto);
    }
}
