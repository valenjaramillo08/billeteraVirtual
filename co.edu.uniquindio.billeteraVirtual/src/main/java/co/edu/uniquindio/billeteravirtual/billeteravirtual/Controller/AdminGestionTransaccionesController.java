package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.Exportador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ReporteService;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.EstrategiaReporteGastos;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminGestionTransaccionesController {

    ModelFactory modelFactory;
    private final ReporteService reporteService = new ReporteService();

    public AdminGestionTransaccionesController(){
        modelFactory=ModelFactory.getInstancia();

    }

    public List<Usuario> obtenerUsuarios() {
        return modelFactory.getListaUsuarios(); // O el método correspondiente
    }

    public void crearTransaccion(Usuario usuario, String tipoStr, double monto, String descripcion) {
        TipoTransaccion tipo = TipoTransaccion.valueOf(tipoStr);

        // Obtener cuenta origen (asumimos que hay una primera cuenta)
        Cuenta cuentaOrigen = usuario.getListaCuentas().isEmpty() ? null : usuario.getListaCuentas().get(0);

        Transaccion transaccion = new Transaccion();
        transaccion.setIdTransaccion("TX-" + System.currentTimeMillis());
        transaccion.setCuentaOrigen(cuentaOrigen);
        transaccion.setFechaTransaccion(LocalDate.now());
        transaccion.setMonto(monto);
        transaccion.setDescripcion(descripcion);
        transaccion.setTipoTransaccion(tipo);
        transaccion.setCuentaDestino(null); // puedes ajustar esto según el tipo

        modelFactory.agregarTransaccion(transaccion);
    }

    public List<Transaccion> obtenerTransacciones(Usuario usuario) {
        return usuario.getListaTransacciones(); // asumimos que Usuario tiene este método
    }

    public void exportarReporte(Usuario usuario, Exportador exportador, String nombreArchivo) {
        modelFactory.getReporteService().generarReporteGastosUsuario(usuario, nombreArchivo, exportador);
    }

    public boolean crearTransaccion(Cuenta cuentaOrigen,Cuenta cuentaDestino,double monto,String descripcion,TipoTransaccion tipoTransaccion) {
        return modelFactory.getAdministrador().crearTransaccion(cuentaOrigen, cuentaDestino, monto, descripcion, tipoTransaccion);
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return modelFactory.getListaUsuarios().stream()
                .flatMap(usuario -> usuario.getListaCuentas().stream())
                .toList();
    }

    public double obtenerSaldoCuenta(Cuenta cuenta) {
        Presupuesto presupuesto = cuenta.getPresupuesto();
        return (presupuesto != null) ? presupuesto.getMontoPresupuesto() : 0;
    }


    public void exportarTransacciones(List<Transaccion> transacciones, String nombreArchivo, Exportador exportador) {
        List<String[]> filas = new ArrayList<>();
        filas.add(new String[]{"Fecha", "Monto", "Tipo", "Cuenta Origen", "Cuenta Destino", "Descripción"});

        for (Transaccion t : transacciones) {
            filas.add(new String[]{
                    t.getFechaTransaccion().toString(),
                    String.format("%.2f", t.getMonto()),
                    t.getTipoTransaccion().toString(),
                    t.getCuentaOrigen() != null ? t.getCuentaOrigen().getNumeroCuenta() : "N/A",
                    t.getCuentaDestino() != null ? t.getCuentaDestino().getNumeroCuenta() : "N/A",
                    t.getDescripcion()
            });
        }

        exportador.exportar("Reporte de Transacciones", filas, nombreArchivo);
    }



}
