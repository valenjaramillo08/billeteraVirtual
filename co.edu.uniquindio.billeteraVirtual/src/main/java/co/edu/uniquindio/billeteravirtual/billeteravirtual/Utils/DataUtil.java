package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade.TransaccionFacade;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


import com.itextpdf.text.List;

public class DataUtil {

        public static Billetera billetera;
        
    public static Billetera inicializarDatos() {

     

        Billetera billetera = new Billetera();
        Administrador administrador = new Administrador("admin", "empleadp", "admin@gmail.com","123", "123");
        Usuario usuario1 = Usuario.builder()
                .nombre("Valentina")
                .apellido("Orozco")
                .idUsuario("12736")
                .correo("valentina@gmail.com")
                .contrasenaUsuario("12345678")
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("Laura")
                .apellido("Bareño")
                .idUsuario("132324")
                .correo("laura@gmail.com")
                .contrasenaUsuario("1234678")
                .build();

        Usuario usuario3 = Usuario.builder()
                .nombre("Mateo")
                .apellido("Toquica")
                .idUsuario("2223")
                .correo("mateo@gmail.com")
                .contrasenaUsuario("1234778")
                .build();



        // Crear transacción usando Facade
        TransaccionFacade transaccionFacade = new TransaccionFacade();




        billetera.getListaUsuarios().add(usuario1);
        billetera.getListaUsuarios().add(usuario2);
        billetera.getListaUsuarios().add(usuario3);
        administrador.getListaUsuarios().add(usuario3);
        administrador.getListaUsuarios().add(usuario2);

        administrador.agregarCuenta("567", "bogota", "889", TipoCuenta.AHORROS, usuario3, administrador);
        billetera.getListaAdministradores().add(administrador);
        administrador.agregarCuenta("890","AVVillas","6383",TipoCuenta.CORRIENTE,usuario2,administrador);
        administrador.agregarCuenta("7485","BBVA" ,"5647474",TipoCuenta.AHORROS, usuario3, administrador);
        administrador.agregarCuenta("7890","Colpatria" ,"563783",TipoCuenta.CORRIENTE, usuario1, administrador);
      


        return billetera;
    }


  
    public static Usuario autenticarUsuario(String correo, String contrasena) {
        return billetera.getListaUsuarios().stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getContrasenaUsuario().equals(contrasena))
                .findFirst()
                .orElse(null);
    }

        public static ObservableList<Cuenta> obtenerCuentasUsuario (Usuario usuario){
                return FXCollections.observableArrayList(usuario.getListaCuentas());
        }

 


}
