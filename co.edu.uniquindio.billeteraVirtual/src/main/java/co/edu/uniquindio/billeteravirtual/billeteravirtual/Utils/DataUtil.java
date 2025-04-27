package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade.TransaccionFacade;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataUtil {
    public static Billetera inicializarDatos() {
        Billetera billetera = new Billetera();
        Administrador administrador = new Administrador("admin", "empleadp", "admin@gmail.com","123", "123");
        Usuario usuario1 = Usuario.builder()
                .nombre("Valentina")
                .apellido("Orozco")
                .idUsuario("12736")
                .correo("valentina@gmail.com")
                .contrasenaUsuario("12345")
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("Laura")
                .apellido("Bareño")
                .idUsuario("132324")
                .correo("laura@gmail.com")
                .contrasenaUsuario("12346")
                .build();

        Usuario usuario3 = Usuario.builder()
                .nombre("Mateo")
                .apellido("Toquica")
                .idUsuario("2223")
                .correo("mateo@gmail.com")
                .contrasenaUsuario("12347")
                .build();



        // Crear transacción usando Facade
        TransaccionFacade transaccionFacade = new TransaccionFacade();

// Crear un depósito para usuario1
        Transaccion transaccion1 = transaccionFacade.realizarTransaccion(
                "deposito",                      // Tipo de transacción
                50000,                            // Monto
                "Depósito inicial",
                LocalDate.of(2022,2,2),// Descripción
                TipoCuenta.AHORROS,               // Tipo de cuenta
                TipoCuentaOrigen.AHORROS,         // Tipo de cuenta origen
                usuario1,                         // Usuario asociado
                administrador,                    // Administrador asociado
                new ArrayList<>()                 // Categorías (vacío por ahora)
        );

// Crear un retiro para usuario2
        Transaccion transaccion2 = transaccionFacade.realizarTransaccion(
                "retiro",
                10000,
                "Retiro en cajero automático",
                LocalDate.of(2020, 1, 1),
                TipoCuenta.AHORROS,
                TipoCuentaOrigen.AHORROS,
                usuario2,
                administrador,
                new ArrayList<>()
        );

        Transaccion transaccion3 = transaccionFacade.realizarTransaccion(
                "deposito",
                80000,
                "Deposito por nomina",
                LocalDate.of(2020, 1, 1),
                TipoCuenta.CORRIENTE,
                TipoCuentaOrigen.AHORROS,
                usuario1,
                administrador,
                new ArrayList<>()
        );


        billetera.getListaUsuarios().add(usuario1);
        billetera.getListaUsuarios().add(usuario2);
        billetera.getListaUsuarios().add(usuario3);
        administrador.getListaUsuarios().add(usuario3);
        administrador.getListaUsuarios().add(usuario2);
        administrador.getListaTransacciones().add(transaccion1);
        administrador.agregarCuenta("567", "bogota", "889", TipoCuenta.AHORROS, usuario3, administrador);
        billetera.getListaAdministradores().add(administrador);


        return billetera;
    }
}
