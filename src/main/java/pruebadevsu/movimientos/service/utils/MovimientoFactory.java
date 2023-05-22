package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;
import pruebadevsu.movimientos.web.dto.MovimientoDto;
import pruebadevsu.movimientos.web.dto.reponse.MovimientoResponseDto;
import pruebadevsu.movimientos.web.dto.request.MovimientoRequestDto;

import java.util.Date;

/**
 * clase factory para el movimiento.
 */
public final class MovimientoFactory {
    /**
     * constructor vacio.
     */
    private MovimientoFactory() {
    }

    /**
     * Crea movimiento con informacion de la cuente.
     * @param movimientoEntity entidad del movimiento
     * @return Objeto de transferencia de la cuenta
     */
    public static MovimientoResponseDto crearMovimientoCuenta(final MovimientoEntity movimientoEntity) {
        return MovimientoResponseDto.builder()
                .numeroCuenta(movimientoEntity.getCuentaEntity().getNumeroCuenta())
                .tipoCuenta(movimientoEntity.getCuentaEntity().getTipoCuenta())
                .saldoInicial(movimientoEntity.getSaldo())
                .estado(movimientoEntity.getCuentaEntity().getEstado())
                .movimientoDescipcion(movimientoEntity.getTipoMovimiento() + " de "
                        + Math.abs(movimientoEntity.getValor()))
                .build();
    }

    /**
     * Creacion de la entidad de movimiento a partir del request de movimiento.
     * @param movimientoDto Request de movimiento
     * @param cuentaEntity Entidad de la cuenta.
     * @return entidad del movimiento.
     */
    public static MovimientoEntity crearMovimientoCuentaEntity(final MovimientoRequestDto movimientoDto,
                                                               final CuentaEntity cuentaEntity) {
        return MovimientoEntity.builder()
                .fecha(new Date())
                .tipoMovimiento(movimientoDto.getValor() < 0 ? "retiro" : "deposito")
                .valor(movimientoDto.getValor())
                .saldo(cuentaEntity.getSaldoInicial())
                .cuentaEntity(cuentaEntity)
                .build();
    }

    /**
     * Creacion de la entidad de movimiento a partir del movimiento dto.
     * Entrega informacion completa para la actualizacion del movimiento.
     * @param movimientoDto Request de movimiento
     * @param cuentaEntity Entidad de la cuenta.
     * @return entidad del movimiento.
     */
    public static MovimientoEntity crearMovimientoCuentaEntity(final MovimientoDto movimientoDto,
                                                               final CuentaEntity cuentaEntity) {
        return MovimientoEntity.builder()
                .movimientoId(movimientoDto.getMovimientoId())
                .fecha(movimientoDto.getFecha())
                .tipoMovimiento(movimientoDto.getTipoMovimiento())
                .valor(movimientoDto.getValor())
                .saldo(movimientoDto.getSaldo())
                .cuentaEntity(cuentaEntity)
                .build();
    }

    /**
     * Edicion de movimiento en la fecha.
     * @param movimientoEntity entidad del movimiento
     * @param fecha fecha a modificar
     * @return entidad del movimiento
     */
    public static MovimientoEntity editarFechaMovimientoEntity(MovimientoEntity movimientoEntity, Date fecha) {
        movimientoEntity.setFecha(fecha);
        return movimientoEntity;
    }
}
