package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import org.controlsfx.tools.Platform;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.perfilUsuarioController;
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
            mostrarAlertaError("Error interno: usuario no encontrado.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();

        // Validaciones
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            mostrarAlertaError("Todos los campos son obligatorios.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarAlertaError("El correo debe contener '@' y '.'");
            return;
        }

        // Si pasa todas las validaciones, actualizar
        usuarioActual.setNombre(nombre);
        usuarioActual.setApellido(apellido);
        usuarioActual.setCorreo(correo);

        // Puedes guardar en base de datos aquí si lo necesitas

        mostrarAlertaInfo("Datos actualizados correctamente.");
        System.out.println("Usuario actualizado: " + usuarioActual);
    }
    

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioActual = usuario;
    

        if (txtNombre != null && txtApellido != null && txtCorreo != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtCorreo.setText(usuario.getCorreo());
        } else {
            javafx.application.Platform.runLater(() -> {
                txtNombre.setText(usuario.getNombre());
                txtApellido.setText(usuario.getApellido());
                txtCorreo.setText(usuario.getCorreo());
            });
        }
    }

    private void mostrarAlertaInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
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
