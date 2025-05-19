package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;

public class ReporteAdminCommand implements Command {

    public Administrador administrador;
    public String formato; 

    public ReporteAdminCommand(Administrador administrador, String formato){
        this.administrador = administrador;
        this.formato = formato; 
    }

    @Override
    public void execute() {
       administrador.generarReporte(formato);
    }

    
}
