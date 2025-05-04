package co.edu.uniquindio.billeteravirtual.billeteravirtual.ObserverUsuario;

import java.util.ArrayList;
import java.util.List;

public class GestionMonto {

    private List<Observer> observers = new ArrayList<>();
    public double saldoActual;
    public double saldoGastado;

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    public double getSaldoActual() {
        return saldoActual;
       
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
        notificarObservers();
    }

    public double getSaldoGastado() {
        return saldoGastado;
    }

    public void setSaldoGastado(double saldoGastado) {
        this.saldoGastado = saldoGastado;
        notificarObservers();
    }

    private void notificarObservers() {
        for (Observer observer : observers) {
            observer.realizarTransaccion(saldoActual,saldoGastado);
        }
    }
}
