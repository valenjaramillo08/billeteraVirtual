package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Exportador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ReporteService;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;

public class ReporteCuentaCommand implements Command {

    private final Cuenta cuenta;
    private final String nombreArchivo;
    private final Exportador exportador;
    private final ReporteService reporteService;

    public ReporteCuentaCommand(Cuenta cuenta, String nombreArchivo, Exportador exportador) {
        this.cuenta = cuenta;
        this.nombreArchivo = nombreArchivo;
        this.exportador = exportador;
        this.reporteService = new ReporteService();
    }

    @Override
    public void execute() {
        reporteService.generarReportePorCuenta(cuenta, nombreArchivo, exportador);
    }
}
