package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.LoginAdminController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdministradorVentanaPrincipalViewController {
    LoginAdminController loginAdminController;
    HelloApplication helloApp;
    Administrador administrador;


    public void setApp(HelloApplication helloApplication, Administrador administrador) {
        this.helloApp = helloApplication;
        administrador = administrador;
    }


    public void onTest(ActionEvent actionEvent) {
    }

    public void onCallRegresar(ActionEvent actionEvent) throws IOException {
        this.helloApp.onCallLoginAdmin(actionEvent);
    }
}
