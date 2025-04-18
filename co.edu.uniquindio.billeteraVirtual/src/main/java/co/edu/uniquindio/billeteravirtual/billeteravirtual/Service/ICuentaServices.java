package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

public interface ICuentaServices {
    boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado);
    boolean actualizarCuenta(String idCuenta,String idCuentaActual,String nombreBanco, String numeroCuenta,TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado);
    boolean eliminarCuenta(String idCuenta,String nombreBanco,String numeroCuenta,TipoCuenta tipoCuenta);
}
