package co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorCSV;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ExportadorPDF;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget.ReporteService;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.Command;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.InvocadorReporte;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.ReporteCuentaCommand;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Command.ReporteUsuarioCommand;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        // Inicializaciones si es necesario
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuarioActual != null) {
            cbCuentas.getItems().setAll(usuarioActual.getListaCuentas());
        }
    }

    /**
     * Exporta un reporte general de gastos del usuario usando el patrón Command.
     */
    @FXML
    void exportarCSV() {
        File archivo = mostrarDialogoGuardar("csv", "Archivo CSV (*.csv)");
        if (archivo != null) {
            Command comando = new ReporteCuentaCommand(
                    cbCuentas.getValue(),
                    eliminarExtension(archivo.getAbsolutePath()),
                    new ExportadorCSV()
            );
            InvocadorReporte invocador = new InvocadorReporte();
            invocador.setCommand(comando);
            invocador.ejecutar();
        }
    }

    /**
     * Exporta un reporte detallado de la cuenta seleccionada en PDF usando Strategy + Bridge + Command.
     */
    @FXML
    void onExportarPDF(ActionEvent event) {
        Cuenta cuentaSeleccionada = cbCuentas.getValue();
        if (cuentaSeleccionada == null) {
            mostrarAlerta("Selecciona una cuenta primero.");
            return;
        }

        File archivo = mostrarDialogoGuardar("pdf", "Archivo PDF (*.pdf)");
        if (archivo != null) {
            Command comando = new ReporteCuentaCommand(
                    cuentaSeleccionada,
                    eliminarExtension(archivo.getAbsolutePath()),
                    new ExportadorPDF()
            );
            InvocadorReporte invocador = new InvocadorReporte();
            invocador.setCommand(comando);
            invocador.ejecutar();
        }
    }

    private File mostrarDialogoGuardar(String extension, String descripcion) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(descripcion, "*." + extension));
        fileChooser.setInitialFileName("reporte." + extension);
        return fileChooser.showSaveDialog(new Stage());
    }

    private String eliminarExtension(String ruta) {
        int punto = ruta.lastIndexOf('.');
        return (punto > 0) ? ruta.substring(0, punto) : ruta;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
