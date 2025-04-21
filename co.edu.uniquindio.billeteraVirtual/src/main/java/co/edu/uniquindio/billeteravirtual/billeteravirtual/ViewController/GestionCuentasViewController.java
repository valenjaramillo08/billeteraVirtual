package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionCuentasController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.AdministradorConstantes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionCuentasViewController {

    Administrador administrador;
    private Cuenta cuentaSeleccionado;
    private ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();

    @FXML
    GestionCuentasController gestionCuentasController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Cuenta, String> columIdCuenta;

    @FXML
    private TableColumn<Cuenta, String> columNCuenta;

    @FXML
    private Button btAgregar;

    @FXML
    private Label lbNumeroCuenta;

    @FXML
    private Button btActualizar;

    @FXML
    private ComboBox<TipoCuenta> cbTipoCuenta;

    @FXML
    private Label lbBanco;

    @FXML
    private Button btDetalles;

    @FXML
    private TextField tfIdCuenta;

    @FXML
    private Label lbIdCuenta;

    @FXML
    private TableView<Cuenta> tabCuenta;

    @FXML
    private TextField tfBanco;

    @FXML
    private TableColumn<Cuenta, String> columBanco;

    @FXML
    private TextField tfNumeroCuenta;

    @FXML
    private TableColumn<Cuenta, String> ColumTpoCuenta;

    @FXML
    private Button btEliminar;

    @FXML
    private Label lbNumeroCuenta1;

    @FXML
    private TextField tfIdUsuario;

    @FXML
    public void initialize() {
        cbTipoCuenta.getItems().addAll(TipoCuenta.values());
        columIdCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdCuenta()));
        columNCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumeroCuenta()));
        columBanco.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreBanco()));
        ColumTpoCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoCuenta().name()));
        listenerSelection();
    }

    /** Invocado desde el padre, ya tenemos el Administrador */
    public void initData(Administrador administrador) {
        this.administrador = administrador;
        this.gestionCuentasController = new GestionCuentasController();
        initView();
    }

    /** Carga lista de cuentas y muestra en la tabla */
    private void initView() {
        listaCuentas.clear();
        listaCuentas.addAll( gestionCuentasController.obtenerCuentas() );
        tabCuenta.setItems(listaCuentas);
    }

    @FXML
    void eliminarCuenta(ActionEvent event) {
        eliminarCuenta();
    }

    @FXML
    void agregarCuenta(ActionEvent event) {

        agregarCuenta();
    }

    @FXML
    void actualizarCuenta(ActionEvent event) {

        actualizarCuenta();
    }

    @FXML
    private void mostrarDetallesCuenta() {
        Cuenta cuentaSeleccionada = tabCuenta.getSelectionModel().getSelectedItem();

        if (cuentaSeleccionada != null) {
            Stage ventanaDetalles = new Stage();
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(15));

            Label id = new Label("ID Cuenta: " + cuentaSeleccionada.getIdCuenta());
            Label banco = new Label("Banco: " + cuentaSeleccionada.getNombreBanco());
            Label numero = new Label("Número: " + cuentaSeleccionada.getNumeroCuenta());
            Label tipo = new Label("Tipo: " + cuentaSeleccionada.getTipoCuenta());

            layout.getChildren().addAll(id, banco, numero, tipo);

            Scene escena = new Scene(layout, 300, 200);
            ventanaDetalles.setTitle("Detalles de la Cuenta");
            ventanaDetalles.setScene(escena);
            ventanaDetalles.initModality(Modality.APPLICATION_MODAL); // bloquea ventana anterior
            ventanaDetalles.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona una cuenta de la tabla primero.");
            alerta.showAndWait();
        }
    }

    private void eliminarCuenta() {

        if (cuentaSeleccionado != null) {
            boolean eliminado = gestionCuentasController.eliminarCuenta(cuentaSeleccionado.getIdCuenta(),cuentaSeleccionado.getNombreBanco(),cuentaSeleccionado.getNumeroCuenta(),cuentaSeleccionado.getTipoCuenta());

            if (eliminado) {
                listaCuentas.remove(cuentaSeleccionado);
                tabCuenta.refresh(); // Refresca la tabla
                mostrarMensaje("Éxito", "Cuenta eliminada", "La cuenta fue eliminada correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos(); // Limpia los campos
            } else {
                mostrarMensaje("Error", "No se pudo eliminar", "Hubo un problema eliminando la cuenta", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Selección requerida", "No se seleccionó ningúna cuenta", "Por favor selecciona una cuenta de la tabla", Alert.AlertType.WARNING);
        }
    }

    private void agregarCuenta() {
        Cuenta cuenta = crearCuenta();
        Usuario usuario = gestionCuentasController.obtenerUsuario(tfIdUsuario.getText());

        if(datosValidos(cuenta)){
            if(gestionCuentasController.agregarCuenta(cuenta.getIdCuenta(),cuenta.getNombreBanco(),cuenta.getNumeroCuenta(),cuenta.getTipoCuenta(), usuario, administrador)){

                listaCuentas.add(cuenta);
                mostrarMensaje(AdministradorConstantes.TITULO_CUENTA_AGREGADO,AdministradorConstantes.HEADER,AdministradorConstantes.BODY_CUENTA_AGREGADO, Alert.AlertType.INFORMATION);
            }

        }else {
            mostrarMensaje(AdministradorConstantes.TITULO_INCOMPLETO,AdministradorConstantes.HEADER,AdministradorConstantes.BODY_INCOMPLETO, Alert.AlertType.WARNING);

        }
    }

    public void actualizarCuenta() {

        if (cuentaSeleccionado != null) {
            Cuenta cuentaActualizado = crearCuenta();

            if (datosValidos(cuentaActualizado)) {
                boolean actualizado = gestionCuentasController.actualizarCuenta(
                        cuentaSeleccionado.getIdCuenta(),
                        cuentaActualizado.idCuenta,
                        cuentaActualizado.nombreBanco,
                        cuentaActualizado.numeroCuenta,
                        cuentaActualizado.tipoCuenta,
                        cuentaActualizado.usuarioAsociado,
                        cuentaActualizado.administradorAsociado
                );

                if (actualizado) {
                    int index = listaCuentas.indexOf(cuentaSeleccionado);
                    listaCuentas.set(index, cuentaActualizado);
                    tabCuenta.refresh(); // Refresca la tabla con los nuevos datos
                    mostrarMensaje("Éxito", "Cuenta actualizada", "La cuenta fue actualizado correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCampos(); // 🔥 Limpia los campos después de actualizar
                } else {
                    mostrarMensaje("Error", "No se pudo actualizar", "Hubo un problema actualizando la cuenta", Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Datos incompletos", "Verifica los campos", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Selección requerida", "No se seleccionó ningúna cuenta", "Por favor selecciona una cuenta de la tabla", Alert.AlertType.WARNING);
        }
    }



    private Cuenta crearCuenta() {

        return new Cuenta(
                tfIdCuenta.getText(),
                tfBanco.getText(),
                tfNumeroCuenta.getText(),
                cbTipoCuenta.getSelectionModel().getSelectedItem(),
                gestionCuentasController.obtenerUsuario(tfIdUsuario.getText()),
                administrador
        );
    }

    private boolean datosValidos(Cuenta cuenta) {
        return cuenta != null &&
                cuenta.getIdCuenta() != null && !cuenta.getIdCuenta().isEmpty() &&
                cuenta.getNombreBanco() != null && !cuenta.getNombreBanco().isEmpty() &&
                cuenta.getNumeroCuenta() != null && !cuenta.getNumeroCuenta().isEmpty() &&
                cuenta.getTipoCuenta() != null;
    }



    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    private void limpiarCampos() {
        tfIdCuenta.clear();
        tfBanco.clear();
        tfNumeroCuenta.clear();
        cuentaSeleccionado = null;
        tabCuenta.getSelectionModel().clearSelection();
    }

    private void initDataBinding() {
        columIdCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCuenta()));
        columNCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumeroCuenta()));
        columBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreBanco()));
        ColumTpoCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoCuenta().name()));
    }

    private void obtenerCuentas() {

        listaCuentas.addAll(gestionCuentasController.obtenerCuentas());
    }

    private void listenerSelection() {
        tabCuenta.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
                    cuentaSeleccionado = newSelection;
                    mostrarInformacionCuenta(cuentaSeleccionado);
                }

        );
    }

    private void mostrarInformacionCuenta(Cuenta cuentaSeleccionado) {
        if(cuentaSeleccionado != null){
            tfIdCuenta.setText(cuentaSeleccionado.getIdCuenta());
            tfBanco.setText(cuentaSeleccionado.getNombreBanco());
            tfNumeroCuenta.setText(cuentaSeleccionado.getNumeroCuenta());
        }
    }
}
