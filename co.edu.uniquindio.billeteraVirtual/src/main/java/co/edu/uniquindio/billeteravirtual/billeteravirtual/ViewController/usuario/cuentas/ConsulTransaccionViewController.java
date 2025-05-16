package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsulTransaccionViewController {

    public ObservableList<Transaccion> transacciones = FXCollections.observableArrayList();
    public Usuario usuarioLogueado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private TableColumn<Transaccion, String> tcTipo;

    @FXML
    private TableColumn<Transaccion, LocalDate> tcFecha;

    @FXML
    private TextField txtBuscarTransacciones;

    @FXML
    private Button btBuscar;

    @FXML
    private TableColumn<Transaccion, String> tcCategoria;

    @FXML
    private Button btExportar;

    @FXML
    private TableView<Transaccion> tableTransacciones;

    @FXML
    private TableColumn<Transaccion, Double> tcMonto;

    @FXML
    private Button btVerTodas;

    @FXML
    void initialize() {

        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

       


        

    }
}

