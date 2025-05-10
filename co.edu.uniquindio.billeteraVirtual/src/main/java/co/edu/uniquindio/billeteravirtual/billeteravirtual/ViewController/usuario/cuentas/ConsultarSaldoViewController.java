package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ConsultarSaldoViewController {

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
    private ComboBox<?> cbCuentas;

    @FXML
    private DatePicker dpHasta;

    @FXML
    private DatePicker dpDesde;

    @FXML
    void initialize() {
        
    }
}

