package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.LoginUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.HelloApplication;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Proxy.AutenticacionProxy;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUsuarioViewController {

    LoginUsuarioController loginUsuarioController;
    HelloApplication helloApp;
    private AutenticacionProxy autenticacion;


    public void setApp(HelloApplication helloApplication) {
        this.helloApp = helloApplication;
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_iniciar_usuario;

    @FXML
    private Button btn_regresar;

    @FXML
    private PasswordField txt_clave;

    @FXML
    private TextField txt_id_usuario;

    @FXML
    void onIniciarUsuario(ActionEvent event) throws IOException {
        validarCredenciales(event);
    }

    @FXML
    void onCallRegresar(ActionEvent event) throws IOException {
        this.helloApp.showPrimaryView();
    }

    @FXML
    void initialize() {
        loginUsuarioController = new LoginUsuarioController();
        autenticacion = new AutenticacionProxy(loginUsuarioController);

    }
    
    private void validarCredenciales(ActionEvent event) throws IOException {
        String id = txt_id_usuario.getText();
        String clave = txt_clave.getText();

        
    
        boolean valid = autenticacion.autenticar(id,clave);
    
        if (valid) {
            Usuario usuarioLogueado = loginUsuarioController.obtenerUsuario(id);
    
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioVentanaPrincipal.fxml"));
            Parent root = loader.load();
    
            UsuarioVentanaPrincipalViewController controller = loader.getController();
            controller.setApp(helloApp); 
            controller.setUsuarioLogueado(usuarioLogueado); 
    
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    
            ((Stage)((Button) event.getSource()).getScene().getWindow()).close();

            mostrarAlertaInfo(("Bienvenido" + usuarioLogueado.getNombre()));
        } else {
            if(autenticacion.estaBloqueado()){
                mostrarAlertaError("Has excedido los 3 intentos. El acceso ha sido bloqueado.");
            }else{
                mostrarAlertaError("Credenciales incorrectas. Intenta nuevamente.");
            }
        }
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de Autenticación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarAlertaInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Inicio de sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    



}


