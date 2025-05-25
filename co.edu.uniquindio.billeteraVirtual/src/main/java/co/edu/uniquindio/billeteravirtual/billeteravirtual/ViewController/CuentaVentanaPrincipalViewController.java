package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador.PresupuestoObservable;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas.ConsulTransaccionViewController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas.ConsultarSaldoViewController;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario.cuentas.GestionarCuentaViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class CuentaVentanaPrincipalViewController {

    private final PresupuestoObservable presupuestoObservable = new PresupuestoObservable();

     private Usuario usuarioLogueado;
     private Usuario usuarioActual;
     

    @FXML
    private Tab GestionarCuentasTab;

    @FXML
    private Tab ConsultarTransaccionesTab;

    @FXML
    private Tab ConsultarSaldoTab;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane tabPane;

    @FXML
     void initialize() {
      
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        cargarVistaGestionCuentas();
        cargarVistaSaldo();
        cargarVistaConsulTransaccion();

      
    }

    

    private void cargarVistaConsulTransaccion() {
        if (usuarioLogueado == null) {
            System.err.println("[Error] usuarioLogueado es null al cargar la vista consultar transacciones.");
           
            new Alert(Alert.AlertType.ERROR, "El usuario no está logueado. Por favor, inicie sesión nuevamente.").showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/cuentas/consulTransaccion.fxml"));
            Parent vistaCuentasGestion = loader.load();

            ConsulTransaccionViewController controller = loader.getController();
            controller.setUsuarioLogueado(usuarioLogueado);  

            ConsultarTransaccionesTab.setContent(vistaCuentasGestion);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error al cargar la vista de consultar transacciones: " + e.getMessage()).showAndWait();
        }
    }

    private void cargarVistaSaldo() {
		 if (usuarioLogueado == null) {
        System.err.println("[Error] usuarioLogueado es null al cargar la vista consultar saldo.");
        
        new Alert(Alert.AlertType.ERROR, "El usuario no está logueado. Por favor, inicie sesión nuevamente.").showAndWait();
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/cuentas/consultarSaldo.fxml"));
        Parent vistaCuentasGestion = loader.load();

        ConsultarSaldoViewController controller = loader.getController();
        controller.setUsuarioActual(usuarioLogueado);  
        presupuestoObservable.agregarObserver(controller);
        
         
        double sumaPresupuestos = usuarioLogueado.getListaPresupuestos()
            .stream()
            .mapToDouble(Presupuesto::getMontoPresupuesto)
            .sum();

       
        Presupuesto presupuestoTotal = new Presupuesto( sumaPresupuestos, 0);
        presupuestoObservable.setPresupuesto(presupuestoTotal);


        ConsultarSaldoTab.setContent(vistaCuentasGestion);
    } catch (IOException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Error al cargar la vista del saldo: " + e.getMessage()).showAndWait();
    }
	}

	public void setUsuarioActual(Usuario usuario) {
    this.usuarioActual = usuario;
    }


    @FXML
    private void cargarVistaGestionCuentas() {
     if (usuarioLogueado == null) {
        System.err.println("[Error] usuarioLogueado es null al cargar la vista de gestionar cuentas.");
        
        new Alert(Alert.AlertType.ERROR, "El usuario no está logueado. Por favor, inicie sesión nuevamente.").showAndWait();
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/billeteravirtual/billeteravirtual/usuarioFolder/cuentas/gestionarCuentas.fxml"));
        Parent vistaCuentasGestion = loader.load();

        GestionarCuentaViewController controller = loader.getController();
        controller.setUsuarioActual(usuarioLogueado);  

        GestionarCuentasTab.setContent(vistaCuentasGestion);
    } catch (IOException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Error al cargar la vista de gestionar cuentas: " + e.getMessage()).showAndWait();
    }
}

   

  

    

   





   
}

