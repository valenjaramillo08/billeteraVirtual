package co.edu.uniquindio.billeteravirtual.billeteravirtual.Model;

public class Categoria {
    public NombreCategoria nombreCategoria;
    public double precio;

    public Categoria(NombreCategoria nombreCategoria, double precio) {
        this.nombreCategoria = nombreCategoria;
        this.precio=precio;

    }

    public NombreCategoria getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(NombreCategoria nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
