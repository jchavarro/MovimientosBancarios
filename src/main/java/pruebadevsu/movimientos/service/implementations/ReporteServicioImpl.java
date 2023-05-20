package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.service.interfaces.ReporteService;
import pruebadevsu.movimientos.web.dto.ReporteDto;

import java.util.Date;

/**
 * Servicio para los reportes.
 *
 * @author Juan Chavarro
 */
@Service
@Slf4j
public class ReporteServicioImpl implements ReporteService {

    /**
     * Permite la conversión de un objeto a otro.
     */
    @Autowired
    private ModelMapper modelMapper;


    /**
     * Metodo get para obtener reportes de movimientos por fechas.
     * @param fechaInicial fecha inicial.
     * @param fechaFinal fecha final.
     * @return Objeto de transferencia de datos del reporte.
     */
    @Override
    public ReporteDto obtenerMovimientoPorFechas(final Date fechaInicial, final Date fechaFinal) {
        return null;
    }
}
