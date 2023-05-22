package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.MovimientoEntity;
import pruebadevsu.movimientos.web.dto.ReporteDto;

public final class ReporteFactory {

    /**
     * constructor vacio.
     */
    private ReporteFactory() {
    }

    /**
     * Creacion de reporte de movimiento.
     * @param movimientoEntity entidad de movimiento.
     * @return objeto de transferencia de datos de reporte
     */
    public static ReporteDto crearReporteMovimientoCuenta(MovimientoEntity movimientoEntity) {
        return ReporteDto.builder()
                .fecha(movimientoEntity.getFecha())
                .cliente(movimientoEntity.getCuentaEntity().getClienteEntity().getNombre())
                .numeroCuenta(movimientoEntity.getCuentaEntity().getNumeroCuenta())
                .tipo(movimientoEntity.getCuentaEntity().getTipoCuenta())
                .estado(movimientoEntity.getCuentaEntity().getEstado())
                .movimiento(movimientoEntity.getValor())
                .saldoDisponible(movimientoEntity.getSaldo() + movimientoEntity.getValor())
                .build();
    }
}
