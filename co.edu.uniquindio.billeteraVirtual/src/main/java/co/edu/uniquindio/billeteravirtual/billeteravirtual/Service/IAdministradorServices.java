package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import java.util.List;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public interface IAdministradorServices {

    Administrador autorizarLoginAdministrador(String idAdministrador, String pass);

    List<Transaccion> obtenerTransacciones();
}
