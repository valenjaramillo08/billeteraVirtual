package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;

public interface IAdministradorServices {

    Administrador autorizarLoginAdministrador(String idAdministrador, String pass);
}
