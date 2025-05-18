package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

public abstract class DecoratorTransaccion implements TransaccionD  {

    protected TransaccionD transaccion;

    public DecoratorTransaccion(TransaccionD transaccion) {
        this.transaccion=transaccion;
    }


    @Override
    public void ejecutar() {
       transaccion.ejecutar();
        
    }

    







   
    
}
