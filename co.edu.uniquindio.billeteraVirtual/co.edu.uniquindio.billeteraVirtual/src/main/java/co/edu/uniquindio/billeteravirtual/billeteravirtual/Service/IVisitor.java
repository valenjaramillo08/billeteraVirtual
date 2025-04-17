package co.edu.uniquindio.billeteravirtual.billeteravirtual.Service;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Presupuesto;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

public interface IVisitor {
    void visitar(Usuario usuario);
    void visitar(Presupuesto presupuesto);
    void visitar(Transaccion transaccion);
}


