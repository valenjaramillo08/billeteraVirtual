package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    this.usuarioActual = usuario;
    cargarCuentas(); // Cargar cuentas del usuario una vez recibido
  }

   private void cargarCuentas() {
      try {
        if(usuarioActual == null) {
            System.err.println("Usuario actual es null");
            return;
        }
        
        ObservableList<Cuenta> cuentasUsuario = DataUtil.obtenerCuentasUsuario(usuarioActual);
        System.out.println("Cuentas del usuario: " + cuentasUsuario);//NUNCA LLEGA A ESTE METODO

        if(cuentasUsuario == null || cuentasUsuario.isEmpty()) {
            System.out.println("No se encontraron cuentas para este usuario");
            return;
        }
        
        
        tableCuentas.setItems(cuentasUsuario);
        tableCuentas.refresh(); // Forzar actualización de la tabla
        
    } catch (Exception e) {
        System.err.println("Error al cargar cuentas: " + e.getMessage());
        e.printStackTrace();
        
        // Mostrar alerta al usuario
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se pudieron cargar las cuentas");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
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

