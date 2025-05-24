package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.MostrarEstadisticasAdmController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

public class MostrarEstadisticasAdmViewController {

    private MostrarEstadisticasAdmController mostrarEstadisticasAdmController;

    @FXML
    private ComboBox<String> comboEstadisticas;

    @FXML
    private PieChart chartEstadistica;

    @FXML
    public void initialize() {
        mostrarEstadisticasAdmController = new MostrarEstadisticasAdmController();
        comboEstadisticas.setItems(FXCollections.observableArrayList(
                mostrarEstadisticasAdmController.obtenerOpcionesEstadisticas()));
        comboEstadisticas.setOnAction(e -> mostrarEstadistica());
    }

    private void mostrarEstadistica() {
        String seleccion = comboEstadisticas.getValue();
        if (seleccion == null || seleccion.isEmpty()) return;

        ObservableList<PieChart.Data> datos = mostrarEstadisticasAdmController.obtenerDatosParaEstadistica(seleccion);
        chartEstadistica.setTitle(mostrarEstadisticasAdmController.obtenerTituloEstadistica(seleccion));
        chartEstadistica.setData(datos);
    }
}
