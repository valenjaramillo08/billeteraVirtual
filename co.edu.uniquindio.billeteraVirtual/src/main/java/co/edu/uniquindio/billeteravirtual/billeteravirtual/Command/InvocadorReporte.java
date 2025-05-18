package co.edu.uniquindio.billeteravirtual.billeteravirtual.Command;

public class InvocadorReporte {
    private Command command;

    public void setCommand (Command command){
        this.command = command;
    }

    public void ejecutar(){
        if ( command != null){
            command.execute();
        }
    }
}
