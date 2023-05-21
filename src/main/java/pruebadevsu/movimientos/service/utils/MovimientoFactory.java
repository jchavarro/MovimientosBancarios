package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;
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
     * @param cuentaEntity entidad de la cuenta
     * @return Objeto de transferencia de la cuenta
     */
    public static MovimientoResponseDto crearMovimientoCuenta(final MovimientoEntity movimientoEntity,
                                                       final CuentaEntity cuentaEntity) {
        return MovimientoResponseDto.builder()
                .numeroCuenta(cuentaEntity.getNumeroCuenta())
                .tipoCuenta(cuentaEntity.getTipoCuenta())
                .saldoInicial(cuentaEntity.getSaldoInicial())
                .estado(cuentaEntity.getEstado())
                .movimientoDescipcion(movimientoEntity.getTipoMovimiento() + " de "
                        + Math.abs(movimientoEntity.getValor()))
                .build();
    }

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
}
