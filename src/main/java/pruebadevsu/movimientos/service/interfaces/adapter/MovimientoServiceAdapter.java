package pruebadevsu.movimientos.service.interfaces.adapter;

import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;

import java.util.List;

public interface MovimientoServiceAdapter {

    /**
     * Obtiene los movimientos de la lista de cuentas que recibe.
     * @param cuentasEntity Lista de cuentas
     * @return lista de movimientos.
     */
    List<MovimientoEntity> obtenerMovimientoPorCuentas(List<CuentaEntity> cuentasEntity);
}
