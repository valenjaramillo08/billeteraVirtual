package co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade.TransaccionFacade;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Singleton.ModelFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionTransaccionesController {
    ModelFactory modelFactory;
    TransaccionFacade transaccionFacade;

    public GestionTransaccionesController(){

        modelFactory = ModelFactory.getInstancia();
        transaccionFacade = new TransaccionFacade();
    }

    public boolean agregarTransaccion(Transaccion transaccion){
        return modelFactory.agregarTransaccion(transaccion);
    }



    public List<Usuario> obtenerListaUsuarios() {
        return modelFactory.getListaUsuarios();
    }
    /*public Transaccion crearTransaccionConFecha(String tipo, double monto, String descripcion, LocalDate fecha, Usuario usuario) {
        Transaccion transaccion = transaccionFacade.realizarTransaccion(
                tipo,
                monto,
                descripcion,
                fecha,
                null, // TipoCuenta
                null, // TipoCuentaOrigen
                usuario,
                null, // Administrador
                new ArrayList<>() // Categorías
        );

        // Guardar en modelFactory también si quieres centralizar todo
        modelFactory.agregarTransaccion(transaccion);
        usuario.getListaTransacciones().add(transaccion);

        return transaccion;
    }*/




}

