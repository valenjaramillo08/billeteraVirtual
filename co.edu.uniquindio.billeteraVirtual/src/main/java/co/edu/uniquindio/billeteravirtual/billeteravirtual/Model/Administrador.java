package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IUsuarioServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ICuentaServices;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Persona implements IUsuarioServices, ICuentaServices{
    public String idAdministrador;
    public String contrasenaAdm;
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Transaccion> listaTransacciones = new ArrayList<>();
    List<Cuenta> listaCuentas = new ArrayList<>();


    public Administrador (String nombre,
                          String apellido,
                          String correo,
                          String idAdministrador,
                          String contrasenaAdm) {
        super(nombre,apellido,correo);
        this.idAdministrador = idAdministrador;
        this.contrasenaAdm = contrasenaAdm;


    }

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

    @Override
    public boolean agregarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuario, String direccion) {
        Usuario usuario = obtenerUsuario(idUsuario);
        if(usuario == null){
            usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            getListaUsuarios().add(usuario);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {

        Usuario usuarioEncontrado = null;
        for (Usuario usuario: getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase(idUsuario)){
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;

    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {

        Usuario usuario = obtenerUsuario(idUsuario);
        if(usuario != null){
            getListaUsuarios().remove(usuario);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean actualizarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuarioActual, String idUsuario, String direccion) {
        Usuario usuario = obtenerUsuario(idUsuarioActual);
        if(usuario != null){
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean autorizarLoginUsuario(String idUsuario, String contrasena){
        boolean usuarioEncontrado = false;
        for (Usuario usuario: getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase(idUsuario) && usuario.getContrasenaUsuario().equalsIgnoreCase(contrasena)){
                usuarioEncontrado = true;
                break;
            }
        }

        return usuarioEncontrado;
    }


    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(List<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getContrasenaAdm() {
        return contrasenaAdm;
    }

    @Override
    public boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        return false;
    }

    @Override
    public boolean actualizarCuenta(String idCuenta, String idCuentaActual, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        return false;
    }

    @Override
    public boolean eliminarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        return false;
    }
}

