package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.TransaccionConNotificacion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.TransaccionD;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Decorator.ValidacionSaldo;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.DatosTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.FabricaTransacciones;
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

                billetera.getListaUsuarios().addAll(List.of(usuario1, usuario2, usuario3));
                admin.getListaUsuarios().addAll(List.of(usuario1,usuario2, usuario3));

                // Crear cuentas
                admin.agregarCuenta("567", "Bogotá", "889", TipoCuenta.AHORROS, usuario3, admin);
                admin.agregarCuenta("789", "Banco Popular", "123", TipoCuenta.AHORROS, usuario3, admin);
                admin.agregarCuenta("1011", "banco mi banco", "1212", TipoCuenta.AHORROS, usuario2, admin);

                admin.agregarCuenta("1234", "Banco Davivienda", "4321", TipoCuenta.AHORROS, usuario1, admin);


                Cuenta cuenta1 = admin.obtenerCuenta("567");  // Mateo
                Cuenta cuenta2 = admin.obtenerCuenta("789");  // Mateo
                Cuenta cuenta3 = admin.obtenerCuenta("1011"); // Laura
                Cuenta cuenta4 = admin.obtenerCuenta("1234"); // Valentina

                // Presupuesto para cuenta2 (Mateo)
                Presupuesto presupuesto = new Presupuesto( 600000, 0);
                presupuesto.agregarCategoria(NombreCategoria.COMIDA, "222", 200000);
                presupuesto.agregarCategoria(NombreCategoria.TRANSPORTE, "333", 120000);
                presupuesto.agregarCategoria(NombreCategoria.UNIVERSIDAD, "555", 200000);

               
                cuenta2.setPresupuesto(presupuesto);

                // Presupuesto para cuenta1 (Mateo)
                Presupuesto presupuesto2 = new Presupuesto( 1000000, 0);
                presupuesto2.agregarCategoria(NombreCategoria.HOGAR, "666", 300000);
                cuenta1.setPresupuesto(presupuesto2);

                // Presupuesto para cuenta3 (Laura)
                Presupuesto presupuesto3 = new Presupuesto(1500000, 0);
                presupuesto3.agregarCategoria(NombreCategoria.OCIO, "777", 400000);
                cuenta3.setPresupuesto(presupuesto3);

                // Presupuesto para cuenta4 (Valentina)
                Presupuesto presupuesto4 = new Presupuesto( 800000, 0);
                presupuesto4.agregarCategoria(NombreCategoria.HOGAR, "888", 300000);
                cuenta4.setPresupuesto(presupuesto4);

                // Transacción para Mateo - depósito
                DatosTransaccion datos1 = new DatosTransaccion(
                        UUID.randomUUID().toString(), cuenta2, LocalDate.now(), 100000,
                        "Depósito de prueba", cuenta1, TipoTransaccion.DEPOSITO, null
                );
                admin.registrarTransaccion(datos1);

                // Ejemplo: fechas diferentes para pruebas
                  LocalDate hoy = LocalDate.now();
                  LocalDate haceUnaSemana = hoy.minusDays(7);
                  LocalDate haceTresDias = hoy.minusDays(3);

                // Mateo - retiro transporte
                DatosTransaccion datos2 = new DatosTransaccion(
                        UUID.randomUUID().toString(), cuenta2, LocalDate.now(), 10000,
                        "Retiro transporte", null, TipoTransaccion.RETIRO, null
                );
                Transaccion trans1 = FabricaTransacciones.crear(datos2);
                trans1.setPresupuesto(presupuesto);
                trans1.setCategoriaProcesada(NombreCategoria.TRANSPORTE);
                cuenta2.getListaTransacciones().add(trans1);
                cuenta2.getUsuarioAsociado().getListaTransacciones().add(trans1);

                // Mateo - retiro universidad
                DatosTransaccion datos3 = new DatosTransaccion(
                        UUID.randomUUID().toString(), cuenta2, haceUnaSemana, 15000,
                        "Retiro universidad", null, TipoTransaccion.RETIRO, null
                );
                Transaccion trans2 = FabricaTransacciones.crear(datos3);
                trans2.setPresupuesto(presupuesto);
                trans2.setCategoriaProcesada(NombreCategoria.UNIVERSIDAD);
                cuenta2.getListaTransacciones().add(trans2);
                cuenta2.getUsuarioAsociado().getListaTransacciones().add(trans2);

                // Laura - retiro ocio
                DatosTransaccion datos4 = new DatosTransaccion(
                        UUID.randomUUID().toString(), cuenta3, LocalDate.now(), 30000,
                        "Pago ocio", null, TipoTransaccion.RETIRO, null
                );
                Transaccion trans3 = FabricaTransacciones.crear(datos4);
                trans3.setPresupuesto(presupuesto3);
                trans3.setCategoriaProcesada(NombreCategoria.OCIO);
                try {
                        trans3.procesar(NombreCategoria.OCIO);
                        cuenta3.getListaTransacciones().add(trans3);
                        System.out.println("✅ Laura: transacción registrada");
                } catch (Exception e) {
                        System.out.println("❌ Laura: Error al procesar transacción → " + e.getMessage());
                }

                // Valentina - retiro hogar
                DatosTransaccion datos5 = new DatosTransaccion(
                        UUID.randomUUID().toString(), cuenta4, LocalDate.now(), 20000,
                        "Compra para el hogar", null, TipoTransaccion.RETIRO, null
                );
                Transaccion trans4 = FabricaTransacciones.crear(datos5);
                trans4.setPresupuesto(presupuesto4);
                trans4.setCategoriaProcesada(NombreCategoria.HOGAR);
                try {
                        trans4.procesar(NombreCategoria.HOGAR);
                        cuenta4.getListaTransacciones().add(trans4);
                        System.out.println("✅ Valentina: transacción registrada");
                } catch (Exception e) {
                        System.out.println("❌ Valentina: Error al procesar transacción → " + e.getMessage());
                }


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

        private static Administrador crearAdministrador(String nombre, String apellido, String correo, String id, String contrasena) {

                return new Administrador(nombre, apellido, correo, id, contrasena);
        }
}
