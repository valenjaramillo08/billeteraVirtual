package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Billetera;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Usuario;

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

        billetera.getListaUsuarios().add(usuario1);
        billetera.getListaUsuarios().add(usuario2);
        billetera.getListaUsuarios().add(usuario3);
        administrador.getListaUsuarios().add(usuario3);
        billetera.getListaAdministradores().add(administrador);

        billetera.agregarCuenta("567", "bogota", "889", TipoCuenta.AHORROS, usuario3, administrador);


        return billetera;
    }
}
