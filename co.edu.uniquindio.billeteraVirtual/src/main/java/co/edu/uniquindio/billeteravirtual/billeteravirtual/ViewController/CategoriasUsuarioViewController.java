package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriasUsuarioViewController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columNombre;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private TextField txtSaldoCategoria;

    @FXML
    private TableView<?> tableCategoria;

    @FXML
    private TableColumn<?, ?> columSaldo;

    @FXML
    private ComboBox<?> comboCuentas;

    @FXML
    void onCrearCategoria(ActionEvent event) {

    }

    @FXML
    void onModificarCategoria(ActionEvent event) {

    }

    @FXML
    void onEliminarCategoria(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
