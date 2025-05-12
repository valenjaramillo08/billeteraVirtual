package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.DatosTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;

public class DataUtil {

        public static Billetera inicializarDatos() {
                Billetera billetera = new Billetera();
                Administrador admin = crearAdministrador("admin", "empleado", "admin@gmail.com", "123", "123");
                billetera.getListaAdministradores().add(admin);

                // Crear usuarios
                Usuario usuario1 = crearUsuario("Valentina", "Orozco", "321", "valentina@gmail.com", "12345");
                Usuario usuario2 = crearUsuario("Laura", "Bareño", "1234", "laura@gmail.com", "12346");
                Usuario usuario3 = crearUsuario("Mateo", "Toquica", "2223", "mateo@gmail.com", "12347");

                // Agregar usuarios a billetera y administrador
                billetera.getListaUsuarios().addAll(List.of(usuario1, usuario2, usuario3));
                admin.getListaUsuarios().addAll(List.of(usuario2, usuario3));

                // Crear cuentas para usuario3
                admin.agregarCuenta("567", "Bogotá", "889", TipoCuenta.AHORROS, usuario3, admin);
                admin.agregarCuenta("789", "Banco Popular", "123", TipoCuenta.AHORROS, usuario3, admin);
                admin.agregarCuenta("1011", "banco mi banco", "1212", TipoCuenta.AHORROS, usuario2, admin);

                Cuenta cuenta1 = admin.obtenerCuenta("567");
                Cuenta cuenta2 = admin.obtenerCuenta("789");
                Cuenta cuenta3 = admin.obtenerCuenta("1011");



                // Crear presupuesto y asociarlo
                Presupuesto presupuesto = new Presupuesto("1", 500000, 0);
                Presupuesto presupuesto2 = new Presupuesto("2", 100000, 0);
                Presupuesto presupuesto3 = new Presupuesto("3", 1000000, 0);
                presupuesto.agregarCategoria(NombreCategoria.COMIDA, "222", 20000);
                presupuesto.agregarCategoria(NombreCategoria.TRANSPORTE, "333", 20000);
                cuenta2.setPresupuesto(presupuesto);

                cuenta1.setPresupuesto(presupuesto2);
                cuenta3.setPresupuesto(presupuesto3);



                // Crear transacción entre cuentas
                DatosTransaccion datos = new DatosTransaccion(
                                UUID.randomUUID().toString(),
                                cuenta2,
                                LocalDate.now(),
                                100000,
                                "Depósito de prueba",
                                cuenta1,
                                TipoTransaccion.DEPOSITO,
                                null);
                admin.registrarTransaccion(datos);

                return billetera;
        }

        private static Usuario crearUsuario(String nombre, String apellido, String idUsuario, String correo, String contrasena) {
                return Usuario.builder()
                                .nombre(nombre)
                                .apellido(apellido)
                                .idUsuario(idUsuario)
                                .correo(correo)
                                .contrasenaUsuario(contrasena)
                                .build();
        }

        private static Administrador crearAdministrador(String nombre, String apellido, String correo, String id,
                        String contrasena) {
                return new Administrador(nombre, apellido, correo, id, contrasena);
        }
}
