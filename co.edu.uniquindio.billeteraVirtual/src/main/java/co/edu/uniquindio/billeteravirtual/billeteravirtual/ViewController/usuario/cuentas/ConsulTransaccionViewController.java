package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConsulTransaccionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cbCategoria;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    private TextField txtBuscarTransacciones;

    @FXML
    private Button btBuscar;

    @FXML
    private TableColumn<?, ?> tcCategoria;

    @FXML
    private Button btExportar;

    @FXML
    private TableView<?> tableTransacciones;

    @FXML
    private TableColumn<?, ?> tcMonto;

    @FXML
    private Button btVerTodas;

    @FXML
    void initialize() {
        

    }
}

