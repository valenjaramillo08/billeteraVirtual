package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ValidacionSaldo extends DecoratorTransaccion {

    public ValidacionSaldo(TransaccionD transaccion){
        super(transaccion);
    }

    @Override
    public void ejecutar() {
        if (transaccion instanceof Transaccion t) {
        double saldoDisponible = t.getCuentaOrigen().getPresupuesto().getMontoPresupuesto();
        double monto = t.getMonto();
        System.out.println("Validando saldo: disponible=" + saldoDisponible + ", monto=" + monto);
        if (saldoDisponible >= monto) {
            super.ejecutar();
            mostrarAlerta("Hecho!", "Saldo válido: " + saldoDisponible);
        } else {
            mostrarAlerta("Error", "El saldo es insuficiente. No se puede realizar la operación");
        }
    } else {
        super.ejecutar();
    }  
        
    }

     private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    
}

    



   
    

