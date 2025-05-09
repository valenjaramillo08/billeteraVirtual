package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class ExportadorPDF implements Exportador {
    @Override
    public void exportar(String titulo, List<String[]> contenido, String nombreArchivo) {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo + ".pdf"));
            documento.open();

            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph parrafoTitulo = new Paragraph(titulo, fontTitulo);
            parrafoTitulo.setAlignment(Element.ALIGN_CENTER);
            parrafoTitulo.setSpacingAfter(20);
            documento.add(parrafoTitulo);

            // Tabla
            PdfPTable tabla = new PdfPTable(contenido.get(0).length);
            tabla.setWidthPercentage(100);

            // Encabezados
            for (String encabezado : contenido.get(0)) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }

            // Datos
            for (int i = 1; i < contenido.size(); i++) {
                for (String dato : contenido.get(i)) {
                    PdfPCell celdaDato = new PdfPCell(new Phrase(dato));
                    celdaDato.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(celdaDato);
                }
            }

            documento.add(tabla);
            documento.close();

            System.out.println("PDF generado correctamente: " + nombreArchivo + ".pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
