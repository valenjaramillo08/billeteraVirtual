package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

public class LoginAdminController {
    ModelFactory modelFactory;
    public LoginAdminController() {
        modelFactory = ModelFactory.getInstancia();
    }
    public Administrador loginAdmin(String idAdmin, String passAdmin) {
        return modelFactory.autorizarLoginAdministrador(idAdmin, passAdmin);
    }
}
