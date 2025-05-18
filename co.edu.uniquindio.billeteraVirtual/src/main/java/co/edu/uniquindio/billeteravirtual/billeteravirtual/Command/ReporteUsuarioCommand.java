package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

public class ReporteUsuarioCommand implements Command {

    private Usuario usuario;
    private String formato;

    public ReporteUsuarioCommand(Usuario usuario, String formato){
        this.usuario = usuario;
        this.formato = formato;
    }

    @Override
    public void execute() {

        usuario.generarReporte(formato);
       
    }

    
    
    
}
