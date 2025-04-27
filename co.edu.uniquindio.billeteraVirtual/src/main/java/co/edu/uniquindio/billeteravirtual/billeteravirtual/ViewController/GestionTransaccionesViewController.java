package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade.TransaccionFacade;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class GestionTransaccionesViewController {

    GestionTransaccionesController gestionTransaccionesController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;


    @FXML
    private TableColumn<Transaccion, String> columTipoTransaccion;

    @FXML
    private DatePicker dateDesde;

    @FXML
    private TableColumn<Usuario, String> columIdUsuario;

    @FXML
    private TableColumn<Transaccion, Double> columMonto;

    @FXML
    private DatePicker fechaTransaccion;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private ChoiceBox<Usuario> comboUsuarioNuevaTransaccion;

    @FXML
    private DatePicker dateHasta;

    @FXML
    private TextField labelTipoTransaccion;

    @FXML
    private TableColumn<Transaccion, String> columTransaccionFecha;

    @FXML
    private ChoiceBox<Usuario> comboUsuarioLista;

    @FXML
    private TextField labelMontoTransaccion;

    @FXML
    void onCrearTransaccion(ActionEvent event) {
        crearTransaccion();

    }

    @FXML
    void onListarTransacciones(ActionEvent event) {

    }

    private void cargarTiposTransaccion() {
        ObservableList<String> tipos = FXCollections.observableArrayList(
                "deposito", "retiro"
        );
        choiceBox.setItems(tipos);
    }


    private void cargarUsuarios() {
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList(
                gestionTransaccionesController.obtenerListaUsuarios()
        );
        comboUsuarioNuevaTransaccion.setItems(listaUsuarios);

        comboUsuarioNuevaTransaccion.setConverter(new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario usuario) {
                if (usuario != null) {
                    return usuario.getNombre(); // o puedes mostrar el correo o el ID
                }
                return "";
            }

            @Override
            public Usuario fromString(String s) {
                return null; // No es necesario implementarlo en ChoiceBox
            }
        });
    }

    private void crearTransaccion() {
        try {
            Usuario usuarioSeleccionado = comboUsuarioNuevaTransaccion.getValue();
            String tipo = choiceBox.getValue(); // 👈 corregido aquí
            double monto = Double.parseDouble(labelMontoTransaccion.getText());
            String descripcion = txtAreaDescripcion.getText();
            LocalDate fecha = fechaTransaccion.getValue();

            if (usuarioSeleccionado == null || tipo == null || descripcion.isEmpty() || fecha == null) {
                mostrarAlerta("Debes completar todos los campos, incluyendo el tipo de transacción.", Alert.AlertType.WARNING);
                return;
            }

            Transaccion nuevaTransaccion = gestionTransaccionesController.crearTransaccionConFecha(
                    tipo, monto, descripcion, fecha, usuarioSeleccionado
            );

            mostrarAlerta("¡Transacción creada exitosamente!", Alert.AlertType.INFORMATION);
            ///actualizarTabla(usuarioSeleccionado);

        } catch (Exception e) {
            mostrarAlerta("Error creando transacción: " + e.getMessage(), Alert.AlertType.ERROR);
        }

        }


        private void mostrarAlerta (String mensaje, Alert.AlertType tipo){
            Alert alerta = new Alert(tipo);
            alerta.setTitle("Resultado de la Operación");
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();
        }


        @FXML
        void initialize () {
            gestionTransaccionesController = new GestionTransaccionesController();
            cargarUsuarios();
            cargarTiposTransaccion();
        }
}

