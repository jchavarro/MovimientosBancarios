package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.ReporteDto;

import java.util.Date;

/**
 * Intefaz de servicio de reportes.
 *
 * @author Juan Chavarro
 */
public interface ReporteService {

    /**
     * Metodo get para obtener reportes de movimientos por fechas.
     * @param fechaInicial fecha inicial.
     * @param fechaFinal fecha final.
     * @return Objeto de transferencia de datos del reporte.
     */
    ReporteDto obtenerMovimientoPorFechas(Date fechaInicial, Date fechaFinal);
}
