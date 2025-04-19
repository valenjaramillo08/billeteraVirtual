package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

public class perfilUsuarioController {
    ModelFactory modelFactory;
    public perfilUsuarioController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public boolean actualizarUsuarioPerfil(String nombre, String apellido, String Correo, String idUsuarioActual){
        return modelFactory.actualizarUsuarioPerfil(nombre, apellido, Correo, idUsuarioActual);
    }
    
}
