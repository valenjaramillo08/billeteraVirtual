package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionUsuariosController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.LoginAdminController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdministradorVentanaPrincipalViewController {
    private HelloApplication helloApp;
    private Administrador administradorLogueado;

    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
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



    @FXML
    private Button btn_cerrar_sesion;

    @FXML
    void onCerrarSesionAdm(ActionEvent event) throws IOException {
        if (helloApp != null) {
            // Mostrar la vista principal (inicial.fxml)
            helloApp.onCallVentanaPrincipalAdm(event);

            // Cerrar la ventana actual (ventana de Usuario)
            ((javafx.stage.Stage) gestionCuentasTab.getTabPane().getScene().getWindow()).close();
        } else {
            System.err.println("Error: helloApp es null. No se pudo cerrar sesión correctamente.");
        }

    }


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


}
