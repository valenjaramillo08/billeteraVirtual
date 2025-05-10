package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
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

    @FXML
    private TextField txtMonto, txtSaldoActual;
    @FXML
    private ComboBox<TipoTransaccion> cbTipo;
    @FXML
    private ComboBox<Cuenta> cbCuentaOrigen, cbCuentaDestino;
    @FXML
    private Button btTransaccion;
    @FXML
    private TableView<Transaccion> tablaTransacciones;
    @FXML
    private TableColumn<Transaccion, LocalDate> tabCFecha;
    @FXML
    private TableColumn<Transaccion, String> tabTipo, tabCCuentaO, tabCCuentaD;
    @FXML
    private TableColumn<Transaccion, Double> tabCM;

    private ObservableList<Cuenta> cuentasUsuario;
    private ObservableList<Cuenta> cuentas;
    private ObservableList<Transaccion> transacciones;
    private GestionTransaccionesController controller;
    private Usuario usuarioActual;

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
            ObservableList<Cuenta> filtradas = cuentas.filtered(c -> !c.equals(origen));
            cbCuentaDestino.setItems(filtradas);
        } else if (cbTipo.getValue() == TipoTransaccion.DEPOSITO) {
            cbCuentaDestino.setItems(cuentas);
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
            String id = UUID.randomUUID().toString();
            LocalDate fecha = LocalDate.now();
            double monto = Double.parseDouble(txtMonto.getText());
            TipoTransaccion tipo = cbTipo.getValue();
            Cuenta origen = cbCuentaOrigen.getValue();
            Cuenta destino = cbCuentaDestino.getValue();

            // ✅ Verificación: si es RETIRO o TRANSFERENCIA, revisa que el monto no supere
            // el saldo
            if ((tipo == TipoTransaccion.RETIRO || tipo == TipoTransaccion.TRANSFERENCIA) && origen != null) {
                double saldoDisponible = controller.saldoCuenta(origen);

                if (monto > saldoDisponible) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Saldo insuficiente",
                            "No puedes retirar o transferir más de lo que tienes.");
                    return; // Salir sin hacer la transacción
                }
            }

            // ✅ Si todo está bien, crea la transacción
            if (controller.crearTransaccion(origen, destino, monto, "Generada por usuario", tipo)) {
                Transaccion nueva = new Transaccion(id, origen, fecha, monto, "Generada por usuario", destino, tipo);
                transacciones.add(nueva);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Transacción registrada correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Error", "Verifica los campos requeridos.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos inválidos o incompletos.");
        }
    }

    private void limpiarCampos() {
        txtMonto.clear();
        cbTipo.setValue(null);
        cbCuentaOrigen.setValue(null);
        cbCuentaDestino.setValue(null);

        // Oculte campos según el tipo
        cbCuentaOrigen.setVisible(false);
        cbCuentaDestino.setVisible(false);
    }

    public void actualizarSaldoActual(String saldo) {
        txtSaldoActual.setText(saldo);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void initialize() {
        controller = new GestionTransaccionesController();
        transacciones = FXCollections.observableArrayList();
        configurarTabla();

        cbTipo.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));
        cbTipo.setOnAction(e -> actualizarVisibilidadCampos());
        cbCuentaOrigen.setOnAction(e -> {
            filtrarCuentasDestino();

            Cuenta cuentaSeleccionada = cbCuentaOrigen.getValue();
            if (cuentaSeleccionada != null) {
                // ✅ Aquí llamas al método del controller
                double saldo = controller.saldoCuenta(cuentaSeleccionada);

                // ✅ Y luego actualizas el TextField
                actualizarSaldoActual(String.valueOf(saldo));
            } else {
                actualizarSaldoActual(""); // Si se limpia la selección
            }
        });

        txtSaldoActual.setEditable(false);
        txtSaldoActual.setFocusTraversable(false);

        tablaTransacciones.setItems(transacciones);

    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;

        // Inicializa datos que dependen del usuario
        if (usuarioActual != null) {
            cuentasUsuario = FXCollections.observableArrayList(controller.listarCuentasUsuario(usuario));
            cuentas = FXCollections.observableArrayList(controller.listarCuentas());
            cbCuentaOrigen.setItems(cuentasUsuario);
            cbCuentaDestino.setItems(cuentas);

            // Puedes también inicializar transacciones del usuario si las tienes
            transacciones.addAll(controller.listarTransacciones(usuario));

        }
    }

}
