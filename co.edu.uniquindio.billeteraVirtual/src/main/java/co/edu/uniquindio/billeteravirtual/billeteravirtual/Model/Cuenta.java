package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Cuenta {
    public String idCuenta;
    public String nombreBanco;
    public String numeroCuenta;
    public TipoCuenta tipoCuenta;
    public Usuario usuarioAsociado;
    public co.edu.uniquindio.proyectobilletera.proyectobilletera.model.Administrador administradorAsociado;

    public Cuenta(){}

    public Cuenta(String idCuenta,
                  String nombreBanco,
                  String numeroCuenta,
                  TipoCuenta tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public co.edu.uniquindio.proyectobilletera.proyectobilletera.model.Administrador getAdministradorAsociado() {
        return administradorAsociado;
    }

    public void setAdministradorAsociado(co.edu.uniquindio.proyectobilletera.proyectobilletera.model.Administrador administradorAsociado) {
        this.administradorAsociado = administradorAsociado;
    }



}
