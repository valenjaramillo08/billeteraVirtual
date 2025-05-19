package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorCSV;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorPDF;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.Command;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.InvocadorReporte;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.ReporteAdminPDFCommand;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.AdminGestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorAdministrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.UsuarioObservable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTransaccionesViewController implements ObservadorAdministrador {

    @FXML private ComboBox<Usuario> comboUsuarios;
    @FXML private Button btnExportarCSV, btnExportarPDF, btnCrearTransaccion;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField txtMonto, txtDescripcion;
    @FXML private ComboBox<TipoTransaccion> comboTipoTransaccion;
    @FXML private ComboBox<Cuenta> comboCuentaOrigen, comboCuentaDestino;
    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, LocalDate> colFecha;
    @FXML private TableColumn<Transaccion, String> colDescripcion, colTipo, colCuentaOrigen, colCuentaDestino;
    @FXML private TableColumn<Transaccion, Double> colMonto;
    @FXML private Label labelSaldo;

    private final AdminGestionTransaccionesController controller = new AdminGestionTransaccionesController();

    @FXML
    public void initialize() {
        UsuarioObservable.agregarObservador(this);
        comboUsuarios.setItems(FXCollections.observableArrayList(controller.obtenerUsuarios()));
        comboTipoTransaccion.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));

        comboUsuarios.setConverter(new StringConverter<>() {
            @Override
            public String toString(Usuario usuario) {
                return usuario != null ? usuario.getNombre() : "";
            }
            @Override
            public Usuario fromString(String s) { return null; }
        });

        comboUsuarios.setOnAction(e -> cargarCuentasUsuario());
        comboCuentaOrigen.setOnAction(e -> filtrarCuentaDestino());

        btnExportarCSV.setOnAction(e -> exportarCSV());
        btnExportarPDF.setOnAction(e -> exportarPDFConCommand());

        configurarComboBoxCuentas();
        configurarTabla();
    }

    @FXML
    void onCrearTransaccion() {
        Cuenta cuentaOrigen = comboCuentaOrigen.getValue();
        Cuenta cuentaDestino = comboCuentaDestino.getValue();
        String descripcion = txtDescripcion.getText();
        TipoTransaccion tipo = comboTipoTransaccion.getValue();

        if ((tipo != TipoTransaccion.DEPOSITO && cuentaOrigen == null) || tipo == null || descripcion.isBlank()) {
            mostrarAlerta("Completa todos los campos obligatorios.");
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(txtMonto.getText());
            if (monto <= 0) {
                mostrarAlerta("El monto debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Monto inválido.");
            return;
        }

        // Asegurar que cuentas tengan presupuesto antes de la transacción
        if (cuentaOrigen != null && cuentaOrigen.getPresupuesto() == null) {
            cuentaOrigen.setPresupuesto(new Presupuesto(0, 0));
        }
        if (cuentaDestino != null && cuentaDestino.getPresupuesto() == null) {
            cuentaDestino.setPresupuesto(new Presupuesto(0, 0));
        }

        if ((tipo == TipoTransaccion.RETIRO || tipo == TipoTransaccion.TRANSFERENCIA)) {
            double saldoDisponible = controller.obtenerSaldoCuenta(cuentaOrigen);
            if (monto > saldoDisponible) {
                mostrarAlerta("Saldo insuficiente para realizar la transacción.");
                return;
            }
        }

        boolean creada = controller.crearTransaccion(cuentaOrigen, cuentaDestino, monto, descripcion, tipo);

        if (creada) {
            mostrarAlerta("Transacción creada exitosamente.");
            tablaTransacciones.getItems().add(cuentaOrigen.getListaTransacciones().getLast());

            if (cuentaOrigen != null) {
                labelSaldo.setText(String.valueOf(controller.obtenerSaldoCuenta(cuentaOrigen)));
            }

            limpiarCampos();
        } else {
            mostrarAlerta("No se pudo crear la transacción.");
        }
    }

    private void exportarCSV() {
        Usuario usuario = comboUsuarios.getValue();
        if (usuario == null) {
            mostrarAlerta("Selecciona un usuario.");
            return;
        }

        File archivo = mostrarDialogoGuardar("csv", "Archivo CSV (*.csv)");
        if (archivo != null) {
            controller.exportarReporte(usuario, new ExportadorCSV(), eliminarExtension(archivo.getAbsolutePath()));
        }
    }

    private void exportarPDFConCommand() {
        Usuario usuario = comboUsuarios.getValue();
        List<Transaccion> transacciones = tablaTransacciones.getItems();

        if (usuario == null) {
            mostrarAlerta("Selecciona un usuario.");
            return;
        }

        if (transacciones == null || transacciones.isEmpty()) {
            mostrarAlerta("No hay transacciones para exportar.");
            return;
        }

        File archivo = mostrarDialogoGuardar("pdf", "Archivo PDF (*.pdf)");
        if (archivo != null) {
            Command comando = new ReporteAdminPDFCommand(
                    usuario,
                    new ArrayList<>(transacciones),
                    eliminarExtension(archivo.getAbsolutePath())
            );
            InvocadorReporte invocador = new InvocadorReporte();
            invocador.setCommand(comando);
            invocador.ejecutar();
        }
    }

    private void limpiarCampos() {
        txtMonto.clear();
        txtDescripcion.clear();
        comboTipoTransaccion.getSelectionModel().clearSelection();
        comboCuentaOrigen.getSelectionModel().clearSelection();
        comboCuentaDestino.getSelectionModel().clearSelection();
    }

    private void cargarCuentasUsuario() {
        Usuario usuario = comboUsuarios.getValue();
        if (usuario != null && usuario.getListaCuentas() != null) {
            comboCuentaOrigen.setItems(FXCollections.observableArrayList(usuario.getListaCuentas()));
            comboCuentaDestino.setItems(FXCollections.observableArrayList(controller.obtenerTodasLasCuentas()));
        }
    }

    private void filtrarCuentaDestino() {
        Cuenta origen = comboCuentaOrigen.getValue();
        if (origen != null) {
            List<Cuenta> cuentasFiltradas = controller.obtenerTodasLasCuentas().stream()
                    .filter(c -> !c.getNumeroCuenta().equals(origen.getNumeroCuenta()))
                    .toList();
            comboCuentaDestino.setItems(FXCollections.observableArrayList(cuentasFiltradas));
            labelSaldo.setText(String.valueOf(controller.obtenerSaldoCuenta(origen)));
        }
    }

    private void configurarComboBoxCuentas() {
        comboCuentaOrigen.setConverter(new StringConverter<>() {
            @Override public String toString(Cuenta cuenta) { return cuenta != null ? cuenta.getNumeroCuenta() : ""; }
            @Override public Cuenta fromString(String s) { return null; }
        });
        comboCuentaDestino.setConverter(new StringConverter<>() {
            @Override public String toString(Cuenta cuenta) { return cuenta != null ? cuenta.getNumeroCuenta() : ""; }
            @Override public Cuenta fromString(String s) { return null; }
        });
    }

    private File mostrarDialogoGuardar(String extension, String descripcion) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(descripcion, "*." + extension));
        fileChooser.setInitialFileName("reporte." + extension);
        return fileChooser.showSaveDialog(new Stage());
    }

    private String eliminarExtension(String ruta) {
        int punto = ruta.lastIndexOf('.');
        return (punto > 0) ? ruta.substring(0, punto) : ruta;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaTransaccion()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colMonto.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMonto()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoTransaccion().toString()));
        colCuentaOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCuentaOrigen().getNumeroCuenta()));
        colCuentaDestino.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCuentaDestino() != null ? cellData.getValue().getCuentaDestino().getNumeroCuenta() : "N/A"
        ));
        tablaTransacciones.setItems(FXCollections.observableArrayList());
    }

    @Override
    public void actualizarUsuarios() {
        comboUsuarios.getItems().clear();
        comboUsuarios.getItems().addAll(controller.obtenerUsuarios());
    }
}
