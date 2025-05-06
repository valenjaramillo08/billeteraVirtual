package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.ObserverUsuario.GestionMonto;

public class GestionMontoController {
    public GestionMonto gestionMonto;

    public GestionMontoController(GestionMonto gestionMonto){
        if (gestionMonto == null){
            throw new IllegalArgumentException("El monto no puede ser null");
        }
        this.gestionMonto = gestionMonto;

    }

    public void realizarTransaccion(double saldoActual,double saldoGastado){
        gestionMonto.setSaldoActual(saldoActual);
        gestionMonto.setSaldoGastado(saldoGastado);
        gestionMonto.notificarObservers();
    }

    public GestionMonto getGestionMonto() {
        return gestionMonto;
    }

    

   

    
}
