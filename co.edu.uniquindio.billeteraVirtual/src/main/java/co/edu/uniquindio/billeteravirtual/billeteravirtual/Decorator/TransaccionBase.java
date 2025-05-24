package co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator;

public class TransaccionBase implements TransaccionD {
    private String descripcion;

    public TransaccionBase(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando transacción base: " + descripcion);
    }
    
}
