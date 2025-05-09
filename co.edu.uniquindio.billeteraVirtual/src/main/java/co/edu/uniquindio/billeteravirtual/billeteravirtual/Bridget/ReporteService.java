package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstrategiaEstadistica;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstrategiaGastosPorCategoria;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstrategiaSaldoPromedio;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstrategiaUsuariosConMasTransacciones;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.List;

public class ReporteService {
    public void generarReporteUsuariosActivos(List<Usuario> usuarios, String nombreArchivo, Exportador exportador) {
        EstrategiaEstadistica estrategia = new EstrategiaUsuariosConMasTransacciones();
        Reporte reporte = new ReporteEstadisticaGenerica(estrategia, exportador, usuarios);
        reporte.generarYExportar(nombreArchivo);
    }

    public void generarReporteSaldoPromedio(List<Usuario> usuarios, String nombreArchivo, Exportador exportador) {
        EstrategiaEstadistica estrategia = new EstrategiaSaldoPromedio();
        Reporte reporte = new ReporteEstadisticaGenerica(estrategia, exportador, usuarios);
        reporte.generarYExportar(nombreArchivo);
    }

    public void generarReporteGastosMasComunes(List<Usuario> usuarios, String nombreArchivo, Exportador exportador) {
        EstrategiaEstadistica estrategia = new EstrategiaGastosPorCategoria();
        Reporte reporte = new ReporteEstadisticaGenerica(estrategia, exportador, usuarios);
        reporte.generarYExportar(nombreArchivo);
    }
}
