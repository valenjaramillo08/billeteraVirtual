package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ConsultarSaldoViewController implements Observador {

    public Usuario usuario;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btActualizarSaldo;

    @FXML
    private Button btConsultar;

    @FXML
    private TextField txtSaldoActual;

    @FXML
    private ComboBox<Cuenta> cbCuentas;

    @FXML
    private DatePicker dpHasta;

    @FXML
    private DatePicker dpDesde;

    @FXML
    void initialize() {
        cbCuentas.valueProperty().addListener((obs, oldVal, newVal) -> mostrarSaldoActualCuenta());
        
    }

    public void setUsuarioActual(Usuario usuario){
        this.usuario= usuario;
        llenarComboCuentas();
    }

    private void mostrarSaldoActualCuenta() {
    Cuenta cuentaSeleccionada = cbCuentas.getValue();
    if (cuentaSeleccionada != null && cuentaSeleccionada.getPresupuesto() != null) {
        txtSaldoActual.setText(String.valueOf(cuentaSeleccionada.getPresupuesto().getMontoPresupuesto()));
    } else {
        txtSaldoActual.setText("0.0");
    }
}

    

    public void llenarComboCuentas() {
        cbCuentas.getItems().addAll(usuario.getListaCuentas());
        if (!cbCuentas.getItems().isEmpty()) {
        cbCuentas.getSelectionModel().selectFirst();
        mostrarSaldoActualCuenta();
    }
    }

     @FXML
    void consultarSaldo(ActionEvent event) {
          Cuenta cuentaSeleccionada = cbCuentas.getValue();
          LocalDate desde = dpDesde.getValue();
          LocalDate hasta = dpHasta.getValue();

    if (cuentaSeleccionada != null && desde != null && hasta != null) {
        double saldo = cuentaSeleccionada.calcularSaldo(desde, hasta);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Saldo Consultado");
        alerta.setHeaderText("Información de la cuenta");
        alerta.setContentText("Cuenta: " + cuentaSeleccionada.toString() +
                              "\nDesde: " + desde +
                              "\nHasta: " + hasta +
                              "\nSaldo en ese período: $" + saldo);
        alerta.showAndWait();
    } else {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Datos incompletos");
        error.setContentText("Por favor selecciona una cuenta y un rango de fechas.");
        error.showAndWait();
    }

    }

     @Override
     public void actualizar(Presupuesto presupuesto) {
        txtSaldoActual.setText(String.valueOf(presupuesto.getMontoPresupuesto()));
    }

    public void inicializarVista(Presupuesto presupuestoActual){
        txtSaldoActual.setText(String.valueOf(presupuestoActual.getMontoPresupuesto()));
    }
        
}

