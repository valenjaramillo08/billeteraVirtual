package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.tools.Platform;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.perfilUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class perfilUsuarioViewController {

    perfilUsuarioController perfilUsuarioController;
    private Usuario usuarioActual;
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField txtCorreo;

    @FXML
    void onModificarDatos(ActionEvent event) {
        if (usuarioActual == null) {
            System.out.println("Error: usuarioActual es null. ¿Se llamó a inicializarDatos?");
            return;
        }
    
        usuarioActual.setNombre(txtNombre.getText());
        usuarioActual.setApellido(txtApellido.getText());
        usuarioActual.setCorreo(txtCorreo.getText());
    
        System.out.println("Usuario actualizado: " + usuarioActual);
    }
    

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
    
        // ⚠️ Si lo haces antes del initialize o antes de que los TextField estén listos, da error
        if (txtNombre != null && txtApellido != null && txtCorreo != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtCorreo.setText(usuario.getCorreo());
        } else {
            // Si todavía no está todo listo, lo hacemos después
            javafx.application.Platform.runLater(() -> {
                txtNombre.setText(usuario.getNombre());
                txtApellido.setText(usuario.getApellido());
                txtCorreo.setText(usuario.getCorreo());
            });
        }
    }
    
    

    @FXML
    void initialize() {
        perfilUsuarioController = new perfilUsuarioController();
        if (usuarioActual != null) {
            txtNombre.setText(usuarioActual.getNombre());
            txtApellido.setText(usuarioActual.getApellido());
            txtCorreo.setText(usuarioActual.getCorreo());
        }

        

    }

    
}
