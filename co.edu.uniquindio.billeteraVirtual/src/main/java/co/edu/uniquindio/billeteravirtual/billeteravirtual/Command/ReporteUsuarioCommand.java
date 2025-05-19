package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Exportador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ReporteService;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.function.BiConsumer;

public class ReporteUsuarioCommand implements Command {

    private final Usuario usuario;
    private final String nombreArchivo;
    private final Exportador exportador;
    private final ReporteService reporteService;
    private final BiConsumer<ReporteService, Usuario> estrategiaMetodo;

    public ReporteUsuarioCommand(
            Usuario usuario,
            String nombreArchivo,
            Exportador exportador,
            BiConsumer<ReporteService, Usuario> estrategiaMetodo
    ) {
        this.usuario = usuario;
        this.nombreArchivo = nombreArchivo;
        this.exportador = exportador;
        this.reporteService = new ReporteService();
        this.estrategiaMetodo = estrategiaMetodo;
    }

    @Override
    public void execute() {
        estrategiaMetodo.accept(reporteService, usuario);
    }
}
