package pruebadevsu.movimientos.service.interfaces.adapter;

import pruebadevsu.movimientos.model.entities.ClienteEntity;

public interface ClienteServiceAdapter {

    /**
     * Metodo para obtener la entidad de cliente por Id.
     * @param clienteId id del cliente.
     * @return Entidad del cliente.
     */
    ClienteEntity obtenerClienteEntityPorId(Integer clienteId);
}
