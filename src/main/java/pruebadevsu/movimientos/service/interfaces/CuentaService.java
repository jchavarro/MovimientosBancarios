package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.CuentaDto;
import pruebadevsu.movimientos.web.dto.reponse.CuentaResponseDto;

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
    CuentaResponseDto obtenerCuentaPorId(Integer numeroCuenta);

    /**
     * Metodo post para crear cuenta a partir del objeto cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    CuentaResponseDto crearCuenta(CuentaDto cuentaDto);

    /**
     * Metodo de actualizar la información completa del cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    CuentaResponseDto actualizarCuenta(CuentaDto cuentaDto);

    /**
     * Metodo de eliminar cuenta por su id.
     * @param numeroCuenta Identifiacion de la cuenta.
     * @return True si es eliminada.
     */
    Boolean eliminarCuenta(Integer numeroCuenta);

    /**
     * Metodo de editar el estado de la cuenta por su numero de cuenta.
     * @param numeroCuenta numero de la cuenta.
     * @param estadoCuenta estado nuevo de la cuenta.
     * @return Objeto de transferencia de datos editados de la cuenta.
     */
    CuentaResponseDto editarEstadoCliente(Integer numeroCuenta, Boolean estadoCuenta);
}
