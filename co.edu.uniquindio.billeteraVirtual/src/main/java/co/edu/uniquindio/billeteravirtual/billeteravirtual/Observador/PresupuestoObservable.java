package co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;

public class PresupuestoObservable implements ObservadorMetodos {

    private List<Observador> observadores = new ArrayList<>();
    private Presupuesto presupuesto;

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
        notificarObservers();
    }
    
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    @Override
    public void agregarObserver(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObserver(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservers() {
        for (Observador observador : observadores) {
            observador.actualizar(presupuesto);
        }
    }

    
}
