package pruebadevsu.movimientos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pruebadevsu.movimientos.service.interfaces.MovimientoService;
import pruebadevsu.movimientos.web.dto.MovimientoDto;
import pruebadevsu.movimientos.web.dto.reponse.MovimientoResponseDto;
import pruebadevsu.movimientos.web.dto.request.MovimientoRequestDto;

/**
 * Clase controlador para el objeto movimientos.
 *
 * @author Juan Chavarro
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    /**
     * Servicio respectivo del controlador.
     */
    @Autowired
    private MovimientoService movimientoService;

    /**
     * Metodo get para obtener movimiento por su identificacion.
     *
     * @param movimientoId identificacion del movimiento.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @GetMapping()
    public ResponseEntity<MovimientoDto> obtenerMovimiento(@RequestParam("movimientoId") final Integer movimientoId) {
        return new ResponseEntity<>(movimientoService.obtenerMovimientoPorId(movimientoId), HttpStatus.FOUND);
    }

    /**
     * Metodo post para crear movimiento a partir del objeto movimiento.
     *
     * @param movimientoDto Objeto movimiento con su informacion.
     * @return Objeto de transferencia de datos del movimiento.
     */
    @PostMapping()
    public ResponseEntity<MovimientoResponseDto> crearMovimiento(
            @RequestBody final MovimientoRequestDto movimientoDto) {
        return new ResponseEntity<>(movimientoService.crearMovimiento(movimientoDto), HttpStatus.CREATED);
    }

    /**
     * Metodo de actualizar la información completa del cuenta.
     *
     * @param movimientoDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @PutMapping()
    public ResponseEntity<MovimientoDto> actualizarMovimiento(@RequestBody final MovimientoDto movimientoDto) {
        return new ResponseEntity<>(movimientoService.actualizarMovimiento(movimientoDto), HttpStatus.OK);
    }

    /**
     * Metodo de eliminar movimiento por su id.
     *
     * @param movimientoId Identifiacion del movimiento.
     * @return True si es eliminado.
     */
    @DeleteMapping()
    public ResponseEntity<Boolean> eliminarMovimiento(@RequestParam("movimientoId") final Integer movimientoId) {
        return new ResponseEntity<>(movimientoService.eliminarMovimiento(movimientoId), HttpStatus.OK);
    }
}
