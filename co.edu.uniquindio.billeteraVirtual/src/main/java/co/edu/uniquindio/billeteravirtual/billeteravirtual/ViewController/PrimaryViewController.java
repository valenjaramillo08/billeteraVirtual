package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryViewController {
    HelloApplication helloApp;
    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_usuario;

    @FXML
    private Button btn_admin;

    @FXML
    void onCallUsuario(ActionEvent event) throws IOException {
        this.helloApp.onCallLoginUsuario(event);
    }

    @FXML
    void onCallAdmin(ActionEvent event) throws IOException {
        this.helloApp.onCallLoginAdmin(event);
    }

    @FXML
    void onRegresar(ActionEvent event) throws IOException{
        this.helloApp.onRegresarInicial(event);
    }

    @FXML
    void initialize() {
        assert btn_usuario != null : "fx:id=\"btn_usuario\" was not injected: check your FXML file 'primary.fxml'.";
        assert btn_admin != null : "fx:id=\"btn_admin\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
