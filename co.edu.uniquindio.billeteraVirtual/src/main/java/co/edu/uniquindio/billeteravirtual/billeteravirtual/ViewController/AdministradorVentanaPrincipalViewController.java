package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionUsuariosController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.LoginAdminController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdministradorVentanaPrincipalViewController {
    public void setApp(HelloApplication helloApplication, Administrador administrador) {
    }

    @FXML
    private Tab gestionCuentasTab;

    @FXML
    private Tab mostrarEstadisticasTab;

    @FXML
    private Tab gestionTransaccionesTab;

    @FXML
    private Tab gestionUsuariosTab;

    @FXML private AnchorPane placeholder; // un <AnchorPane fx:id="placeholder"/> en tu FXML

    private Administrador administradorLogueado;


    public void setAdministradorLogueado(Administrador administrador) throws IOException {

        this.administradorLogueado = administrador;
        cargarVistaPerfil();

    }

    private void cargarVistaPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/adminFolder/gestionUsuarios.fxml"));
            AnchorPane vistaPerfil = loader.load();

            GestionUsuariosViewController controller = loader.getController();

            gestionUsuariosTab.setContent(vistaPerfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCallRegresar(ActionEvent actionEvent) {
    }
}
