package co.edu.uniquindio.billeteravirtual.billeteravirtual.Observador;

public interface ObservadorMetodosAdmin {
    void agregarObserver(ObservadorAdministrador observador);
    void eliminarObserver(ObservadorAdministrador observador);
    void notificarObservers();
}
