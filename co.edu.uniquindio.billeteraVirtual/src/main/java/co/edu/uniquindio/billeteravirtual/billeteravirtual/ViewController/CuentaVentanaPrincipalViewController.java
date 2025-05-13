package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas.GestionarCuentaViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class CuentaVentanaPrincipalViewController {

     private Usuario usuarioLogueado;
     private Usuario usuarioActual;


      public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
      
    }

    public void setUsuarioActual(Usuario usuario) {
    this.usuarioActual = usuario;
    }


    @FXML
    private Tab GestionarCuentasTab;

    @FXML
    private Tab ConsultarTransaccionesTab;

    @FXML
    private Tab ConsultarSaldoTab;

    @FXML
    private void cargarVistaGestionCuentas() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/cuentas/gestionarCuentas.fxml"));
        TabPane vistaCuentasGestion = loader.load();

        GestionarCuentaViewController controller = loader.getController();
        controller.setUsuarioActual(usuarioLogueado);  // ¡Este método pasa el usuario correctamente!

        GestionarCuentasTab.setContent(vistaCuentasGestion);  // o donde quieras mostrar la vista

    } catch (IOException e) {
        e.printStackTrace();
    }
}



   
}

