package co.edu.uniquindio.billeteravirtual.billeteravirtual;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Billetera;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.DataUtil;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class HelloApplication extends Application {

    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Billetera Virtual");

        // 2. Mostrar la vista principal
        showPrimaryView();
    }
    public void showPrimaryView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Pane root = loader.load();

        PrimaryViewController controller = loader.getController();
        controller.setApp(this);  // inyecta la referencia a la app

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void onCallLoginAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdministrador.fxml"));
        Pane root = loader.load();

        LoginAdminViewController controller = loader.getController();
        controller.setApp(this);  // inyecta la referencia a la app

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void onCallLoginUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginUsuario.fxml"));
        Pane root = loader.load();

        LoginUsuarioViewController controller = loader.getController();
        controller.setApp(this);  // inyecta la referencia a la app

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onCallUsuarioVentanaPrincipal(ActionEvent event, Usuario usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("usuarioVentanaPrincipal.fxml"));
        Pane root = loader.load();

        UsuarioVentanaPrincipalViewController controller = loader.getController();
        controller.setApp(this, usuario);  // inyecta la referencia a la app

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onCallAdministradorVentanaPrincipal(ActionEvent event, Administrador administrador) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("administradorVentanaPrincipal.fxml"));
        Pane root = loader.load();

        AdministradorVentanaPrincipalViewController controller = loader.getController();
        controller.setApp(this, administrador);  // inyecta la referencia a la app

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


