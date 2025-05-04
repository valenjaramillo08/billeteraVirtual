package co.edu.uniquindio.billeteravirtual.billeteravirtual.Observes;

import java.util.ArrayList;
import java.util.List;

public class TransaccionObservable {

    private static TransaccionObservable instancia= new TransaccionObservable();
    private final List<Observador> observadores=new ArrayList<>();


    public static TransaccionObservable getInstancia(){
        return instancia;
    }

    public void registrarObservador(Observador o){
        observadores.add(o);
    }

    public void eliminarObservador(Observador o){
        observadores.remove(o);
    }

    public void notificarObservadores(){
        for(Observador o:observadores){
            o.actualizar();
        }
    }
    
}
