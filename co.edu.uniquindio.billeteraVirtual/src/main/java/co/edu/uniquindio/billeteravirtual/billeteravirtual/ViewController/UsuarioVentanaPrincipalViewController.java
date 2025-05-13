package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.perfilUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas.GestionarCuentaViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

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
        cargarVistaCuentas();
    }

    @FXML
    private Tab cuentasTab;

    @FXML
    private Tab perfilTab;

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









@FXML
private void cargarVistaCuentas() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/cuentas/cuenta.fxml"));
        TabPane vistaCuentas = loader.load();

        CuentaVentanaPrincipalViewController controller = loader.getController();
        controller.setUsuarioActual(usuarioLogueado);  // ¡Este método pasa el usuario correctamente!
        System.out.println("Usuario actual: " + usuarioLogueado);
        cuentasTab.setContent(vistaCuentas);  // o donde quieras mostrar la vista

    } catch (IOException e) {
        e.printStackTrace();
    }
}






}



