package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionarCuentaViewController {

    private Usuario usuarioActual;
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btDetallesCuenta;

    @FXML
    private TableView<Cuenta> tableCuentas;

    @FXML
    private TableColumn<Cuenta, String> tcNumeroCuenta;

    @FXML
    private TableColumn<Cuenta, String> tcIdCuenta;

    @FXML
    private TableColumn<Cuenta, String> tcNombreBanco;

    

    @FXML
    void initialize() {

        tcNombreBanco.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        tcNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        tcIdCuenta.setCellValueFactory(new PropertyValueFactory<>("idCuenta"));
        

    }

    public void setUsuarioActual(Usuario usuario) {
        if (usuario == null) {
        System.err.println("El usuario proporcionado es null");
        return;
    }
    this.usuarioActual = usuario;
    System.out.println("Usuario actual: " + usuario.getNombre());

    // Cargamos las cuentas del usuario en la tabla
    cargarCuentas();
    }

   private void cargarCuentas() {
     try {
        if (usuarioActual == null) {
            System.err.println("Usuario actual es null");
            return;
        }

        List<Cuenta> cuentas = usuarioActual.getListaCuentas();
        System.out.println("Cantidad de cuentas del usuario: " + cuentas.size());
        for (Cuenta c : cuentas) {
            System.out.println("Cuenta -> Número: " + c.getNumeroCuenta() + ", Banco: " + c.getNombreBanco());
        }

        ObservableList<Cuenta> cuentasUsuario = FXCollections.observableArrayList(cuentas);
        tableCuentas.setItems(cuentasUsuario);
        tableCuentas.refresh();
    } catch (Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Error cargando cuentas: " + e.getMessage()).showAndWait();
    }

}
     

    @FXML
    void mostrarDetallesCuenta(ActionEvent event) {
           Cuenta cuentaSeleccionada = tableCuentas.getSelectionModel().getSelectedItem();

    if (cuentaSeleccionada != null) {
        // Crear labels con los datos
        Label labelTitulo = new Label("Detalles de la Cuenta");
        labelTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label labelBanco = new Label("Banco: " + cuentaSeleccionada.getNombreBanco());
        Label labelNumero = new Label("Número: " + cuentaSeleccionada.getNumeroCuenta());
        Label labelTipo = new Label("Tipo: " + cuentaSeleccionada.getTipoCuenta().toString());
        Label labelId = new Label("ID de Cuenta: " + cuentaSeleccionada.getIdCuenta());
        Label labelPresupuesto = new Label("Presupuesto: " + (cuentaSeleccionada.getPresupuesto() != null ? cuentaSeleccionada.getPresupuesto().toString() : "No asignado"));
        Label labelTransacciones = new Label("Transacciones: " + cuentaSeleccionada.getListaTransacciones().size());

        // Crear layout y agregar elementos
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));
        vbox.getChildren().addAll(labelTitulo, labelBanco, labelNumero, labelTipo,labelId,labelPresupuesto,labelTransacciones);
        // Crear escena y mostrar en una nueva ventana
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Detalles de la Cuenta");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("No hay cuenta seleccionada");
        alert.setContentText("Por favor selecciona una cuenta.");
        alert.showAndWait();
    }
    }

}

