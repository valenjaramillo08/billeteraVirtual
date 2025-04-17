package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Builder.UsuarioBuilder;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IObservadorSaldo;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Service.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements IVisitable {
    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    public String telefono;
    public String idUsuario;
    public String direccion;
    public double saldoDisponible;
    public List<Cuenta> listaCuentas = new ArrayList<>();
    public List<Transaccion> listaTransacciones = new ArrayList<>();
    public List<Presupuesto> listaPresupuestos = new ArrayList<>();
    public List<Categoria> listaCategorias = new ArrayList<>();
    public Administrador administradorAsociado;

    private List<IObservadorSaldo> observadores = new ArrayList<>();

    public Usuario(){}

    public  Usuario(String nombre,
                    String apellido,
                    String correo,
                    String telefono,
                    String idUsuario,
                    String direccion,
                    double saldoDisponible
    ){
        super(nombre,apellido,correo);
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.saldoDisponible = saldoDisponible;

    }
    public static UsuarioBuilder builder(){
        return new UsuarioBuilder();
    }

    public void agregarObservador(IObservadorSaldo observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(IObservadorSaldo observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (IObservadorSaldo observador : observadores) {
            observador.actualizar(saldoDisponible);
        }
    }

    public void setSaldoDisponible(double nuevoSaldo) {
        this.saldoDisponible = nuevoSaldo;
        notificarObservadores();
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

    @Override
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
    }
}
