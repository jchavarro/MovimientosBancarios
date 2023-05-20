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
import pruebadevsu.movimientos.service.interfaces.CuentaService;
import pruebadevsu.movimientos.web.dto.CuentaDto;

/**
 * Clase controlador para el objeto cuentas.
 *
 * @author Juan Chavarro
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    /**
     * Servicio respectivo del controlador.
     */
    @Autowired
    private CuentaService cuentaService;

    /**
     * Metodo get para obtener cuenta por su identificacion.
     * @param numeroCuenta identificacion de la cuenta.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @GetMapping()
    public ResponseEntity<CuentaDto> obtenerCuenta (@RequestParam("numeroCuenta") final Integer numeroCuenta) {
        return new ResponseEntity<>(cuentaService.obtenerCuentaPorId(numeroCuenta), HttpStatus.FOUND);
    }

    /**
     * Metodo post para crear cuenta a partir del objeto cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @PostMapping()
    public ResponseEntity<CuentaDto> crearCuenta (@RequestBody final CuentaDto cuentaDto) {
        return new ResponseEntity<>(cuentaService.crearCuenta(cuentaDto), HttpStatus.CREATED);
    }

    /**
     * Metodo de actualizar la información completa del cuenta.
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @PutMapping()
    public ResponseEntity<CuentaDto> actualizarCuenta (@RequestBody final CuentaDto cuentaDto) {
        return new ResponseEntity<>(cuentaService.actualizarCuenta(cuentaDto), HttpStatus.OK);
    }

    /**
     * Metodo de eliminar cuenta por su id.
     * @param numeroCuenta Identifiacion de la cuenta.
     * @return True si es eliminada.
     */
    @DeleteMapping()
    public ResponseEntity<Boolean> eliminarCuenta (@RequestParam("numeroCuenta") final Integer numeroCuenta) {
        return new ResponseEntity<>(cuentaService.eliminarCuenta(numeroCuenta), HttpStatus.OK);
    }
}
