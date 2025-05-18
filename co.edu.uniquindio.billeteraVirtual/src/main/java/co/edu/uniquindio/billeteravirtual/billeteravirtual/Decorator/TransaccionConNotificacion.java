package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.EmailUtil;

public class TransaccionConNotificacion extends DecoratorTransaccion {

    public TransaccionConNotificacion(TransaccionD transaccion){
        super(transaccion);
    }

    @Override
    public void ejecutar() {
        System.out.println("Decorador de notificación ejecutado"); // <-- Agrega esto
       super.ejecutar();
        if (transaccion instanceof Transaccion t) {
            String mensaje = 
                    "\nTipo: " + t.getTipoTransaccion() +
                    "\nMonto: $" + t.getMonto() +
                    "\nFecha: " + t.getFechaTransaccion() +
                    "\nDescripción: " + t.getDescripcion();

            // Simulación en consola (sin correo)
            EmailUtil.enviarCorreo("simulado", "Transacción realizada", mensaje);

            // Mostrar ventana emergente solo con el mensaje
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correo simulado");
                alert.setHeaderText("Notificación de transacción");
                alert.setContentText(mensaje);
                alert.showAndWait();
            });
        }
  }
   
}
