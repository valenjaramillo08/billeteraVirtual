package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicialViewController {
        HelloApplication helloApp;
        public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
    }

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        void onIniciarSesion(ActionEvent event) throws IOException {
            this.helloApp.onCallPrimary(event);

        }

        @FXML
        void onRegistrarse(ActionEvent event) throws IOException {
            this.helloApp.onCallRegistro(event);

        }

        @FXML
        void initialize() {


        }
    }


