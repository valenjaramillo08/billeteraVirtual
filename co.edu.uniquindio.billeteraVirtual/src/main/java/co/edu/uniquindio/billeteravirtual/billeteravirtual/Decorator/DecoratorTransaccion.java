package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

public abstract class DecoratorTransaccion implements Transaccion  {

    protected Transaccion transaccion;

    public DecoratorTransaccion(Transaccion transaccion) {
        this.transaccion=transaccion;
    }


    @Override
    public void ejecutar() {
        transaccion.ejecutar();
        
    }

    







   
    
}
