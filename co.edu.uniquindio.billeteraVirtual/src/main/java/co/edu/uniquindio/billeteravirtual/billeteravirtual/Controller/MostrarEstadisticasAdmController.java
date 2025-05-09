package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.List;

public class MostrarEstadisticasAdmController {
    private final ModelFactory modelFactory = ModelFactory.getInstancia();

    public List<String> obtenerOpcionesEstadisticas() {
        return List.of(
                "Usuarios con más transacciones",
                "Saldo promedio de usuarios",
                "Gastos más comunes"
        );
    }

    public ObservableList<PieChart.Data> obtenerDatosParaEstadistica(String tipoEstadistica) {
        EstrategiaEstadistica estrategia;

        switch (tipoEstadistica) {
            case "Usuarios con más transacciones" -> estrategia = new EstrategiaUsuariosConMasTransacciones();
            case "Saldo promedio de usuarios" -> estrategia = new EstrategiaSaldoPromedio();
            case "Gastos más comunes" -> estrategia = new EstrategiaGastosPorCategoria();
            default -> {
                return FXCollections.observableArrayList();
            }
        }

        List<Usuario> usuarios = modelFactory.getListaUsuarios();
        List<EstadisticaCategoria> datos = estrategia.calcular(usuarios);

        List<PieChart.Data> dataChart = new ArrayList<>();
        for (EstadisticaCategoria dato : datos) {
            dataChart.add(new PieChart.Data(dato.getNombre(), dato.getValor()));
        }
        return FXCollections.observableArrayList(dataChart);
    }

    public String obtenerTituloEstadistica(String tipoEstadistica) {
        return switch (tipoEstadistica) {
            case "Usuarios con más transacciones" -> new EstrategiaUsuariosConMasTransacciones().getTitulo();
            case "Saldo promedio de usuarios" -> new EstrategiaSaldoPromedio().getTitulo();
            case "Gastos más comunes" -> new EstrategiaGastosPorCategoria().getTitulo();
            default -> "";
        };
    }
}
