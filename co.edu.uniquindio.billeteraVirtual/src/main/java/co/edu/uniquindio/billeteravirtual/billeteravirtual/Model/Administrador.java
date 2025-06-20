package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Exportador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorCSV;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorPDF;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Reporte;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.DatosTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.FabricaTransacciones;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Builder.UsuarioBuilder;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ITransaccionServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IUsuarioServices;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.ICuentaServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Administrador extends Persona implements IUsuarioServices, ICuentaServices, ITransaccionServices {
    public String idAdministrador;
    public String contrasenaAdm;
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Transaccion> listaTransacciones = new ArrayList<>();
    List<Cuenta> listaCuentas = new ArrayList<>();


    public Administrador(String nombre,
                         String apellido,
                         String correo,
                         String idAdministrador,
                         String contrasenaAdm) {
        super(nombre, apellido, correo);
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
                                String contrasenaUsuario) {
        Usuario usuarioEncontrado = obtenerUsuario(idUsuario);
        if (usuarioEncontrado == null) {
            Usuario usuario = getBuildUsuario(nombre, apellido, correo, telefono, idUsuario, direccion, saldoDisponible, contrasenaUsuario);
            getListaUsuarios().add(usuario);
            return true;
        } else {
            return false;
        }
    }

    public boolean crearUsuario(Usuario nuevoUsuario) {
        Usuario usuarioEncontrado = obtenerUsuario(nuevoUsuario.getIdUsuario());
        if (usuarioEncontrado == null) {
            getListaUsuarios().add(nuevoUsuario);
            return true;
        } else {
            return false;
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
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            getListaUsuarios().add(usuario);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : getListaUsuarios()) {
            if (usuario.getIdUsuario().equalsIgnoreCase(idUsuario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }
        System.out.println("Usuario encontrado: " + usuarioEncontrado);
        return usuarioEncontrado;

    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {

        Usuario usuario = obtenerUsuario(idUsuario);
        if (usuario != null) {
            getListaUsuarios().remove(usuario);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean actualizarUsuario(String nombre, String apellido, String correo, String telefono, String idUsuarioActual, String idUsuario, String direccion) {
        Usuario usuario = obtenerUsuario(idUsuarioActual);
        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean autorizarLoginUsuario(String idUsuario, String contrasena) {
        boolean usuarioEncontrado = false;
        for (Usuario usuario : getListaUsuarios()) {
            if (usuario.getIdUsuario().equalsIgnoreCase(idUsuario) && usuario.getContrasenaUsuario().equalsIgnoreCase(contrasena)) {
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
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if (cuenta == null) {
            cuenta = new Cuenta();
            cuenta.setIdCuenta(idCuenta);
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setUsuarioAsociado(usuarioAsociado);
            cuenta.setAdministradorAsociado(administradorAsociado);
            getListaCuentas().add(cuenta);

            usuarioAsociado.getListaCuentas().add(cuenta); 



            return true;
        } else {
            return false;
        }
    }
    public List<Transaccion> listarTransaccionesUsuario(Usuario usuario){
        List<Transaccion> transacciones = new ArrayList<>();

        for (Transaccion transaccion : listaTransacciones) {
            if ((transaccion.getCuentaOrigen() != null &&
                    transaccion.getCuentaOrigen().getUsuarioAsociado().getIdUsuario().equals(usuario.getIdUsuario()))
                    ||
                    (transaccion.getCuentaDestino() != null &&
                            transaccion.getCuentaDestino().getUsuarioAsociado().getIdUsuario().equals(usuario.getIdUsuario()))) {
                transacciones.add(transaccion);
            }
        }
        return transacciones;
    }


    public Cuenta obtenerCuenta(String idCuenta) {
        Cuenta cuentaEncontrada = null;
        for (Cuenta cuenta : getListaCuentas()) {
            if (cuenta.getIdCuenta().equalsIgnoreCase(idCuenta)) {
                cuentaEncontrada = cuenta;
                break;
            }
        }

        return cuentaEncontrada;
    }

    public List<Cuenta> listarCuentasUsuarios(Usuario usuario){
        List<Cuenta> cuentasAux = new ArrayList<>();
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getUsuarioAsociado().getIdUsuario().equals(usuario.getIdUsuario())) {
                cuentasAux.add(cuenta);
            }
        }
        return cuentasAux;
    }

    public boolean crearTransaccion(
            Cuenta cuentaOrigen, 
            Cuenta cuentaDestino, 
            double monto,
            String descripcion,
            TipoTransaccion tipoTransaccion) {
        String idTransaccion = UUID.randomUUID().toString();
        LocalDate fecha = LocalDate.now();

        
        if (tipoTransaccion == TipoTransaccion.RETIRO && cuentaOrigen == null) {
            System.out.println("Error: Cuenta origen requerida para retiro.");
            return false;
        }

        if (tipoTransaccion == TipoTransaccion.DEPOSITO && cuentaDestino == null) {
            System.out.println("Error: Cuenta destino requerida para depósito.");
            return false;
        }

        if (tipoTransaccion == TipoTransaccion.TRANSFERENCIA && (cuentaOrigen == null || cuentaDestino == null)) {
            System.out.println("Error: Ambas cuentas requeridas para transferencia.");
            return false;
        }

        DatosTransaccion datos = new DatosTransaccion(
                idTransaccion,
                cuentaOrigen,
                fecha,
                monto,
                descripcion,
                cuentaDestino,
                tipoTransaccion,
                null);

        return registrarTransaccion(datos);
    }

    @Override
    public boolean actualizarCuenta(String idCuenta, String idCuentaActual, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta, Usuario usuarioAsociado, Administrador administradorAsociado) {
        Cuenta cuenta = obtenerCuenta(idCuentaActual);
        if (cuenta != null) {
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setNumeroCuenta(numeroCuenta);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarCuenta(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if (cuenta != null) {
            getListaCuentas().remove(cuenta);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean actualizarUsuarioPerfil(String nombre, String apellido, String correo, String idUsuarioActual) {
        Usuario usuario = obtenerUsuario(idUsuarioActual);
        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean agregarUsuarioRegistro(String nombre, String correo, String idUsuario, String contrasena) {
        if (obtenerUsuario(idUsuario) != null) {
            return false; 
        }

        Usuario nuevoUsuario = new UsuarioBuilder()
                .nombre(nombre)
                .correo(correo)
                .idUsuario(idUsuario)
                .contrasenaUsuario(contrasena)
                .build();

        nuevoUsuario.administradorAsociado = this;

        listaUsuarios.add(nuevoUsuario);
        return true;
    }

    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        if (transaccion != null) {
            listaTransacciones.add(transaccion);
            return true; 
        } else {
            return false; 
        }
    }
    public boolean registrarTransaccion(DatosTransaccion datos) {
        if (datos == null) return false;

        
        Transaccion transaccion = FabricaTransacciones.crear(datos);


        agregarTransaccion(transaccion);


        Cuenta cuentaOrigen = transaccion.getCuentaOrigen();
        Cuenta cuentaDestino = transaccion.getCuentaDestino();

        Presupuesto presupuestoOrigen = (cuentaOrigen != null) ? cuentaOrigen.getPresupuesto() : null;
        Presupuesto presupuestoDestino = (cuentaDestino != null) ? cuentaDestino.getPresupuesto() : null;


        switch (transaccion.getTipoTransaccion()) {
            case DEPOSITO -> {
                if (presupuestoDestino != null) {
                    presupuestoDestino.setMontoPresupuesto(
                            presupuestoDestino.getMontoPresupuesto() + transaccion.getMonto()
                    );
                    presupuestoDestino.notificarObservers();
                }
            }

            case RETIRO -> {
                if (presupuestoOrigen != null) {
                    presupuestoOrigen.setMontoPresupuesto(
                            presupuestoOrigen.getMontoPresupuesto() - transaccion.getMonto()
                    );
                    presupuestoOrigen.setMontoPresupuestoGastado(
                            presupuestoOrigen.getMontoPresupuestoGastado() + transaccion.getMonto()
                    );
                    presupuestoOrigen.notificarObservers();
                }
            }

            case TRANSFERENCIA -> {
                if (presupuestoOrigen != null) {
                    presupuestoOrigen.setMontoPresupuesto(
                            presupuestoOrigen.getMontoPresupuesto() - transaccion.getMonto()
                    );
                    presupuestoOrigen.setMontoPresupuestoGastado(
                            presupuestoOrigen.getMontoPresupuestoGastado() + transaccion.getMonto()
                    );
                    presupuestoOrigen.notificarObservers();
                }
                if (presupuestoDestino != null) {
                    presupuestoDestino.setMontoPresupuesto(
                            presupuestoDestino.getMontoPresupuesto() + transaccion.getMonto()
                    );
                    presupuestoDestino.notificarObservers();
                }
            }
        }


        if (cuentaOrigen != null) {
            cuentaOrigen.getListaTransacciones().add(transaccion);
        }
        if (cuentaDestino != null) {
            cuentaDestino.getListaTransacciones().add(transaccion);
        }

        return true;

    }

    public double saldoCuenta(Cuenta cuentaParam) {
        double saldo = 0;
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getIdCuenta().equals(cuentaParam.getIdCuenta())) {
                saldo = cuenta.getPresupuesto().getMontoPresupuesto();
            }
        }
        return saldo;
    }

    public void generarReporte(String formato){
        Exportador exportador;
        String extension;

        if("CSV".equalsIgnoreCase(formato)){
            exportador = new ExportadorCSV();
            extension = "csv";
        }else{
            exportador = new ExportadorPDF();
            extension = "pdf";
        }

        /* Reporte reporte = new ReporteAdministrador(this,exportador);*/
        /*reporte.generarYExportar("reporte_admin_" + getIdAdministrador() + "." + extension);*/
    }


    

}



