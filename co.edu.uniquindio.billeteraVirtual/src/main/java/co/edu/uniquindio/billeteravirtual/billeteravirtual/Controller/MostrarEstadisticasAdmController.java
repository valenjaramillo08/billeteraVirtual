package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostrarEstadisticasAdmController {
    private final ModelFactory modelFactory = ModelFactory.getInstancia();

    // Mapa que asocia el nombre visible en el ComboBox con una estrategia
    private final Map<String, EstrategiaEstadistica> estrategias = new HashMap<>();

    public MostrarEstadisticasAdmController() {
        estrategias.put("Usuarios con más transacciones", new EstrategiaUsuariosConMasTransacciones());
        estrategias.put("Saldo promedio de usuarios", new EstrategiaSaldoPromedio());
        estrategias.put("Gastos más comunes", new EstrategiaGastosPorCategoria());
    }

    public List<String> obtenerOpcionesEstadisticas() {
        return new ArrayList<>(estrategias.keySet());
    }

    public ObservableList<PieChart.Data> obtenerDatosParaEstadistica(String tipoEstadistica) {
        EstrategiaEstadistica estrategia = estrategias.get(tipoEstadistica);

        if (estrategia == null) {
            return FXCollections.observableArrayList();
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
        EstrategiaEstadistica estrategia = estrategias.get(tipoEstadistica);
        return (estrategia != null) ? estrategia.getTitulo() : "";
    }

}


