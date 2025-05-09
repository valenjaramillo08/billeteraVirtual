package co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ReporteService;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade.TransaccionFacade;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Mappers.AdministradorMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory implements IModelFactoryServices, ICuentaServices, IUsuarioServices, IAdministradorServices, ITransaccionServices {
    private static ModelFactory modelFactory;
    private Billetera billetera;
    private IAdministradorMapping mapper;
    private Administrador administrador;
    private TransaccionFacade transaccionFacade;
    private ReporteService reporteService;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        mapper = new AdministradorMapping();
        billetera = DataUtil.inicializarDatos();
        administrador = billetera.getListaAdministradores().getFirst();
        reporteService = new ReporteService();
    }

    public ReporteService getReporteService() {
        return reporteService;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(administrador.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
        return administrador.crearUsuario(usuario);
    }

    @Override
    public boolean actualizarUsuario(String idUsuario,UsuarioDto usuarioDto) {
        Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
        return administrador.actualizarUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getTelefono(),idUsuario,usuario.getIdUsuario(), usuario.getDireccion());
    }

    @Override
    public boolean eliminarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
        return administrador.eliminarUsuario(usuario.getIdUsuario());
    }

    @Override
    public boolean agregarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        return administrador.agregarCuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta, usuarioAsociado, administradorAsociado);
    }

    @Override
    public boolean actualizarCuenta(String idCuenta, String idCuentaActual, String nombreBanco, String numeroCuenta,
                                    TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        return administrador.actualizarCuenta(idCuenta, idCuentaActual, nombreBanco, numeroCuenta, tipoCuenta, usuarioAsociado, administradorAsociado);
    }

    @Override
    public boolean eliminarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        return administrador.eliminarCuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta);
    }

    public List<Cuenta> obtenerCuentas() {
        return administrador.getListaCuentas();
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        return administrador.getListaUsuarios();
    }

    @Override
    public boolean agregarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuario, String direccion) {
        return false;
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {
        return administrador.obtenerUsuario(idUsuario);
    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {
        return false;
    }

    @Override
    public boolean actualizarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuarioActual, String idUsuario, String direccion) {
        return false;
    }

    @Override
    public boolean autorizarLoginUsuario(String idUsuario, String pass) {
        return administrador.autorizarLoginUsuario(idUsuario, pass);
    }

    @Override
    public Administrador autorizarLoginAdministrador(String idAdministrador, String pass) {
        return billetera.autorizarLoginAdministrador(idAdministrador, pass);
    }

    @Override
    public boolean actualizarUsuarioPerfil(String nombre, String apellido, String correo, String idUsuarioActual) {
        return administrador.actualizarUsuarioPerfil(nombre, apellido, correo, idUsuarioActual);
    }

    @Override
    public boolean agregarUsuarioRegistro(String nombre, String correo, String idUsuario, String contrasena) {
        return administrador.agregarUsuarioRegistro(nombre, correo, idUsuario, contrasena);
    }


    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        return administrador.agregarTransaccion(transaccion);
    }


}