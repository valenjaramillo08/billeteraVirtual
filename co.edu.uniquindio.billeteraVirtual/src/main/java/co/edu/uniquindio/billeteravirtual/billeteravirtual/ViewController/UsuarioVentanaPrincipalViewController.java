package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;


import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.TransaccionViewController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.CategoriasUsuarioViewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class UsuarioVentanaPrincipalViewController {
    private HelloApplication helloApp;
    private Usuario usuarioLogueado;

    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
        

    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        cargarVistaPerfil();

        cargarVistaTransacciones();
        cargarVistaCategorias();
        cargarVistaCuentas();
        cargarVistaReportes();
    }


  


    @FXML
    private Tab categoriasTab;
    @FXML
    private Tab cuentasTab;

    @FXML
    private Tab perfilTab;

    @FXML
    private Tab transaccionTab;

    @FXML
    private Tab reportesTab;

    @FXML
    void OnCerrarSesionUsuario(ActionEvent event)  throws IOException {
        if (helloApp != null) {
            
            helloApp.onCallVentanaPrincipal(event);

            
            ((javafx.stage.Stage) perfilTab.getTabPane().getScene().getWindow()).close();
        } else {
            System.err.println("Error: helloApp es null. No se pudo cerrar sesión correctamente.");
        }
    }

      private void cargarVistaReportes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/reportes.fxml"));
            Parent vistaReportes = loader.load();

            ReportesViewController controller = loader.getController();
            controller.setUsuarioActual(usuarioLogueado); 

            reportesTab.setContent(vistaReportes); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    



    private void cargarVistaPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/perfilUsuario.fxml"));
            AnchorPane vistaPerfil = loader.load();
    
            perfilUsuarioViewController controller = loader.getController();
            controller.setUsuarioLogueado(usuarioLogueado);  

    
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
            controller.setUsuarioLogueado(usuarioLogueado); 

            transaccionTab.setContent(vistaTransacciones); 
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
        controller.setUsuarioLogueado(usuarioLogueado);  
        System.out.println("Usuario actual: " + usuarioLogueado);
        cuentasTab.setContent(vistaCuentas);  

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void cargarVistaCategorias() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/categoriasUsuario.fxml"));
            AnchorPane vistaCategorias = loader.load();

            CategoriasUsuarioViewController controller = loader.getController();
            controller.setUsuarioLogueado(usuarioLogueado); 

            categoriasTab.setContent(vistaCategorias); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



