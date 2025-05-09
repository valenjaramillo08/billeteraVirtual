package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.MostrarEstadisticasAdmController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MostrarEstadisticasAdmViewController {
    MostrarEstadisticasAdmController mostrarEstadisticasAdmController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboEstadisticas;

    @FXML
    private PieChart chartEstadistica;

    @FXML
    void onMostrarEstadistica(ActionEvent event) {
        String seleccion = comboEstadisticas.getValue();
        if (seleccion == null) return;

        ObservableList<PieChart.Data> datos = mostrarEstadisticasAdmController.obtenerDatosParaEstadistica(seleccion);

        chartEstadistica.setTitle(mostrarEstadisticasAdmController.obtenerTituloEstadistica(seleccion));
        chartEstadistica.setData(datos);

    }

    @FXML
    void initialize() {
        mostrarEstadisticasAdmController = new MostrarEstadisticasAdmController();
        comboEstadisticas.setItems(FXCollections.observableArrayList(mostrarEstadisticasAdmController.obtenerOpcionesEstadisticas()));
    }
}
