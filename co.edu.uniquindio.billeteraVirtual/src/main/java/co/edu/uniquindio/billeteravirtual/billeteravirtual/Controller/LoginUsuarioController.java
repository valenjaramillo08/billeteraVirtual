package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

public class LoginUsuarioController {
    ModelFactory modelFactory;
    public LoginUsuarioController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public boolean autorizarLoginUsuario(String idUsuario, String pass) {
        return modelFactory.autorizarLoginUsuario(idUsuario, pass);
    }

    public Usuario obtenerUsuario(String idUsuario) {
        return modelFactory.obtenerUsuario(idUsuario);
    }
}
