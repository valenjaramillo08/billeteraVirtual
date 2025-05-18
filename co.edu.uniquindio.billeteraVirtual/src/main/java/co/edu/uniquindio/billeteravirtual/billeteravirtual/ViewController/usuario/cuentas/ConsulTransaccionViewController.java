package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
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
import java.util.stream.Collectors;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsulTransaccionViewController {

    public ObservableList<Transaccion> transacciones = FXCollections.observableArrayList();
    public Usuario usuarioLogueado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


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
    private ComboBox<TipoTransaccion> cbTipo;

    @FXML
    void initialize() {

        cbTipo.getItems().setAll(TipoTransaccion.values());
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaTransaccion"));
        tcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaProcesada"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion")); 

    }

    public void setUsuarioLogueado(Usuario usuario){
        this.usuarioLogueado = usuario;
        mostrarTodasTransacciones();
    }

    @FXML
    void buscarTransaccion() {
        TipoTransaccion tipoSeleccionado = cbTipo.getValue();
    if (usuarioLogueado != null && tipoSeleccionado != null) {
        List<Transaccion> filtradas = usuarioLogueado.getListaTransacciones().stream()
            .filter(t -> t.getTipoTransaccion() == tipoSeleccionado)
            .collect(java.util.stream.Collectors.toList());
        if (filtradas.isEmpty()) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Sin resultados");
            alert.setHeaderText(null);
            alert.setContentText("No se encontraron transacciones de ese tipo.");
            alert.showAndWait();
        }
        transacciones.setAll(filtradas);
        tableTransacciones.setItems(transacciones);
    }

    }

    @FXML
    void todasTransacciones() {
        mostrarTodasTransacciones();

    }

    private void mostrarTodasTransacciones(){
        if(usuarioLogueado != null){
            transacciones.setAll(usuarioLogueado.getListaTransacciones());
            tableTransacciones.setItems(transacciones);
        }
    }

}

