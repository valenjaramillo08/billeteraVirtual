package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Categoria {
    public NombreCategoria nombreCategoria;
    public double saldo;

    public Categoria(NombreCategoria nombreCategoria, double saldo) {
        this.nombreCategoria = nombreCategoria;
        this.saldo=saldo;

    }

    public NombreCategoria getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(NombreCategoria nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public double getSaldoo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo=saldo;
    }
}
