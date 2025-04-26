package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionTransaccionesViewController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columTipoTransaccion;

    @FXML
    private DatePicker dateDesde;

    @FXML
    private TableColumn<?, ?> columIdUsuario;

    @FXML
    private TableColumn<?, ?> columMonto;

    @FXML
    private DatePicker fechaTransaccion;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private ChoiceBox<?> comboUsuarioNuevaTransaccion;

    @FXML
    private DatePicker dateHasta;

    @FXML
    private TextField labelTipoTransaccion;

    @FXML
    private TableColumn<?, ?> columTransaccionFecha;

    @FXML
    private ChoiceBox<?> comboUsuarioLista;

    @FXML
    private TextField labelMontoTransaccion;

    @FXML
    void onCrearTransaccion(ActionEvent event) {

    }

    @FXML
    void onListarTransacciones(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
