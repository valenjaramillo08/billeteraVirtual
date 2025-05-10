package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Proxy.Autenticacion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

public class LoginUsuarioController implements Autenticacion {
    ModelFactory modelFactory;
    public LoginUsuarioController() {
        modelFactory = ModelFactory.getInstancia();
    }


    public Usuario obtenerUsuario(String idUsuario) {
        return modelFactory.obtenerUsuario(idUsuario);
    }

    @Override
    public boolean autenticar(String id, String clave) {
        return modelFactory.autorizarLoginUsuario(id, clave);
    }
}
