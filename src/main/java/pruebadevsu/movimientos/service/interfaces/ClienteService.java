package pruebadevsu.movimientos.service.interfaces;

import pruebadevsu.movimientos.web.dto.ClienteDto;

/**
 * Intefaz de servicio de cliente.
 *
 * @author Juan Chavarro
 */
public interface ClienteService {

    /**
     * Metodo get para obtener cliente por su identificacion.
     * @param clienteId identificacion del cliente.
     * @return Objeto de transferencia de datos del cliente.
     */
    ClienteDto obtenerClientePorId(Integer clienteId);

    /**
     * Metodo post para crear cliente a partir del objeto cliente.
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    ClienteDto crearCliente(ClienteDto clienteDto);

    /**
     * Metodo de actualizar la información completa del cliente.
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    ClienteDto actualizarCliente(ClienteDto clienteDto);

    /**
     * Metodo de eliminar cliente por su id.
     * @param clienteId Identifiacion del cliente.
     * @return True si es eliminado.
     */
    Boolean eliminarCliente(Integer clienteId);

    /**
     * Metodo de editar el estado del cliente por su id.
     * @param clienteId Identifiacion del cliente.
     * @param estadoCliente estado nuevo del cliente.
     * @return Objeto de transferencia de datos editados del cliente.
     */
    ClienteDto editarEstadoCliente(Integer clienteId, Boolean estadoCliente);
}
