package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IAdministradorServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ICuentaServices;

import java.util.ArrayList;
import java.util.List;

public class Billetera implements IAdministradorServices, ICuentaServices {
    List<Cuenta> listaCuentas = new ArrayList<>();
    List<Presupuesto> listaPresupuestos = new ArrayList<>();
    List<Transaccion> listaTransaccioness = new ArrayList<>();
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Administrador> listaAdministradores = new ArrayList<>();
    List<Categoria> listaCategorias = new ArrayList<>();

    public boolean crearUsuario(String nombre,
                                String apellido,
                                String correo,
                                String telefono,
                                String idUsuario,
                                String direccion,
                                double saldoDisponible,
                                String contrasenaUsuario){
        Usuario usuarioEncontrado = obtenerUsuario(idUsuario);
        if(usuarioEncontrado == null){
            Usuario usuario = getBuildUsuario(nombre, apellido, correo, telefono,idUsuario,direccion, saldoDisponible,contrasenaUsuario);
            getListaUsuarios().add(usuario);
            return true;
        }else{
            return  false;
        }
    }

    public boolean crearUsuario(Usuario nuevoUsuario){
        Usuario usuarioEncontrado = obtenerUsuario(nuevoUsuario.getIdUsuario());
        if(usuarioEncontrado == null){
            getListaUsuarios().add(nuevoUsuario);
            return true;
        }else{
            return  false;
        }
    }

    private Usuario getBuildUsuario(String nombre, String apellido, String correo, String telefono, String idUsuario, String direccion, double saldoDisponible, String contrasenaUsuario) {
        return Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .telefono(telefono)
                .idUsuario(idUsuario)
                .direccion(direccion)
                .saldoDisponible(saldoDisponible)
                .contrasenaUsuario(contrasenaUsuario)
                .build();
    }

    private Usuario obtenerUsuario(String idUsuario) {
        Usuario usuario = null;
        for (Usuario usuario1 : getListaUsuarios()) {
            if (usuario1.getIdUsuario().equalsIgnoreCase(idUsuario)) {
                usuario = usuario1;
                break;
            }
        }
        return usuario;
    }



    public boolean actualizarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuarioActual, String idUsuario, String direccion) {
        Usuario usuarioExistente = obtenerUsuario(idUsuarioActual);

        if (usuarioExistente != null) {


            Usuario usuarioActualizado = Usuario.builder()
                    .nombre(nombre)
                    .apellido(apellido)
                    .correo(correo)
                    .telefono(telefono)
                    .idUsuario(idUsuario)
                    .direccion(direccion)
                    .saldoDisponible(usuarioExistente.getSaldoDisponible())
                    .build();


            listaUsuarios.remove(usuarioExistente);
            listaUsuarios.add(usuarioActualizado);

            return true;
        }

        return false;
    }

    public boolean eliminarUsuario(Usuario usuario) {

        Usuario usuario1 = obtenerUsuario(usuario.getIdUsuario());
        if(usuario1 != null){
            getListaUsuarios().remove(usuario1);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if(cuenta == null){
            cuenta = new Cuenta();
            cuenta.setIdCuenta(idCuenta);
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            getListaCuentas().add(cuenta);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean actualizarCuenta(String idCuenta, String idCuentaActual, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        Cuenta cuenta = obtenerCuenta(idCuentaActual);
        if(cuenta != null){
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setNumeroCuenta(numeroCuenta);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean eliminarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if(cuenta != null){
            getListaCuentas().remove(cuenta);
            return true;
        }else{
            return false;
        }
    }

    private Cuenta obtenerCuenta(String idCuenta) {
        Cuenta cuentaEncontrada = null;
        for (Cuenta cuenta: getListaCuentas()) {
            if(cuenta.getIdCuenta().equalsIgnoreCase(idCuenta)){
                cuentaEncontrada = cuenta;
                break;
            }
        }

        return cuentaEncontrada;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public List<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(List<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public List<Transaccion> getListaTransaccioness() {
        return listaTransaccioness;
    }

    public void setListaTransaccioness(List<Transaccion> listaTransaccioness) {
        this.listaTransaccioness = listaTransaccioness;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public Administrador autorizarLoginAdministrador(String idAdministrador, String pass) {
        Administrador administradorEncontrado = null;
        for (Administrador administrador: getListaAdministradores()) {
            if(administrador.getIdAdministrador().equalsIgnoreCase(idAdministrador) && administrador.getContrasenaAdm().equalsIgnoreCase(pass)){
                administradorEncontrado = administrador;
                break;
            }
        }

        return administradorEncontrado;
    }

    @Override
    public List<Transaccion> obtenerTransacciones() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obteneTransacciones'");
    }
}
