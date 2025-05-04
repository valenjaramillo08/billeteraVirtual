package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.perfilUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class UsuarioVentanaPrincipalViewController {
    HelloApplication helloApp;

    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
        // ✅ Asegúrate de esta línea

    }

    private Usuario usuarioLogueado;

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        cargarVistaPerfil();
    }
    @FXML
    private Tab perfilTab;

    @FXML
    void OnCerrarSesionUsuario(ActionEvent event)  throws IOException {
        this.helloApp.onCallVentanaPrincipal(event);

    }



    private void cargarVistaPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/perfilUsuario.fxml"));
            AnchorPane vistaPerfil = loader.load();
    
            perfilUsuarioViewController controller = loader.getController();
            controller.setUsuarioLogueado(usuarioLogueado);  // Asegúrate de que este método exista
    
            perfilTab.setContent(vistaPerfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}



