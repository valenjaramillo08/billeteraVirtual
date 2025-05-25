package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.TransaccionConNotificacion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.TransaccionD;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.DatosTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.FabricaTransacciones;
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
    @FXML
    private ComboBox<NombreCategoria> comboPlata;

    private ObservableList<Cuenta> cuentasUsuario;
    private ObservableList<Cuenta> cuentas;
    private ObservableList<Transaccion> transacciones;
    private GestionTransaccionesController controller;
    private Usuario usuarioActual;

    @FXML
    void onCrearTransaccion(ActionEvent event) {
        try {
        String id = UUID.randomUUID().toString();
        LocalDate fecha = LocalDate.now();
        double monto = Double.parseDouble(txtMonto.getText());
        TipoTransaccion tipo = cbTipo.getValue();
        Cuenta origen = cbCuentaOrigen.getValue();
        Cuenta destino = cbCuentaDestino.getValue();
        NombreCategoria nombreCategoria = comboPlata.getValue();

        if (tipo == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe seleccionar un tipo de transacción.");
            return;
        }

        Transaccion transaccion = null;
        Presupuesto presupuesto = null;

        if (tipo == TipoTransaccion.RETIRO || tipo == TipoTransaccion.TRANSFERENCIA) {
            if (origen == null || nombreCategoria == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe seleccionar la cuenta origen y la categoría.");
                return;
            }
              presupuesto /*presupuesto*/ = origen.getPresupuesto();
            if (presupuesto == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Sin presupuesto", "La cuenta seleccionada no tiene un presupuesto.");
                return;
            }

            Categoria categoria = presupuesto.obtenerCategoriaPorNombre(nombreCategoria);
            if (categoria == null || monto > categoria.getSaldo()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Saldo insuficiente en la categoría",
                        "No puedes retirar más de lo que tienes en esta categoría.");
                return;
            }

            DatosTransaccion datos = new DatosTransaccion(
                    id, origen, fecha, monto, "Generada por usuario", destino,
                    tipo, presupuesto
            );

            /*Transaccion*/ transaccion = FabricaTransacciones.crear(datos);
            transaccion.setPresupuesto(presupuesto);
            transaccion.setCategoriaProcesada(nombreCategoria);
            // --- DECORATOR ---
           
            TransaccionD t = new Transaccion(transaccion);

            
            t = new TransaccionConNotificacion(transaccion); 

            
            t.ejecutar();
            transacciones.add(transaccion); 
            usuarioActual.getListaTransacciones().add(transaccion);
            // -----------------

           
            if (tipo == TipoTransaccion.TRANSFERENCIA && destino != null && destino.getPresupuesto() != null) {
                Presupuesto destinoPresupuesto = destino.getPresupuesto();
                destinoPresupuesto.setMontoPresupuesto(destinoPresupuesto.getMontoPresupuesto() + monto);
                destinoPresupuesto.notificarObservers();
            }

        } else if (tipo == TipoTransaccion.DEPOSITO) {
            if (destino == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe seleccionar la cuenta destino.");
                return;
            }

            /*Presupuesto*/ presupuesto = destino.getPresupuesto();
             if (presupuesto == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Sin presupuesto", "La cuenta destino no tiene un presupuesto asociado.");
                return;
            }

            DatosTransaccion datos = new DatosTransaccion(
                    id, null, fecha, monto, "Generada por usuario", destino,
                    tipo, presupuesto
            );

            /*Transaccion*/ transaccion = FabricaTransacciones.crear(datos);
            transaccion.setPresupuesto(presupuesto);

            // --- DECORATOR SOLO NOTIFICACIÓN ---
            TransaccionD t = new Transaccion(transaccion);
            t = new TransaccionConNotificacion(transaccion);
            t.ejecutar();

            // -----------------------------------

            
            presupuesto.setMontoPresupuesto(presupuesto.getMontoPresupuesto() + monto);
            presupuesto.notificarObservers();

            transacciones.add(transaccion);
            usuarioActual.getListaTransacciones().add(transaccion);
        }

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Transacción registrada correctamente.");
        limpiarCampos();
    } catch (Exception e) {
        mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos inválidos o incompletos: " + e.getMessage());
    }
    }

    private void limpiarCampos() {
        txtMonto.clear();
        cbTipo.setValue(null);
        cbCuentaOrigen.setValue(null);
        cbCuentaDestino.setValue(null);
        txtSaldoActual.clear();
        comboPlata.setValue(null);
        comboPlata.setItems(FXCollections.observableArrayList());
        comboPlata.setDisable(true);

        // Restaurar visibilidad
        cbCuentaOrigen.setDisable(true);
        cbCuentaDestino.setDisable(true);
    }


    @FXML
    public void initialize() {
        controller = new GestionTransaccionesController();
        transacciones = FXCollections.observableArrayList();
        configurarTabla();

        cbTipo.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));
        cbTipo.setOnAction(this::onTipoSeleccionado);
        cbCuentaOrigen.setOnAction(this::onCuentaOrigenSeleccionada);
        comboPlata.setOnAction(this::onComboPlata);

        txtSaldoActual.setEditable(false);
        txtSaldoActual.setFocusTraversable(false);
        tablaTransacciones.setItems(transacciones);
    }

    @FXML
    private void onTipoSeleccionado(ActionEvent e) {
        TipoTransaccion tipo = cbTipo.getValue();

        cbCuentaOrigen.setDisable(true);
        cbCuentaDestino.setDisable(true);
        cbCuentaOrigen.setVisible(true);
        cbCuentaDestino.setVisible(true);

        comboPlata.setDisable(true); // Por defecto deshabilitado

        if (tipo == TipoTransaccion.RETIRO) {
            cbCuentaOrigen.setDisable(false);
            comboPlata.setDisable(false); // Habilitado en RETIRO
        } else if (tipo == TipoTransaccion.DEPOSITO) {
            cbCuentaDestino.setDisable(false);
            comboPlata.setDisable(true); // Deshabilitado en DEPÓSITO
        } else if (tipo == TipoTransaccion.TRANSFERENCIA) {
            cbCuentaOrigen.setDisable(false);
            cbCuentaDestino.setDisable(false);
            comboPlata.setDisable(false); // Habilitado en TRANSFERENCIA
            filtrarCuentasDestino();
        }
    }


    @FXML
    private void onCuentaOrigenSeleccionada(ActionEvent e) {
        filtrarCuentasDestino();
        Cuenta cuentaSeleccionada = cbCuentaOrigen.getValue();
        if (cuentaSeleccionada != null && cuentaSeleccionada.getPresupuesto() != null) {
            Presupuesto presupuesto = cuentaSeleccionada.getPresupuesto();
            ObservableList<NombreCategoria> categoriasConSaldo = FXCollections.observableArrayList();
            for (Categoria cat : presupuesto.getListaCategorias()) {
                if (cat.getSaldo() > 0) {
                    categoriasConSaldo.add(cat.getNombreCategoria());
                }
            }
            comboPlata.setItems(categoriasConSaldo);
        } else {
            comboPlata.setItems(FXCollections.observableArrayList());
        }
        txtSaldoActual.clear();
    }

    @FXML
    private void onComboPlata(ActionEvent e) {
        Cuenta cuenta = cbCuentaOrigen.getValue();
        NombreCategoria nombreCategoria = comboPlata.getValue();
        if (cuenta != null && nombreCategoria != null && cuenta.getPresupuesto() != null) {
            for (Categoria cat : cuenta.getPresupuesto().getListaCategorias()) {
                if (cat.getNombreCategoria() == nombreCategoria) {
                    txtSaldoActual.setText(String.valueOf(cat.getSaldo()));
                    break;
                }
            }
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

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuarioActual != null) {
            cuentasUsuario = FXCollections.observableArrayList(controller.listarCuentasUsuario(usuario));
            cuentas = FXCollections.observableArrayList(controller.listarCuentas());
            cbCuentaOrigen.setItems(cuentasUsuario);
            cbCuentaDestino.setItems(cuentas);
            /*transacciones.addAll(controller.listarTransacciones(usuario));*/
            
            transacciones.clear();
            transacciones.addAll(usuarioActual.getListaTransacciones());
            tablaTransacciones.setItems(transacciones);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void actualizarSaldoActual(String saldo) {
        txtSaldoActual.setText(saldo);
    }
}
