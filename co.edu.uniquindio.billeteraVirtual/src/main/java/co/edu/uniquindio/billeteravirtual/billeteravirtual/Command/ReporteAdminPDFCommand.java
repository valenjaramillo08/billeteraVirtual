package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorPDF;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ReporteAdminPDFCommand implements Command {

    private final Usuario usuario;
    private final List<Transaccion> transacciones;
    private final String nombreArchivo;

    public ReporteAdminPDFCommand(Usuario usuario, List<Transaccion> transacciones, String nombreArchivo) {
        this.usuario = usuario;
        this.transacciones = transacciones;
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void execute() {
        List<String[]> contenido = generarContenidoDetallado();
        new ExportadorPDF().exportar("Reporte de Ingresos", contenido, nombreArchivo);
    }

    private List<String[]> generarContenidoDetallado() {
        List<String[]> contenido = new ArrayList<>();

        contenido.add(new String[]{"Información Usuario"});
        contenido.add(new String[]{"Nombre Completo:", usuario.getNombre() + " " + usuario.getApellido()});
        contenido.add(new String[]{"Cédula:", usuario.getIdUsuario()});
        contenido.add(new String[]{"Correo Electrónico:", usuario.getCorreo()});
        contenido.add(new String[]{"Número de Teléfono:", usuario.getTelefono()});
        contenido.add(new String[]{"Dirección:", usuario.getDireccion()});
        contenido.add(new String[]{""});

        contenido.add(new String[]{"Fecha", "Descripción", "Cuenta", "Monto"});

        double total = 0;
        for (Transaccion t : transacciones) {
            contenido.add(new String[]{
                    t.getFechaTransaccion().toString(),
                    t.getDescripcion(),
                    t.getCuentaOrigen().getNumeroCuenta(),
                    "$" + String.format("%,.2f", t.getMonto())
            });
            total += t.getMonto();
        }

        contenido.add(new String[]{"", "", "Total:", "$" + String.format("%,.2f", total)});
        return contenido;
    }
}
