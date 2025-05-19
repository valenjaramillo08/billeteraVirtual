package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller.GestionUsuariosController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Mapping.Dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.ObservadorAdministrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.UsuarioObservable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.AdministradorConstantes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionUsuariosViewController implements ObservadorAdministrador {
    Administrador admin;
    GestionUsuariosController gestionUsuariosController;
    ObservableList<UsuarioDto> listaUsuarios = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<UsuarioDto,String> columNombre;
    @FXML private TableColumn<UsuarioDto, String> columCorreo;
    @FXML private TextField txtNombre;
    @FXML private TableColumn<UsuarioDto, String> columApellido;
    @FXML private TableColumn<UsuarioDto, String> columIdentificacion;
    @FXML private Button btnNuevo;
    @FXML private TextField txtApellido;
    @FXML private TableView<UsuarioDto> tableUsuarios;
    @FXML private Button btnAgregar;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtCorreo;
    @FXML private Button btnActualizar;
    @FXML private TextField txtContrasena;

    @FXML
    public void onAgregar(javafx.event.ActionEvent actionEvent) {
        agregarUsuario();
    }

    private void agregarUsuario() {
        UsuarioDto usuarioDto = crearUsuario();

        if(datosValidos(usuarioDto)){
            if(gestionUsuariosController.agregarUsuario(usuarioDto)){
                listaUsuarios.add(usuarioDto);
                UsuarioObservable.notificarObservadores(); // 🔔 Notificar cambios
                mostrarMensaje(AdministradorConstantes.TITULO_USUARIO_AGREGADO,AdministradorConstantes.HEADER,AdministradorConstantes.BODY_USUARIO_AGREGADO, Alert.AlertType.INFORMATION);
            }
        }else {
            mostrarMensaje(AdministradorConstantes.TITULO_INCOMPLETO,AdministradorConstantes.HEADER,AdministradorConstantes.BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        return !(usuarioDto.nombre().isEmpty() ||
                usuarioDto.apellido().isEmpty() ||
                usuarioDto.idUsuario().isEmpty() ||
                usuarioDto.correo().isEmpty());
    }

    @FXML
    public void onActualizar(javafx.event.ActionEvent actionEvent) {
        actualizarUsuario();
    }

    public void actualizarUsuario() {
        if (usuarioSeleccionado != null) {
            UsuarioDto usuarioActualizado = crearUsuario();

            if (datosValidos(usuarioActualizado)) {
                boolean actualizado = gestionUsuariosController.actualizarUsuario(usuarioSeleccionado.idUsuario(), usuarioActualizado);

                if (actualizado) {
                    int index = listaUsuarios.indexOf(usuarioSeleccionado);
                    listaUsuarios.set(index, usuarioActualizado);
                    tableUsuarios.refresh();
                    UsuarioObservable.notificarObservadores();
                    mostrarMensaje("Éxito", "Usuario actualizado", "El usuario fue actualizado correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCampos();
                } else {
                    mostrarMensaje("Error", "No se pudo actualizar", "Hubo un problema actualizando el usuario", Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Datos incompletos", "Verifica los campos", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje("Selección requerida", "No se seleccionó ningún usuario", "Por favor selecciona un usuario de la tabla", Alert.AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtIdentificacion.clear();
        txtCorreo.clear();
        txtContrasena.clear();
        usuarioSeleccionado = null;
        tableUsuarios.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        gestionUsuariosController = new GestionUsuariosController();
        UsuarioObservable.agregarObservador(this); // 🔁 Registro como observador
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaUsuarios);
        listenerSelection();
    }

    private void initDataBinding() {
        columNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        columApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        columIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idUsuario()));
        columCorreo.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().correo()));
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(gestionUsuariosController.obtenerUsuarios());
    }

    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }

    private UsuarioDto crearUsuario() {
        return new UsuarioDto(
                txtNombre.getText(),
                txtApellido.getText(),
                txtIdentificacion.getText(),
                txtCorreo.getText(),
                txtContrasena.getText()
        );
    }

    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtNombre.setText(usuarioSeleccionado.nombre());
            txtApellido.setText(usuarioSeleccionado.apellido());
            txtIdentificacion.setText(usuarioSeleccionado.idUsuario());
            txtCorreo.setText(usuarioSeleccionado.correo());
        }
    }

    @FXML
    void onEliminar(javafx.event.ActionEvent actionEvent) {
        eliminarUsuario();
    }

    private void eliminarUsuario() {
        if (usuarioSeleccionado != null) {
            boolean eliminado = gestionUsuariosController.eliminarUsuario(usuarioSeleccionado);

            if (eliminado) {
                listaUsuarios.remove(usuarioSeleccionado);
                UsuarioObservable.notificarObservadores();
                tableUsuarios.refresh();
                mostrarMensaje("Éxito", "Usuario eliminado", "El usuario fue eliminado correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar", "Hubo un problema eliminando el usuario", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Selección requerida", "No se seleccionó ningún usuario", "Por favor selecciona un usuario de la tabla", Alert.AlertType.WARNING);
        }
    }

    public void onNuevo(javafx.event.ActionEvent actionEvent) {
        limpiarCampos();
    }


    @Override
    public void actualizarUsuarios() {
        listaUsuarios.clear();
        listaUsuarios.addAll(gestionUsuariosController.obtenerUsuarios());
        tableUsuarios.refresh();

    }
}
