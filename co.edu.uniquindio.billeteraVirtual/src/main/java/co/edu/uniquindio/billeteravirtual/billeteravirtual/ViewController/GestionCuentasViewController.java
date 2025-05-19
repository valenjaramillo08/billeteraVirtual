package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionCuentasController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorAdministrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.UsuarioObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.geometry.Insets;


import java.net.URL;
import java.util.ResourceBundle;

public class GestionCuentasViewController implements ObservadorAdministrador {

    private GestionCuentasController gestionCuentasController;
    private Administrador administrador;
    private Cuenta cuentaSeleccionado;
    private ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private TableView<Cuenta> tabCuenta;
    @FXML private TableColumn<Cuenta, String> columIdCuenta;
    @FXML private TableColumn<Cuenta, String> columBanco;
    @FXML private TableColumn<Cuenta, String> columNCuenta;
    @FXML private TableColumn<Cuenta, String> ColumTpoCuenta;
    @FXML private TableColumn<Cuenta, String> columIdUsuario;

    @FXML private TextField tfIdCuenta;
    @FXML private TextField tfBanco;
    @FXML private TextField tfNumeroCuenta;
    @FXML private ComboBox<TipoCuenta> cbTipoCuenta;
    @FXML private ComboBox<Usuario> cbUsuarios;

    @FXML private Button btAgregar;
    @FXML private Button btActualizar;
    @FXML private Button btDetalles;
    @FXML private Button btEliminar;

    @FXML
    public void initialize() {
        gestionCuentasController = new GestionCuentasController();
        UsuarioObservable.agregarObservador(this);

        cbTipoCuenta.getItems().addAll(TipoCuenta.values());
        cbUsuarios.setItems(FXCollections.observableArrayList(gestionCuentasController.obtenerTodosLosUsuarios()));
        cbUsuarios.setConverter(new StringConverter<>() {
            @Override
            public String toString(Usuario usuario) {
                return usuario != null ? usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getIdUsuario() + ")" : "";
            }

            @Override
            public Usuario fromString(String s) {
                return null;
            }
        });

        columIdCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdCuenta()));
        columBanco.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreBanco()));
        columNCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumeroCuenta()));
        ColumTpoCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoCuenta().name()));
        columIdUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsuarioAsociado().getIdUsuario()));

        tabCuenta.setItems(listaCuentas);
        listenerSelection();
    }

    public void initData(Administrador administrador) {
        this.administrador = administrador;
        listaCuentas.clear();
        listaCuentas.addAll(gestionCuentasController.obtenerCuentas());
    }

    @FXML
    public void agregarCuenta() {
        Usuario usuario = cbUsuarios.getValue();

        if (usuario == null) {
            mostrarMensaje("Usuario no encontrado", null, "Debes seleccionar un usuario del combo.", Alert.AlertType.WARNING);
            return;
        }

        Cuenta nueva = new Cuenta(
                tfIdCuenta.getText().trim(),
                tfBanco.getText().trim(),
                tfNumeroCuenta.getText().trim(),
                cbTipoCuenta.getValue(),
                usuario,
                administrador
        );

        if (!datosValidos(nueva)) {
            mostrarMensaje("Datos incompletos", null, "Todos los campos son obligatorios y deben ser válidos.", Alert.AlertType.WARNING);
            return;
        }

        boolean insertada = gestionCuentasController.agregarCuenta(
                nueva.getIdCuenta(),
                nueva.getNombreBanco(),
                nueva.getNumeroCuenta(),
                nueva.getTipoCuenta(),
                usuario,
                administrador
        );

        if (insertada) {
            listaCuentas.add(nueva);
            tabCuenta.refresh();
            mostrarMensaje("Éxito", null, "La cuenta se guardó correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", null, "No se pudo guardar la cuenta (podría estar duplicada).", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void actualizarCuenta() {
        if (cuentaSeleccionado != null) {
            Cuenta cuentaActualizada = crearCuenta();

            if (!datosValidos(cuentaActualizada)) {
                mostrarMensaje("Datos incompletos", null, "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            boolean actualizado = gestionCuentasController.actualizarCuenta(
                    cuentaSeleccionado.getIdCuenta(),
                    cuentaActualizada.getIdCuenta(),
                    cuentaActualizada.getNombreBanco(),
                    cuentaActualizada.getNumeroCuenta(),
                    cuentaActualizada.getTipoCuenta(),
                    cuentaActualizada.getUsuarioAsociado(),
                    cuentaActualizada.getAdministradorAsociado()
            );

            if (actualizado) {
                int index = listaCuentas.indexOf(cuentaSeleccionado);
                listaCuentas.set(index, cuentaActualizada);
                tabCuenta.refresh();
                mostrarMensaje("Éxito", "Cuenta actualizada", "La cuenta fue actualizada correctamente.", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo actualizar", "Hubo un problema actualizando la cuenta", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Selección requerida", null, "Selecciona una cuenta para actualizar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void eliminarCuenta() {
        if (cuentaSeleccionado != null) {
            boolean eliminado = gestionCuentasController.eliminarCuenta(
                    cuentaSeleccionado.getIdCuenta(),
                    cuentaSeleccionado.getNombreBanco(),
                    cuentaSeleccionado.getNumeroCuenta(),
                    cuentaSeleccionado.getTipoCuenta()
            );

            if (eliminado) {
                listaCuentas.remove(cuentaSeleccionado);
                tabCuenta.refresh();
                mostrarMensaje("Éxito", "Cuenta eliminada", "La cuenta fue eliminada correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar", "Hubo un problema eliminando la cuenta", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Selección requerida", null, "Selecciona una cuenta para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void mostrarDetallesCuenta() {
        Cuenta c = tabCuenta.getSelectionModel().getSelectedItem();

        if (c != null) {
            Stage ventana = new Stage();
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(15));

            layout.getChildren().addAll(
                    new Label("ID Cuenta: " + c.getIdCuenta()),
                    new Label("Banco: " + c.getNombreBanco()),
                    new Label("Número: " + c.getNumeroCuenta()),
                    new Label("Tipo: " + c.getTipoCuenta()),
                    new Label("Usuario: " + c.getUsuarioAsociado().getIdUsuario())
            );

            Scene escena = new Scene(layout, 300, 200);
            ventana.setTitle("Detalles de la Cuenta");
            ventana.setScene(escena);
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.showAndWait();
        } else {
            mostrarMensaje("Información", null, "Selecciona una cuenta de la tabla primero.", Alert.AlertType.INFORMATION);
        }
    }

    private boolean datosValidos(Cuenta c) {
        return c != null &&
                c.getIdCuenta() != null && !c.getIdCuenta().isBlank() &&
                c.getNombreBanco() != null && !c.getNombreBanco().isBlank() &&
                c.getNumeroCuenta() != null && !c.getNumeroCuenta().isBlank() &&
                c.getTipoCuenta() != null &&
                c.getUsuarioAsociado() != null;
    }

    private Cuenta crearCuenta() {
        return new Cuenta(
                tfIdCuenta.getText(),
                tfBanco.getText(),
                tfNumeroCuenta.getText(),
                cbTipoCuenta.getSelectionModel().getSelectedItem(),
                cbUsuarios.getValue(),
                administrador
        );
    }

    private void limpiarCampos() {
        tfIdCuenta.clear();
        tfBanco.clear();
        tfNumeroCuenta.clear();
        cbTipoCuenta.getSelectionModel().clearSelection();
        cbUsuarios.getSelectionModel().clearSelection();
        cuentaSeleccionado = null;
        tabCuenta.getSelectionModel().clearSelection();
    }

    private void listenerSelection() {
        tabCuenta.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            cuentaSeleccionado = newVal;
            if (newVal != null) {
                tfIdCuenta.setText(newVal.getIdCuenta());
                tfBanco.setText(newVal.getNombreBanco());
                tfNumeroCuenta.setText(newVal.getNumeroCuenta());
                cbTipoCuenta.setValue(newVal.getTipoCuenta());
                cbUsuarios.setValue(newVal.getUsuarioAsociado());
            }
        });
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @Override
    public void actualizarUsuarios() {
        cbUsuarios.getItems().clear();
        cbUsuarios.getItems().addAll(gestionCuentasController.obtenerTodosLosUsuarios());
    }
}
