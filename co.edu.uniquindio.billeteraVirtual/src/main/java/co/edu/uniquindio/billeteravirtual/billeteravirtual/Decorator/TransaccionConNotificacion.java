package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

public class TransaccionConNotificacion extends DecoratorTransaccion{

    public TransaccionConNotificacion(Transaccion transaccion){
        super(transaccion);
    }

    @Override
    public void ejecutar() {
        super.ejecutar();
        System.out.println("Enviando notificacion al usuario");
    }

    



   



    
    
}
