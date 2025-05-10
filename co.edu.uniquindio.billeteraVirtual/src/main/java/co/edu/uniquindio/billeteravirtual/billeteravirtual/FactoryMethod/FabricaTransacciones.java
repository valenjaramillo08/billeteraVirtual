package co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.Transaccion;

public class FabricaTransacciones {

    public static Transaccion crear(DatosTransaccion datos) {
        CreadorTransaccion creador = obtenerCreador(datos.tipoTransaccion);
        return creador.crearTransaccion(datos);
    }

    private static CreadorTransaccion obtenerCreador(TipoTransaccion tipo) {
        return switch (tipo) {
            case RETIRO -> new CreadorTransaccionRetiro();
            case DEPOSITO -> new CreadorTransaccionDeposito();
            case TRANSFERENCIA -> new CreadorTransaccionTransferencia();
        };
    }
}
