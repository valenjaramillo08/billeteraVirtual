package co.edu.uniquindio.billeteravirtual.billeteravirtual.Utils;

public class EmailUtil {

    public static void enviarCorreo (String destinatario, String asunto, String mensaje){
        System.out.println("\n================= CORREO ENVIADO =================");
        System.out.println("Para: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje:");
        System.out.println(mensaje);
        System.out.println("==================================================\n");
    }
}
