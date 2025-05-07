package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ObserverUsuario.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class TransaccionViewController implements Observer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMonto;

    @FXML
    private ComboBox<String> cbCuentaDestino;

    @FXML
    private TextField txtIdTran;

    @FXML
    private TextField txtSaldoActual;

    @FXML
    private TableColumn<Transaccion, String> tabCCuentaD;

    @FXML
    private TableColumn<Transaccion, Double> tabCM;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private ComboBox<String> cbCuentaOrigen;

    @FXML
    private Button btTransaccion;

    @FXML
    private DatePicker dateTransaccion;

    @FXML
    private TableColumn<Cuenta, String> tabCCuentaO;

    @FXML
    private TableColumn<Transaccion, LocalDate> tabCFecha;

    @FXML
    private TableColumn<Transaccion, String> tabTipo;

   

    @FXML
    void initialize() {
       
    }





    @Override
    public void actualizar(double saldoActual) {
       txtMonto.setText(String.valueOf(saldoActual));
    }
}


