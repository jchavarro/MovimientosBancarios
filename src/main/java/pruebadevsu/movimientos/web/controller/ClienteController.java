package pruebadevsu.movimientos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pruebadevsu.movimientos.service.interfaces.ClienteService;
import pruebadevsu.movimientos.web.dto.ClienteDto;

/**
 * Clase controlador para el objeto cliente.
 *
 * @author Juan Chavarro
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    /**
     * Servicio respectivo del controlador.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Metodo get para obtener cliente por su identificacion.
     * @param clienteId identificacion del cliente.
     * @return Objeto de transferencia de datos del cliente.
     */
    @GetMapping()
    public ResponseEntity<ClienteDto> obtenerCliente (@RequestParam("clienteId") final Integer clienteId) {
        return new ResponseEntity<>(clienteService.obtenerClientePorId(clienteId), HttpStatus.FOUND);
    }

    /**
     * Metodo post para crear cliente a partir del objeto cliente.
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    @PostMapping()
    public ResponseEntity<ClienteDto> crearCliente (@RequestBody final ClienteDto clienteDto) {
        return new ResponseEntity<>(clienteService.crearCliente(clienteDto), HttpStatus.CREATED);
    }

    /**
     * Metodo de actualizar la información completa del cliente.
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    @PutMapping()
    public ResponseEntity<ClienteDto> actualizarCliente (@RequestBody final ClienteDto clienteDto) {
        return new ResponseEntity<>(clienteService.actualizarCliente(clienteDto), HttpStatus.OK);
    }

    /**
     * Metodo de eliminar cliente por su id.
     * @param clienteId Identifiacion del cliente.
     * @return True si es eliminado.
     */
    @DeleteMapping()
    public ResponseEntity<Boolean> eliminarCliente (@RequestParam("clienteId") final Integer clienteId) {
        return new ResponseEntity<>(clienteService.eliminarCliente(clienteId), HttpStatus.OK);
    }

    /**
     * Metodo de editar el estado del cliente por su id.
     * @param clienteId Identifiacion del cliente.
     * @param estadoCliente estado nuevo del cliente.
     * @return Objeto de transferencia de datos editados del cliente.
     */
    @PatchMapping("/estado")
    public ResponseEntity<ClienteDto> editarEstadoCLiente (@RequestParam("clienteId") final Integer clienteId,
                                                           @RequestParam("estadoCliente") final Boolean estadoCliente) {
        return new ResponseEntity<>(clienteService.editarEstadoCliente(clienteId, estadoCliente), HttpStatus.OK);
    }

}
