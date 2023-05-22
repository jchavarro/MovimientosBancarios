package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.service.interfaces.ReporteService;
import pruebadevsu.movimientos.service.interfaces.adapter.CuentaServiceAdapter;
import pruebadevsu.movimientos.service.interfaces.adapter.MovimientoServiceAdapter;
import pruebadevsu.movimientos.service.utils.ReporteFactory;
import pruebadevsu.movimientos.web.dto.ReporteDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para los reportes.
 *
 * @author Juan Chavarro
 */
@Service
@Slf4j
public class ReporteServicioImpl implements ReporteService {

    /**
     * Adaptador del servicio de cuenta.
     */
    @Autowired
    private CuentaServiceAdapter cuentaServiceAdapter;

    /**
     * Adaptador del servicio de movimientos.
     */
    @Autowired
    private MovimientoServiceAdapter movimientoServiceAdapter;

    /**
     * Metodo para obtener reportes de movimientos por fechas.
     *
     * @param clienteId id de cliente.
     * @param fechaInicial fecha inicial.
     * @param fechaFinal   fecha final.
     * @return Objeto de transferencia de datos del reporte.
     */
    @Override
    public List<ReporteDto> obtenerMovimientoPorFechas(Integer clienteId, Date fechaInicial, Date fechaFinal) {
        log.info("Realizando reporte del cliente: " + clienteId);
        return movimientoServiceAdapter.obtenerMovimientoPorCuentas(
                cuentaServiceAdapter.obtenerCuentasPorCliente(clienteId)).stream()
                .map(ReporteFactory::crearReporteMovimientoCuenta).collect(Collectors.toList());
    }
}
