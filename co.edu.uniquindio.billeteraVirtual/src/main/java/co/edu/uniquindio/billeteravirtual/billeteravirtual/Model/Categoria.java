package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Categoria {
    public String idCategoria;
    public NombreCategoria nombreCategoria;
    public double saldo;

    public Categoria(){}
    public Categoria(String idCategoria, NombreCategoria nombreCategoria, double saldo) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.saldo=saldo;

    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo=saldo;
    }

    public NombreCategoria getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(NombreCategoria nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }



}
