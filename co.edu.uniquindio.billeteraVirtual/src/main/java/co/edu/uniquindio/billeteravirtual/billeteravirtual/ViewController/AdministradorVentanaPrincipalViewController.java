package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.LoginAdminController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdministradorVentanaPrincipalViewController {
    LoginAdminController loginAdminController;
    HelloApplication helloApp;
    Administrador administrador;

    @FXML private AnchorPane placeholder; // un <AnchorPane fx:id="placeholder"/> en tu FXML
    private GestionCuentasViewController childCtrl;

    public void setApp(HelloApplication app,
                       Administrador admin,
                       URL rutaGestionCuentas) throws IOException {

        this.helloApp     = app;
        this.administrador = admin;

        FXMLLoader loader = new FXMLLoader(rutaGestionCuentas);
        AnchorPane root   = loader.load();

        childCtrl = loader.getController();
        childCtrl.initData(administrador);

        placeholder.getChildren().setAll(root);
    }

    @FXML
    public void onCallRegresar(ActionEvent actionEvent) {
    }
}
