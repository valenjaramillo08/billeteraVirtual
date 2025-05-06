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

import javafx.scene.control.Alert.AlertType;


public class GestionTransaccionesViewController {

    GestionTransaccionesController gestionTransaccionesController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> comboPresupuesto;

    @FXML
    private DatePicker fechaTransaccion;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private ComboBox<?> comboCategoria;

    @FXML
    private ChoiceBox<?> comboUsuarioNuevaTransaccion;

    @FXML
    private TextField labelMontoTransaccion;

    @FXML
    void onCrearTransaccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        gestionTransaccionesController = new GestionTransaccionesController();

    }


}

