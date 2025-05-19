package co.edu.uniquindio.billeteravirtual.billeteravirtual.Estrategia;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Cuenta;

import java.util.List;

public interface EstrategiaCuenta {
    String getTitulo();
    List<String[]> generarContenido(Cuenta cuenta);
}
