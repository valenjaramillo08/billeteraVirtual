package co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador;

import java.util.Observer;

public interface ObservadorMetodos {
    void agregarObserver(Observador observador);
    void eliminarObserver(Observador observador);
    void notificarObservers();
}
