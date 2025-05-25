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

        ObservableList<PieChart.Data> lista = FXCollections.observableArrayList();
        for (EstadisticaCategoria dato : datos) {
            String etiqueta = tipoEstadistica.equals("Saldo promedio de usuarios")
                    ? String.format("%.2f", dato.getValor())
                    : dato.getNombre();

            lista.add(new PieChart.Data(etiqueta, dato.getValor()));
        }

        return lista;
    }


    public String obtenerTituloEstadistica(String tipoEstadistica) {
        EstrategiaEstadistica estrategia = estrategias.get(tipoEstadistica);
        return (estrategia != null) ? estrategia.getTitulo() : "";
    }

}


