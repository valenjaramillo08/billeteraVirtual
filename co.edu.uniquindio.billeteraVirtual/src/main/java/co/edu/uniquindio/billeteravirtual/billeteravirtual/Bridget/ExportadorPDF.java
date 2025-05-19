package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class ExportadorPDF implements Exportador {
    @Override
    public void exportar(String titulo, List<String[]> contenido, String nombreArchivo) {
        Document documento = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo + ".pdf"));
            documento.open();

            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph parrafoTitulo = new Paragraph(titulo, fontTitulo);
            parrafoTitulo.setAlignment(Element.ALIGN_LEFT);
            documento.add(parrafoTitulo);

            // Fecha de reporte
            Paragraph fecha = new Paragraph("Fecha Reporte: " + LocalDate.now(), FontFactory.getFont(FontFactory.HELVETICA, 11));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            fecha.setSpacingAfter(10);
            documento.add(fecha);

            // Línea separadora
            LineSeparator separator = new LineSeparator();
            documento.add(separator);

            // Verificar si hay contenido
            if (contenido == null || contenido.isEmpty()) {
                Paragraph sinDatos = new Paragraph("No hay datos para mostrar.", FontFactory.getFont(FontFactory.HELVETICA, 12));
                sinDatos.setAlignment(Element.ALIGN_CENTER);
                documento.add(sinDatos);
                documento.close();
                return;
            }

            // Información usuario (hasta que haya línea vacía)
            Font fontInfo = FontFactory.getFont(FontFactory.HELVETICA, 11);
            PdfPTable tablaUsuario = new PdfPTable(2);
            tablaUsuario.setWidthPercentage(100);
            tablaUsuario.setSpacingBefore(10);
            int i = 0;
            for (; i < contenido.size(); i++) {
                String[] fila = contenido.get(i);
                if (fila.length == 1 && fila[0].isBlank()) {
                    i++; // Saltar línea vacía
                    break;
                }
                if (fila.length == 2) {
                    PdfPCell c1 = new PdfPCell(new Phrase(fila[0], fontInfo));
                    PdfPCell c2 = new PdfPCell(new Phrase(fila[1], fontInfo));
                    c1.setBorder(Rectangle.NO_BORDER);
                    c2.setBorder(Rectangle.NO_BORDER);
                    tablaUsuario.addCell(c1);
                    tablaUsuario.addCell(c2);
                }
            }
            documento.add(tablaUsuario);

            // Tabla de datos
            PdfPTable tablaDatos = new PdfPTable(contenido.get(i).length);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.setSpacingBefore(15);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fontDato = FontFactory.getFont(FontFactory.HELVETICA, 11);

            // Encabezado
            for (String header : contenido.get(i)) {
                PdfPCell celda = new PdfPCell(new Phrase(header, fontHeader));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(6);
                tablaDatos.addCell(celda);
            }
            i++;

            // Resto de datos
            for (; i < contenido.size(); i++) {
                for (String dato : contenido.get(i)) {
                    PdfPCell celda = new PdfPCell(new Phrase(dato, fontDato));
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda.setPadding(5);
                    tablaDatos.addCell(celda);
                }
            }

            documento.add(tablaDatos);
            documento.close();
            System.out.println("PDF generado correctamente: " + nombreArchivo + ".pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
