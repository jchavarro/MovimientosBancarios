package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.CuentaDto;

/**
 * Intefaz de servicio de cuenta.
 *
 * @author Juan Chavarro
 */
public interface CuentaService {

    /**
     * Metodo get para obtener cuenta por su identificacion.
     * @param numeroCuenta identificacion de la cuenta.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    CuentaDto obtenerCuentaPorId(Integer numeroCuenta);

    /**
     * Metodo post para crear cuenta a partir del objeto cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    CuentaDto crearCuenta(CuentaDto cuentaDto);

    /**
     * Metodo de actualizar la información completa del cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    CuentaDto actualizarCuenta(CuentaDto cuentaDto);

    /**
     * Metodo de eliminar cuenta por su id.
     * @param numeroCuenta Identifiacion de la cuenta.
     * @return True si es eliminada.
     */
    Boolean eliminarCuenta(Integer numeroCuenta);
}
