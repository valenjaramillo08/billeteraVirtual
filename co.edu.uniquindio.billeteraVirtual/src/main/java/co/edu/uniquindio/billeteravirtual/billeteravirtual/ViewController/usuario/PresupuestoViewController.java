package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.ObserverUsuario.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PresupuestoViewController implements Observer{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNomPresupuesto;

    @FXML
    private Button btNuevo;

    @FXML
    private TextField txtMontoPre;

    @FXML
    private TextField txtMontoPreGastado;

    @FXML
    private TextField txtIdPresupuesto;

    @FXML
    private Button btEditar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btActualizar;

    @FXML
    void initialize() {
       
    }


    @Override
    public void actualizar(double saldoActual) {
      
    }

   
}

