package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public class GestionTransaccionesController {
    ModelFactory modelFactory;

    public GestionTransaccionesController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Transaccion> listarTransacciones(Usuario usuario) {
        return modelFactory.getAdministrador().listarTransaccionesUsuario(usuario);
    }

    public List<Cuenta> listarCuentas() {
        return modelFactory.getAdministrador().getListaCuentas();
    }

    public List<Cuenta> listarCuentasUsuario(Usuario usuario) {
        return modelFactory.getAdministrador().listarCuentasUsuarios(usuario);
    }

    public boolean crearTransaccion(Cuenta cuentaOrigen,Cuenta cuentaDestino,double monto,String descripcion,TipoTransaccion tipoTransaccion) {
        return modelFactory.getAdministrador().crearTransaccion(cuentaOrigen, cuentaDestino, monto, descripcion, tipoTransaccion);
    }

    public double saldoCuenta(Cuenta cuenta){
        return modelFactory.getAdministrador().saldoCuenta(cuenta);
    }

}
