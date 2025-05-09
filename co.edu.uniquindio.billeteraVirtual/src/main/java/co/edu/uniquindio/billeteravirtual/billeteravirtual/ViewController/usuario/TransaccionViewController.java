package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.UUID;

public class TransaccionViewController {

    @FXML private TextField txtIdTran, txtMonto, txtSaldoActual;
    @FXML private DatePicker dateTransaccion;
    @FXML private ComboBox<TipoTransaccion> cbTipo;
    @FXML private ComboBox<Cuenta> cbCuentaOrigen, cbCuentaDestino;
    @FXML private Button btTransaccion;
    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, LocalDate> tabCFecha;
    @FXML private TableColumn<Transaccion, String> tabTipo, tabCCuentaO, tabCCuentaD;
    @FXML private TableColumn<Transaccion, Double> tabCM;

    private ObservableList<Cuenta> cuentasUsuario;
    private ObservableList<Transaccion> transacciones;
    private GestionTransaccionesController controller;
    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        controller = new GestionTransaccionesController();

        //transacciones = FXCollections.observableArrayList();

        cbTipo.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));
        cbCuentaOrigen.setItems(cuentasUsuario);
        cbCuentaDestino.setItems(cuentasUsuario);

        cbTipo.setOnAction(e -> actualizarVisibilidadCampos());
        cbCuentaOrigen.setOnAction(e -> filtrarCuentasDestino());

        //tablaTransacciones.setItems(transacciones);
        configurarTabla();
    }
    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    private void actualizarVisibilidadCampos() {
        TipoTransaccion tipo = cbTipo.getValue();
        cbCuentaOrigen.setVisible(false);
        cbCuentaDestino.setVisible(false);

        if (tipo == TipoTransaccion.RETIRO) {
            cbCuentaOrigen.setVisible(true);
        } else if (tipo == TipoTransaccion.DEPOSITO) {
            cbCuentaDestino.setVisible(true);
        } else if (tipo == TipoTransaccion.TRANSFERENCIA) {
            cbCuentaOrigen.setVisible(true);
            cbCuentaDestino.setVisible(true);
            filtrarCuentasDestino();
        }
    }

    private void filtrarCuentasDestino() {
        Cuenta origen = cbCuentaOrigen.getValue();
        if (origen != null && cbTipo.getValue() == TipoTransaccion.TRANSFERENCIA) {
            ObservableList<Cuenta> filtradas = cuentasUsuario.filtered(c -> !c.equals(origen));
            cbCuentaDestino.setItems(filtradas);
        }
    }

    private void configurarTabla() {
        tabCFecha.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaTransaccion()));
        tabTipo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoTransaccion().toString()));
        tabCCuentaO.setCellValueFactory(data -> {
            Cuenta origen = data.getValue().getCuentaOrigen();
            return new SimpleStringProperty(origen != null ? origen.getNumeroCuenta() : "-");
        });
        tabCCuentaD.setCellValueFactory(data -> {
            Cuenta destino = data.getValue().getCuentaDestino();
            return new SimpleStringProperty(destino != null ? destino.getNumeroCuenta() : "-");
        });
        tabCM.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMonto()));
    }

    @FXML
    void onCrearTransaccion(ActionEvent event) {
        try {
            String id = txtIdTran.getText().isEmpty() ? UUID.randomUUID().toString() : txtIdTran.getText();
            LocalDate fecha = dateTransaccion.getValue() != null ? dateTransaccion.getValue() : LocalDate.now();
            double monto = Double.parseDouble(txtMonto.getText());
            TipoTransaccion tipo = cbTipo.getValue();
            Cuenta origen = cbCuentaOrigen.getValue();
            Cuenta destino = cbCuentaDestino.getValue();

            if (controller.crearTransaccion(origen, destino, monto, "Generada por usuario", tipo)) {
                Transaccion nueva = new Transaccion(id, origen, fecha, monto, "Generada por usuario", destino, tipo);
                transacciones.add(nueva);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Transacción registrada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Error", "Verifica los campos requeridos.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos inválidos o incompletos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
