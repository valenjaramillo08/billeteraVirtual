package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.Command;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.InvocadorReporte;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.ReporteUsuarioCommand;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ReportesViewController {

    private Usuario usuarioActual;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Cuenta> cbCuentas;

   @FXML
    private Button btExportarCSV;

    @FXML
    private Button btExportarPDF;



    @FXML
    void initialize() {
        
    }

    public void setUsuarioActual(Usuario usuario) {
    this.usuarioActual = usuario;
    if (usuarioActual != null) {
        cbCuentas.getItems().setAll(usuarioActual.getListaCuentas());
    }
}

  @FXML
   void exportarPDF() {
    Command comando = new ReporteUsuarioCommand(usuarioActual, "PDF");
    InvocadorReporte invocador = new InvocadorReporte();
    invocador.setCommand(comando);
    invocador.ejecutar();

    }

    @FXML
    void exportarCSV(ActionEvent event) {
        Command comando = new ReporteUsuarioCommand(usuarioActual, "CSV");
        InvocadorReporte invocador = new InvocadorReporte();
        invocador.setCommand(comando);
        invocador.ejecutar();

    }

}

