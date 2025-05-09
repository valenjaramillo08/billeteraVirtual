package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

public abstract class Reporte {
    protected Exportador exportador;
    public Reporte(Exportador exportador) {
        this.exportador = exportador;
    }

    public abstract void generarYExportar(String nombreArchivo);
}
