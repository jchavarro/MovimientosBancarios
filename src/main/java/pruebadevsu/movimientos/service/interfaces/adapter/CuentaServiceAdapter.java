package pruebadevsu.movimientos.service.interfaces.adapter;

import pruebadevsu.movimientos.model.entities.CuentaEntity;

import java.util.List;

public interface CuentaServiceAdapter {

    /**
     * Metodo para obtener la entidad de Cuenta por Id.
     * @param numeroCuenta id de la cuenta.
     * @return Entidad de la cuenta.
     */
    CuentaEntity obtenerCuentaEntityPorNumeroCuenta(Integer numeroCuenta);

    /**
     * Debitar o acreditar una cuenta con el valor del movimiento.
     * @param numeroCuenta numero de cuenta a aplicar el movimiento.
     * @param valor valor del movimiento.
     * @return Cuenta entity con el valor efectuado.
     */
    CuentaEntity efectuarMovimiento(Integer numeroCuenta, Double valor);

    /**
     * Obtiene todas las cuentas de un cliente.
     * @param clienteId identificador de cliente.
     * @return lista de cuentas.
     */
    List<CuentaEntity> obtenerCuentasPorCliente(Integer clienteId);
}
