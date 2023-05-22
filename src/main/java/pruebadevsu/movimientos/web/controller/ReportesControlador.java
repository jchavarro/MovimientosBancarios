package pruebadevsu.movimientos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pruebadevsu.movimientos.service.interfaces.ReporteService;
import pruebadevsu.movimientos.web.dto.ReporteDto;

import java.util.Date;
import java.util.List;

/**
 * Clase controlador para el objeto reportes.
 *
 * @author Juan Chavarro
 */
@RestController
@RequestMapping("/reportes")
public class ReportesControlador {

    /**
     * Servicio respectivo del controlador.
     */
    @Autowired
    private ReporteService reporteService;

    /**
     * Metodo get para obtener reportes de movimientos por fechas.
     *
     * @param clienteId    id de cliente.
     * @param fechaInicial fecha inicial.
     * @param fechaFinal   fecha final.
     * @return Objeto de transferencia de datos del reporte.
     */
    @GetMapping()
    public ResponseEntity<List<ReporteDto>> obtenerMovimiento(@RequestParam("clienteId") final Integer clienteId,
                                                              @RequestParam("fechaInicial") final Date fechaInicial,
                                                              @RequestParam("fechaFinal") final Date fechaFinal) {
        return new ResponseEntity<>(reporteService.obtenerMovimientoPorFechas(clienteId, fechaInicial, fechaFinal),
                HttpStatus.FOUND);
    }

}
