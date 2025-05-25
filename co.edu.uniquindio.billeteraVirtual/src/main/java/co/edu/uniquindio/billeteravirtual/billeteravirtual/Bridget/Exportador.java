package co.edu.uniquindio.billeteravirtual.billeteravirtual.Bridget;

import java.util.List;

public interface Exportador {
    void exportar(String titulo, List<String[]> filas, String nombreArchivo); 

}
