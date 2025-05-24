package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils.EmailUtil;

public class TransaccionConNotificacion extends DecoratorTransaccion  {

    public TransaccionConNotificacion(TransaccionD transaccion){
        super(transaccion);
    }

    @Override
    public void ejecutar() {
     System.out.println("Decorador de notificación ejecutado");
     super.ejecutar();
    System.out.println("Mostrando alerta de notificación...");
    if (transaccion instanceof Transaccion t) {
        String mensaje = 
                "\nTipo: " + t.getTipoTransaccion() +
                "\nMonto: $" + t.getMonto() +
                "\nFecha: " + t.getFechaTransaccion() +
                "\nDescripción: " + t.getDescripcion();

        EmailUtil.enviarCorreo("simulado", "Transacción realizada", mensaje);
        javafx.application.Platform.runLater(()->{
         System.out.println("Antes del Alert");
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Correo simulado");
         alert.setHeaderText("Notificación de transacción");
         alert.setContentText(mensaje);
         alert.showAndWait();
         System.out.println("Después del Alert");
    }); 
  }

}

}
   

