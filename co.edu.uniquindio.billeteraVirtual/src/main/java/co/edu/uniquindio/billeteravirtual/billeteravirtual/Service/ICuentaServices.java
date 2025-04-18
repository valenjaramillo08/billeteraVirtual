package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;

public interface ICuentaServices {
    boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta);
    boolean actualizarCuenta(String idCuenta,String idCuentaActual,String nombreBanco, String numeroCuenta,TipoCuenta tipoCuenta);
    boolean eliminarCuenta(String idCuenta,String nombreBanco,String numeroCuenta,TipoCuenta tipoCuenta);
}
