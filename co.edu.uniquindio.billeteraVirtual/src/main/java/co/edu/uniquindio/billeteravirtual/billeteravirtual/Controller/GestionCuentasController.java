package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

import java.util.List;

public class GestionCuentasController {
    ModelFactory modelFactory;
    public GestionCuentasController(){
        modelFactory = ModelFactory.getInstancia();
    }
    public Usuario obtenerUsuario(String idUsuario) {
        return modelFactory.obtenerUsuario(idUsuario);
    }

    public boolean eliminarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        return modelFactory.eliminarCuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta);
    }

    public boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuario, Administrador administrador) {
        return modelFactory.agregarCuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta, usuario, administrador);
    }

    public boolean actualizarCuenta(String idCuenta,String idCuentaActual, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuario, Administrador administrador) {
        return modelFactory.actualizarCuenta(idCuenta,idCuentaActual, nombreBanco, numeroCuenta, tipoCuenta, usuario, administrador);
    }

    public List<Cuenta> obtenerCuentas() {
        return modelFactory.obtenerCuentas();
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return modelFactory.getListaUsuarios(); // Este método ya existe
    }

}
