package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.model.repositories.MovimientoRepository;
import pruebadevsu.movimientos.service.interfaces.MovimientoService;
import pruebadevsu.movimientos.web.dto.MovimientoDto;

/**
 * Servicio para los movimientos.
 *
 * @author Juan Chavarro
 */
@Service
@Slf4j
public class MovimientoServiceImpl implements MovimientoService {

    /**
     * Permite la conversión de un objeto a otro.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Repositorio de movimiento.
     */
    @Autowired
    private MovimientoRepository movimientoRepositorio;


    /**
     * Metodo get para obtener movimiento por su identificacion.
     * @param movimientoId identificacion del movimiento.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @Override
    public MovimientoDto obtenerMovimientoPorId(final Integer movimientoId) {
        return null;
    }

    /**
     * Metodo post para crear movimiento a partir del objeto movimiento.
     * @param movimientoDto Objeto movimiento con su informacion.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @Override
    public MovimientoDto crearMovimiento(final MovimientoDto movimientoDto) {
        return null;
    }

    /**
     * Metodo de actualizar la información completa del cuenta.
     * @param movimientoDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public MovimientoDto actualizarMovimiento(final MovimientoDto movimientoDto) {
        return null;
    }

    /**
     * Metodo de eliminar movimiento por su id.
     * @param movimientoId Identifiacion del movimiento.
     * @return True si es eliminado.
     */
    @Override
    public Boolean eliminarMovimiento(final Integer movimientoId) {
        return null;
    }
}
