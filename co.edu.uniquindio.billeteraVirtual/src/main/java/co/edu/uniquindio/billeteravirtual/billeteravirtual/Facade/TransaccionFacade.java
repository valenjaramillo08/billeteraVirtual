package co.edu.uniquindio.billeteravirtual.billeteravirtual.Facade;

import co.edu.uniquindio.billeteravirtual.billeteravirtual.FactoryMethod.TransaccionFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtual.Model.*;

import java.time.LocalDate;
import java.util.List;

public class TransaccionFacade {
    public Transaccion realizarTransaccion(
            String tipoTransaccion,
            double monto,
            String descripcion,
            LocalDate fechaTransaccion,
            TipoCuenta tipoCuenta,
            TipoCuentaOrigen cuentaOrigen,
            Usuario usuario,
            Administrador administrador,
            List<CategoriaBase> categorias
    ) {
        // 1. Crear transacción con Factory
        Transaccion transaccion = TransaccionFactory.crearTransaccion(tipoTransaccion, monto);

        // 2. Configurar atributos adicionales
        transaccion.setIdTransaccion(generarId());
        transaccion.setFechaTransaccion(LocalDate.now());
        transaccion.setDescripcion(descripcion);
        transaccion.setTipoCuenta(tipoCuenta);
        transaccion.setTipoCuentaOrigen(cuentaOrigen);
        transaccion.setUsuarioAsociado(usuario);
        transaccion.setAdministradorAsociado(administrador);
        transaccion.setListaCategorias(categorias);

        // 3. Procesar
        transaccion.procesar();

        // 4. Asociar al usuario (esto depende de tu lógica)
        usuario.getListaTransacciones().add(transaccion); // si tienes lista

        // 5. Retornar transacción creada y lista
        return transaccion;
    }

    private String generarId() {
        return "TX-" + System.currentTimeMillis();
    }


}
