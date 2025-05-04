package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Builder.UsuarioBuilder;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

public class RegistroController {
    ModelFactory modelFactory;
    public RegistroController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public boolean agregarUsuarioRegistro(String nombre, String correo, String idUsuario, String contrasena) {
        if (modelFactory.getAdministrador() == null) return false;

        Administrador administrador = modelFactory.getAdministrador();

        if (administrador.obtenerUsuario(idUsuario) != null) return false;

        Usuario nuevoUsuario = new UsuarioBuilder()
                .nombre(nombre)
                .correo(correo)
                .idUsuario(idUsuario)
                .contrasenaUsuario(contrasena)
                .build();

        nuevoUsuario.administradorAsociado = administrador;
        return administrador.crearUsuario(nuevoUsuario);


    }


}
