package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.types.BadRequestException;
import pruebadevsu.movimientos.exceptions.types.NotFoundException;
import pruebadevsu.movimientos.exceptions.types.UnprocessableEntityException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;
import pruebadevsu.movimientos.model.repositories.MovimientoRepository;
import pruebadevsu.movimientos.service.interfaces.MovimientoService;
import pruebadevsu.movimientos.service.interfaces.adapter.CuentaServiceAdapter;
import pruebadevsu.movimientos.service.interfaces.adapter.MovimientoServiceAdapter;
import pruebadevsu.movimientos.service.utils.CuentaFactory;
import pruebadevsu.movimientos.service.utils.MovimientoFactory;
import pruebadevsu.movimientos.web.dto.MovimientoDto;
import pruebadevsu.movimientos.web.dto.reponse.MovimientoResponseDto;
import pruebadevsu.movimientos.web.dto.request.MovimientoRequestDto;

import javax.persistence.EntityManager;
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
public class MovimientoServiceImpl implements MovimientoService, MovimientoServiceAdapter {

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
    public MovimientoResponseDto obtenerMovimientoPorId(final Integer movimientoId) {
        log.info("Consulta de movimiento : " + movimientoId);
        MovimientoEntity movimientoEntity = movimientoRepositorio.findById(movimientoId)
                .orElseThrow(() -> new NotFoundException("No se encontró el movimiento: " + movimientoId));
        return MovimientoFactory.crearMovimientoCuenta(movimientoEntity);
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
            if ((valorMovimientosDia(movimientoDto.getNumeroCuenta()) + Math.abs(movimientoDto.getValor()))
                    <= TOPE_DIA) {
                CuentaEntity cuentaEntityAntesMovimiento = cuentaServiceAdapter
                        .obtenerCuentaEntityPorNumeroCuenta(movimientoDto.getNumeroCuenta());
                cuentaServiceAdapter.efectuarMovimiento(movimientoDto.getNumeroCuenta(), movimientoDto.getValor());
                MovimientoEntity movimientoEntity = movimientoRepositorio.save(MovimientoFactory
                        .crearMovimientoCuentaEntity(movimientoDto, cuentaEntityAntesMovimiento));
                return MovimientoFactory.crearMovimientoCuenta(movimientoEntity);
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
     * Metodo de actualizar la información completa del cuenta.
     *
     * @param movimientoDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public MovimientoDto actualizarMovimiento(final MovimientoDto movimientoDto) {
        log.info("Actualizacion de movimiento : " + movimientoDto.getMovimientoId());
        movimientoRepositorio.findById(movimientoDto.getMovimientoId())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el movimiento"));
        CuentaEntity cuentaEntity = cuentaServiceAdapter
                .obtenerCuentaEntityPorNumeroCuenta(movimientoDto.getNumeroCuenta());
        return modelMapper.map(movimientoRepositorio.save(
                        MovimientoFactory.crearMovimientoCuentaEntity(movimientoDto, cuentaEntity)),
                MovimientoDto.class);
    }

    /**
     * Metodo de eliminar movimiento por su id.
     *
     * @param movimientoId Identifiacion del movimiento.
     * @return True si es eliminado.
     */
    @Override
    public Boolean eliminarMovimiento(final Integer movimientoId) {
        log.info("Eliminacion de movimiento : " + movimientoId);
        MovimientoEntity movimientoEntity = movimientoRepositorio.findById(movimientoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado cuenta: " + movimientoId));
        movimientoRepositorio.deleteById(movimientoEntity.getMovimientoId());
        return Boolean.TRUE;
    }

    @Override
    public MovimientoDto editarMovimientoFecha(Integer movimientoId, Date fecha) {
        log.info("Edicion de la fecha de movimiento : " + movimientoId);
        MovimientoEntity movimientoEntity = movimientoRepositorio.findById(movimientoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el movimiento: " + movimientoId));
        return modelMapper.map(movimientoRepositorio
                        .save(MovimientoFactory.editarFechaMovimientoEntity(movimientoEntity, fecha)),
                MovimientoDto.class);
    }

    /**
     * Obtener lista de movimientos segun lista de cuentas.
     * @param cuentasEntity Lista de cuentas
     * @return
     */
    @Override
    public List<MovimientoEntity> obtenerMovimientoPorCuentas(List<CuentaEntity> cuentasEntity) {
        return movimientoRepositorio.findAll().stream()
                .filter(movimiento -> cuentasEntity.stream()
                        .anyMatch(cuenta -> cuenta.getNumeroCuenta()
                                .equals(movimiento.getCuentaEntity().getNumeroCuenta())))
                .collect(Collectors.toList());
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
