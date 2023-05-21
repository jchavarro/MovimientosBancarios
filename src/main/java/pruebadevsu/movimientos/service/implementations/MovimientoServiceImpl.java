package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.BadRequestException;
import pruebadevsu.movimientos.exceptions.UnprocessableEntityException;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;
import pruebadevsu.movimientos.model.repositories.MovimientoRepository;
import pruebadevsu.movimientos.service.interfaces.MovimientoService;
import pruebadevsu.movimientos.service.interfaces.adapter.CuentaServiceAdapter;
import pruebadevsu.movimientos.service.utils.MovimientoFactory;
import pruebadevsu.movimientos.web.dto.MovimientoDto;
import pruebadevsu.movimientos.web.dto.reponse.MovimientoResponseDto;
import pruebadevsu.movimientos.web.dto.request.MovimientoRequestDto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
     * Adaptador de cuenta service.
     */
    @Autowired
    private CuentaServiceAdapter cuentaServiceAdapter;


    /**
     * Valor del tope diario de movimientos en retiro
     */
    private Double TOPE_DIA = 1000D;

    /**
     * Metodo get para obtener movimiento por su identificacion.
     *
     * @param movimientoId identificacion del movimiento.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @Override
    public MovimientoDto obtenerMovimientoPorId(final Integer movimientoId) {
        return null;
    }

    /**
     * Crear movimiento a partir del objeto movimiento.
     *
     * @param movimientoDto Objeto movimiento con su informacion.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @Override
    public MovimientoResponseDto crearMovimiento(final MovimientoRequestDto movimientoDto) {
        log.info("Creacion del movimiento para la cuenta: " + movimientoDto.getNumeroCuenta());
        if (validarMovimiento(movimientoDto)) {
            if ((valorMovimientosDia(movimientoDto.getNumeroCuenta()) + movimientoDto.getValor()) <= TOPE_DIA) {
                CuentaEntity cuentaEntityAntesMovimiento =  cuentaServiceAdapter
                        .obtenerCuentaEntityPorNumeroCuenta(movimientoDto.getNumeroCuenta());
                CuentaEntity cuentaEntityMovimientoEfectuado = cuentaServiceAdapter
                        .efectuarMovimiento(movimientoDto.getNumeroCuenta(), movimientoDto.getValor());
                MovimientoEntity movimientoEntity = movimientoRepositorio
                        .save(MovimientoFactory.crearMovimientoCuentaEntity(movimientoDto, cuentaEntityAntesMovimiento));
                return MovimientoFactory.crearMovimientoCuenta(movimientoEntity, cuentaEntityMovimientoEfectuado);
            } else throw new UnprocessableEntityException("Cupo diario excedido");
        } else throw new BadRequestException("El numero de cuenta, el tipo de movimientio, la fecha y el saldo del " +
                "cliente no pueden ser vacios");

    }

    private Double valorMovimientosDia(final Integer numeroCuenta) {
        return movimientoRepositorio.findAllByFechaBetween(fechaDiaAnterior(), new Date())
                .stream().filter(movimientoEntity -> movimientoEntity.getTipoMovimiento().equals("retiro") &&
                        movimientoEntity.getCuentaEntity().getNumeroCuenta().equals(numeroCuenta))
                .mapToDouble(m -> Math.abs(m.getValor()))
                .sum();
    }

    /**
     * Devuelve la fecha del dia menos 24 horas.
     *
     * @return fecha del dia anterior
     */
    private Date fechaDiaAnterior() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * Metodo de actualizar la información completa del cuenta.
     *
     * @param movimientoDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public MovimientoDto actualizarMovimiento(final MovimientoDto movimientoDto) {
        return null;
    }

    /**
     * Metodo de eliminar movimiento por su id.
     *
     * @param movimientoId Identifiacion del movimiento.
     * @return True si es eliminado.
     */
    @Override
    public Boolean eliminarMovimiento(final Integer movimientoId) {
        return null;
    }

    /**
     * Validacion de campos importantes para el registro y actualización del movimiento.
     *
     * @param movimientoDto informacion del movimiento.
     * @return true si todos los campos estan llenos.
     */
    private boolean validarMovimiento(final MovimientoRequestDto movimientoDto) {
        return (movimientoDto.getValor() != null ||
                movimientoDto.getNumeroCuenta() != null);
    }
}
