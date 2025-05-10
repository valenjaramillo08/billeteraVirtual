package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ValidacionSaldo extends DecoratorTransaccion {

    private double saldoDisponible;
    private double monto;

    public ValidacionSaldo(Transaccion transaccion){
        super(transaccion);
    }

    @Override
    public void ejecutar() {
        if( saldoDisponible >= monto){
            mostrarAlerta("Hecho!", "Saldo Valido:" + saldoDisponible);
            super.ejecutar();
        }else{
            mostrarAlerta("Error","El saldo es insuficiente. No se puede realizar la operación" );
        }

        
    }

    private void mostrarAlerta(String titulo, String mensaje) {
    Alert alerta = new Alert(AlertType.WARNING);
    alerta.setTitle(titulo);
    alerta.setHeaderText(null);
    alerta.setContentText(mensaje);
    alerta.showAndWait();
}

    



   
    
}
