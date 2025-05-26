package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.CategoriasUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.Observador;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriasUsuarioViewController implements Observador {

    private Usuario usuarioActual;
    private CategoriasUsuarioController categoriasUsuarioController;

    @FXML
    private ComboBox<Cuenta> comboCuentas;
    @FXML
    private ComboBox<NombreCategoria> comboNombreCategoria;
    @FXML
    private TableView<Categoria> tableCategoria;
    @FXML
    private TableColumn<Categoria, String> columNombre;
    @FXML
    private TableColumn<Categoria, Double> columSaldo;
    @FXML
    private TextField txtSaldoCategoria;
    @FXML
    private TextField txtPresupuesto;

    @FXML
    void onCrearCategoria(ActionEvent event) {
        Cuenta cuenta = comboCuentas.getValue();
        if (cuenta == null || cuenta.getPresupuesto() == null) {
            mostrarAlertaError("Seleccione una cuenta con presupuesto.");
            return;
        }

        String saldoStr = txtSaldoCategoria.getText().trim();
        NombreCategoria nombreCategoria = comboNombreCategoria.getValue();

        if (saldoStr.isEmpty() || nombreCategoria == null) {
            mostrarAlertaError("Debe ingresar todos los campos.");
            return;
        }

        try {
            double saldo = Double.parseDouble(saldoStr);
            Presupuesto presupuesto = cuenta.getPresupuesto();

            double disponible = presupuesto.getMontoPresupuesto() - presupuesto.getMontoPresupuestoGastado();
            if (saldo > disponible) {
                mostrarAlertaError("Saldo insuficiente en el presupuesto. Disponible: " + disponible);
                return;
            }

            boolean creada = presupuesto.agregarCategoria(nombreCategoria, "CAT-" + System.currentTimeMillis(), saldo);
            if (!creada) {
                mostrarAlertaError("Ya existe una categoría con ese nombre.");
                return;
            }

            mostrarAlertaInfo("Categoría creada correctamente.");
            comboNombreCategoria.getSelectionModel().clearSelection();
            txtSaldoCategoria.clear();
        } catch (NumberFormatException e) {
            mostrarAlertaError("El saldo debe ser un número válido.");
        }
    }

    @FXML
    void onModificarCategoria(ActionEvent event) {
        Cuenta cuenta = comboCuentas.getValue();
        Categoria categoria = tableCategoria.getSelectionModel().getSelectedItem();
        NombreCategoria nuevoNombre = comboNombreCategoria.getValue();

        if (cuenta == null || cuenta.getPresupuesto() == null || categoria == null || nuevoNombre == null) {
            mostrarAlertaError("Debe seleccionar una cuenta, una categoría y un nombre nuevo.");
            return;
        }

        try {
            double nuevoSaldo = Double.parseDouble(txtSaldoCategoria.getText().trim());
            Presupuesto presupuesto = cuenta.getPresupuesto();

            double saldoActual = categoria.getSaldo();
            double diferencia = nuevoSaldo - saldoActual;

            if (diferencia > 0) {
                double disponible = presupuesto.getMontoPresupuesto() - presupuesto.getMontoPresupuestoGastado();
                if (diferencia > disponible) {
                    mostrarAlertaError("Saldo insuficiente para aumentar la categoría.");
                    return;
                }
                presupuesto.setMontoPresupuestoGastado(presupuesto.getMontoPresupuestoGastado() + diferencia);
            } else {
                presupuesto.setMontoPresupuestoGastado(presupuesto.getMontoPresupuestoGastado() + diferencia);
            }

            categoria.setSaldo(nuevoSaldo);
            categoria.setNombreCategoria(nuevoNombre);

            tableCategoria.refresh();
            presupuesto.notificarObservers(); 
            mostrarAlertaInfo("Categoría modificada.");
        } catch (NumberFormatException e) {
            mostrarAlertaError("El saldo debe ser un número válido.");
        }
    }



    @FXML
    void onEliminarCategoria(ActionEvent event) {
        Cuenta cuenta = comboCuentas.getValue();
        Categoria categoria = tableCategoria.getSelectionModel().getSelectedItem();
        if (cuenta == null || categoria == null || cuenta.getPresupuesto() == null) {
            mostrarAlertaError("Debe seleccionar una cuenta y una categoría.");
            return;
        }

        Presupuesto presupuesto = cuenta.getPresupuesto();
        boolean eliminada = presupuesto.eliminarCategoria(categoria.getIdCategoria());
        if (eliminada) {
            presupuesto.setMontoPresupuestoGastado(presupuesto.getMontoPresupuestoGastado() - categoria.getSaldo());
            tableCategoria.getItems().setAll(presupuesto.getListaCategorias());
            mostrarAlertaInfo("Categoría eliminada.");
        } else {
            mostrarAlertaError("No se pudo eliminar la categoría.");
        }
    }

    @FXML
    void onComboCuenta(ActionEvent event) {
        Cuenta cuenta = comboCuentas.getValue();
        if (cuenta != null && cuenta.getPresupuesto() != null) {
            Presupuesto presupuesto = cuenta.getPresupuesto();
            presupuesto.agregarObserver(this);

            double disponible = presupuesto.getMontoPresupuesto() - presupuesto.getMontoPresupuestoGastado();
            txtPresupuesto.setText(String.valueOf(disponible));

            tableCategoria.getItems().setAll(presupuesto.getListaCategorias());
        } else {
            tableCategoria.getItems().clear();
            txtPresupuesto.clear();
        }
    }



    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
        cargarCuentas();


    }

    private void cargarCuentas() {
        comboCuentas.getItems().clear();
        if (usuarioActual != null && usuarioActual.getListaCuentas() != null) {
            comboCuentas.getItems().addAll(usuarioActual.getListaCuentas());
        }
        comboCuentas.getSelectionModel().clearSelection();
    }

    @Override
    public void actualizar(Presupuesto presupuesto) {
        Cuenta cuenta = comboCuentas.getValue();
        if (cuenta != null && cuenta.getPresupuesto() == presupuesto) {
            tableCategoria.getItems().setAll(presupuesto.getListaCategorias());
            tableCategoria.refresh();
            double disponible = presupuesto.getMontoPresupuesto() - presupuesto.getMontoPresupuestoGastado();
            txtPresupuesto.setText(String.valueOf(disponible));
        }
    }

    @FXML
    void initialize() {
        categoriasUsuarioController = new CategoriasUsuarioController();

        columNombre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreCategoria().name()));
        columSaldo.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getSaldo()).asObject());

        comboNombreCategoria.getItems().setAll(NombreCategoria.values());
        txtPresupuesto.setEditable(false);
        txtPresupuesto.setFocusTraversable(false);

        tableCategoria.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtSaldoCategoria.setText(String.valueOf(newVal.getSaldo()));
                comboNombreCategoria.setValue(newVal.getNombreCategoria());
            }
        });
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