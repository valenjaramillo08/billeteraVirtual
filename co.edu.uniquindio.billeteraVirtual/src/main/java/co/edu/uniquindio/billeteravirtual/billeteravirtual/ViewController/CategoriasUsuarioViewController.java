package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.CategoriasUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriasUsuarioViewController {
    CategoriasUsuarioController categoriasUsuarioController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Categoria, String> columNombre;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private TextField txtSaldoCategoria;

    @FXML
    private TableView<Categoria> tableCategoria;

    @FXML
    private TableColumn<Categoria, Double> columSaldo;

    @FXML
    private ComboBox<Cuenta> comboCuentas;

    @FXML
    void onCrearCategoria(ActionEvent event) {
        crearCategoria();

    }

    @FXML
    void onModificarCategoria(ActionEvent event) {
        modificarCategoria();


    }

    @FXML
    void onEliminarCategoria(ActionEvent event) {
        eliminarCategoria();

    }

    @FXML
    void onSeleccionarCuenta(ActionEvent event) {
        cuentaSeleccionada();

    }

    private void modificarCategoria(){
        Cuenta cuentaSeleccionada = comboCuentas.getValue();
        Categoria categoriaSeleccionada = tableCategoria.getSelectionModel().getSelectedItem();

        if (cuentaSeleccionada == null || cuentaSeleccionada.getPresupuesto() == null) {
            mostrarAlertaError("Seleccione una cuenta válida.");
            return;
        }

        if (categoriaSeleccionada == null) {
            mostrarAlertaError("Seleccione una categoría para modificar.");
            return;
        }

        String nuevoNombre = txtNombreCategoria.getText().trim();
        String nuevoSaldoStr = txtSaldoCategoria.getText().trim();

        if (nuevoNombre.isEmpty() || nuevoSaldoStr.isEmpty()) {
            mostrarAlertaError("Debe completar todos los campos.");
            return;
        }

        try {
            double nuevoSaldo = Double.parseDouble(nuevoSaldoStr);
            NombreCategoria nombreCategoria = NombreCategoria.valueOf(nuevoNombre.toUpperCase());

            Presupuesto presupuesto = cuentaSeleccionada.getPresupuesto();
            double saldoDisponible = presupuesto.getMontoPresupuesto() - presupuesto.getMontoPresupuestoGastado()
                    + categoriaSeleccionada.getSaldo(); // restablece el saldo anterior

            if (nuevoSaldo > saldoDisponible) {
                mostrarAlertaError("Saldo insuficiente para modificar la categoría.");
                return;
            }

            // Actualizar categoría
            categoriaSeleccionada.setNombreCategoria(nombreCategoria);
            presupuesto.setMontoPresupuestoGastado(
                    presupuesto.getMontoPresupuestoGastado()
                            - categoriaSeleccionada.getSaldo()
                            + nuevoSaldo
            );
            categoriaSeleccionada.setSaldo(nuevoSaldo);

            tableCategoria.refresh(); // actualiza visualmente
            mostrarAlertaInfo("Categoría modificada correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlertaError("El saldo debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mostrarAlertaError("Nombre de categoría inválido.");
        }
    }

    private void eliminarCategoria() {
        Cuenta cuentaSeleccionada = comboCuentas.getValue();
        Categoria categoriaSeleccionada = tableCategoria.getSelectionModel().getSelectedItem();

        if (cuentaSeleccionada == null || cuentaSeleccionada.getPresupuesto() == null) {
            mostrarAlertaError("Seleccione una cuenta válida.");
            return;
        }

        if (categoriaSeleccionada == null) {
            mostrarAlertaError("Seleccione una categoría para eliminar.");
            return;
        }

        Presupuesto presupuesto = cuentaSeleccionada.getPresupuesto();

        boolean eliminada = presupuesto.eliminarCategoria(categoriaSeleccionada.getIdCategoria());
        if (eliminada) {
            presupuesto.setMontoPresupuestoGastado(
                    presupuesto.getMontoPresupuestoGastado() - categoriaSeleccionada.getSaldo()
            );
            tableCategoria.getItems().setAll(presupuesto.getListaCategorias());
            mostrarAlertaInfo("Categoría eliminada correctamente.");
        } else {
            mostrarAlertaError("No se pudo eliminar la categoría.");
        }
    }

    private void crearCategoria() {
        Cuenta cuentaSeleccionada = comboCuentas.getValue();
        if (cuentaSeleccionada == null || cuentaSeleccionada.getPresupuesto() == null) {
            mostrarAlertaError("Debe seleccionar una cuenta con presupuesto.");
            return;
        }

        String nombre = txtNombreCategoria.getText().trim();
        String saldoStr = txtSaldoCategoria.getText().trim();

        if (nombre.isEmpty() || saldoStr.isEmpty()) {
            mostrarAlertaError("Debe ingresar todos los campos.");
            return;
        }

        try {
            double saldo = Double.parseDouble(saldoStr);
            NombreCategoria nombreCategoria = NombreCategoria.valueOf(nombre.toUpperCase());

            Presupuesto presupuesto = cuentaSeleccionada.getPresupuesto();

            if (!presupuesto.tieneSaldoDisponible(saldo)) {
                mostrarAlertaError("Saldo insuficiente en el presupuesto para esta categoría.");
                return;
            }

            boolean creada = presupuesto.agregarCategoria(nombreCategoria, "CAT-" + System.currentTimeMillis(), saldo);
            if (!creada) {
                mostrarAlertaError("Ya existe una categoría con ese ID.");
                return;
            }

            // Actualizar gasto y tabla
            presupuesto.setMontoPresupuestoGastado(presupuesto.getMontoPresupuestoGastado() + saldo);
            tableCategoria.getItems().setAll(presupuesto.getListaCategorias());
            txtNombreCategoria.clear();
            txtSaldoCategoria.clear();
            mostrarAlertaInfo("Categoría creada correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlertaError("El saldo debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mostrarAlertaError("Nombre de categoría inválido.");
        }
    }

    private void cuentaSeleccionada(){
        Cuenta cuentaSeleccionada = comboCuentas.getValue();
        if (cuentaSeleccionada != null && cuentaSeleccionada.getPresupuesto() != null) {
            tableCategoria.getItems().setAll(cuentaSeleccionada.getPresupuesto().getListaCategorias());
        }
    }

    private Usuario usuarioActual;

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
        cargarCuentas();
    }

    private void cargarCuentas() {
        comboCuentas.getItems().clear();
        for (Cuenta cuenta : usuarioActual.getListaCuentas()) {
            comboCuentas.getItems().add(cuenta);
        }
    }


    @FXML
    void initialize() {
        categoriasUsuarioController = new CategoriasUsuarioController();

        columNombre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreCategoria().name()));

        columSaldo.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getSaldo()).asObject());


    }

    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
