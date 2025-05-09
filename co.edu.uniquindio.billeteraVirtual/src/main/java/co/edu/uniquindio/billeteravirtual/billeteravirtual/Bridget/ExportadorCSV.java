package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstadisticaCategoria;

import java.io.PrintWriter;
import java.util.List;

public class ExportadorCSV implements Exportador{

    @Override
    public void exportar(String titulo, List<String[]> contenido, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(nombreArchivo + ".csv")) {
            writer.println("Título: " + titulo);
            for (String[] fila : contenido) {
                writer.println(String.join(",", fila));
            }
            System.out.println("Reporte CSV exportado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
