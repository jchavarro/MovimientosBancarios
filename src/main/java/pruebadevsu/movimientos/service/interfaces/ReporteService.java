package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.ReporteDto;

import java.util.Date;
import java.util.List;

/**
 * Intefaz de servicio de reportes.
 *
 * @author Juan Chavarro
 */
public interface ReporteService {

    /**
     * Metodo para obtener reportes de movimientos por fechas.
     *
     * @param clienteId    id de cliente.
     * @param fechaInicial fecha inicial.
     * @param fechaFinal   fecha final.
     * @return Objeto de transferencia de datos del reporte.
     */
    List<ReporteDto> obtenerMovimientoPorFechas(Integer clienteId, Date fechaInicial, Date fechaFinal);
}
