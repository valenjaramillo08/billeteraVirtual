package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Exportador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorCSV;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorPDF;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Reporte;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Builder.UsuarioBuilder;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    public String telefono;
    public String idUsuario;
    public String direccion;
    public double saldoDisponible;
    public String contrasenaUsuario;
    public List<Cuenta> listaCuentas = new ArrayList<>();
    public List<Transaccion> listaTransacciones = new ArrayList<>();
    public List<Presupuesto> listaPresupuestos = new ArrayList<>();
    public List<Categoria> listaCategorias = new ArrayList<>();
    public Administrador administradorAsociado;



    public Usuario(){}

    public  Usuario(String nombre,
                    String apellido,
                    String correo,
                    String telefono,
                    String idUsuario,
                    String direccion,
                    double saldoDisponible,
                    String contrasenaUsuario
    ){
        super(nombre,apellido,correo);
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.saldoDisponible = saldoDisponible;
        this.contrasenaUsuario = contrasenaUsuario;

    }
    public static UsuarioBuilder builder(){
        return new UsuarioBuilder();
    }


    public String getTelefono() {
        return telefono;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public List<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void generarReporte(String formato){
        Exportador exportador;
        String extension;

        if("CSV".equalsIgnoreCase(formato)){
            exportador = new ExportadorCSV();
            extension = "csv";
        } else{
            exportador = new ExportadorPDF();
            extension = "pdf";
        }

        /*Reporte reporte = new ReporteUsuario(this, exportador);
        reporte.generarYExportar("reporte_usuario_" + idUsuario + "." + extension);*/
    }


    /*/@Override
    public String toString() {
        return "Usuario{" +
                "telefono='" + telefono + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", saldoDisponible=" + saldoDisponible +
                ", listaCuentas=" + listaCuentas +
                ", listaTransacciones=" + listaTransacciones +
                ", listaPresupuestos=" + listaPresupuestos +
                ", listaCategorias=" + listaCategorias +
                ", administradorAsociado=" + administradorAsociado +
                '}';
    }*/

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + idUsuario + ")";
    }

}
