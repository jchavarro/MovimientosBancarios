package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.MovimientoDto;
import pruebadevsu.movimientos.web.dto.reponse.MovimientoResponseDto;
import pruebadevsu.movimientos.web.dto.request.MovimientoRequestDto;

import java.util.Date;

/**
 * Intefaz de servicio de movimiento.
 *
 * @author Juan Chavarro
 */
public interface MovimientoService {

    /**
     * Metodo get para obtener movimiento por su identificacion.
     * @param movimientoId identificacion del movimiento.
     * @return Objeto de transferencia de datos del movimiento.
     */
    MovimientoResponseDto obtenerMovimientoPorId(Integer movimientoId);

    /**
     * Metodo post para crear movimiento a partir del objeto movimiento.
     * @param movimientoDto Objeto movimiento con su informacion.
     * @return Objeto de transferencia de datos del movimiento.
     */
    MovimientoResponseDto crearMovimiento(MovimientoRequestDto movimientoDto);

    /**
     * Metodo de actualizar la información completa del cuenta.
     * @param movimientoDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    MovimientoDto actualizarMovimiento(MovimientoDto movimientoDto);

    /**
     * Metodo de eliminar movimiento por su id.
     * @param movimientoId Identifiacion del movimiento.
     * @return True si es eliminado.
     */
    Boolean eliminarMovimiento(Integer movimientoId);

    /**
     * Metodo de editar la fecha de movivimieto por su numero de movimiento.
     *
     * @param movimientoId numero de movimiento.
     * @param fecha estado nuevo de la cuenta.
     * @return Objeto de transferencia de datos editados de la cuenta.
     */
    MovimientoDto editarMovimientoFecha(Integer movimientoId, Date fecha);
}
