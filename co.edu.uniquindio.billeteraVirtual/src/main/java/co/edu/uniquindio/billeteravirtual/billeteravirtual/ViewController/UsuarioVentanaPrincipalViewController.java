package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;


import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.TransaccionViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class UsuarioVentanaPrincipalViewController {
    private HelloApplication helloApp;
    private Usuario usuarioLogueado;

    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
        // ✅ Asegúrate de esta línea

    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        cargarVistaPerfil();
        cargarVistaTransacciones();
    }


    @FXML
    private Tab perfilTab;

    @FXML
    private Tab transaccionTab;

    @FXML
    void OnCerrarSesionUsuario(ActionEvent event)  throws IOException {
        if (helloApp != null) {
            // Mostrar la vista principal (inicial.fxml)
            helloApp.onCallVentanaPrincipal(event);

            // Cerrar la ventana actual (ventana de Usuario)
            ((javafx.stage.Stage) perfilTab.getTabPane().getScene().getWindow()).close();
        } else {
            System.err.println("Error: helloApp es null. No se pudo cerrar sesión correctamente.");
        }
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

    private void cargarVistaTransacciones() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/transaccion/transaccion.fxml"));
            Pane vistaTransacciones = loader.load();

            TransaccionViewController controller = loader.getController();
            controller.setUsuarioLogueado(usuarioLogueado);  // ✅ Aquí se pasa el usuario

            transaccionTab.setContent(vistaTransacciones); // Reemplazas el contenido del Tab
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



