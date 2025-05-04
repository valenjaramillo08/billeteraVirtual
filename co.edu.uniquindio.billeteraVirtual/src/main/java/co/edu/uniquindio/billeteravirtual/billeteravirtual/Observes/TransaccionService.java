package co.edu.uniquindio.billeteravirtual.billeteravirtual.Observes;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class TransaccionService {

    private static TransaccionService instancia=new TransaccionService();
    private final List<Transaccion> listaTransacciones= new ArrayList<>();

    public static TransaccionService getInstancia(){
        return instancia;
    }

    public void agregarTransaccion(Transaccion t){
        listaTransacciones.add(t);
        TransaccionObservable.getInstancia().notificarObservadores();
    }

    public List<Transaccion>getTransacciones(){
        return listaTransacciones;
    }
    
}
