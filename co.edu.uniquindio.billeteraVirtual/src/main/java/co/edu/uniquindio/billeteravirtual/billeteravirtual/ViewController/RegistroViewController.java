package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.RegistroController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroViewController {
    RegistroController registroController;
    HelloApplication helloApp;
    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField labelCorreo;

    @FXML
    private TextField labelContraseña;

    @FXML
    private TextField labelIdentificacion;

    @FXML
    private TextField labelNombre;

    @FXML
    void onRegistrarse(ActionEvent event) {
        registroUsuario();

    }

    @FXML
    void onRegresar(ActionEvent event) throws IOException {
        this.helloApp.onCallRegresar(event);

    }



    public void registroUsuario(){
        String nombre = labelNombre.getText();
        String correo = labelCorreo.getText();
        String id = labelIdentificacion.getText();
        String clave = labelContraseña.getText();

        if (nombre.isBlank() || correo.isBlank() || id.isBlank() || clave.isBlank()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")){
            mostrarAlerta("El correo no es valido");
            return;
        }

        if (clave.length() <= 6){
            mostrarAlerta("Ingrese una clave valida");
            return;
        }

        boolean exito = registroController.agregarUsuarioRegistro(nombre, correo, id, clave);
        if (exito) {
            mostrarAlerta("¡Registro exitoso!");
            
        } else {
            mostrarAlerta("El usuario ya existe.");
        }
    }



    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    @FXML
    void initialize() {
       registroController = new RegistroController();

    }


}
