package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.ClienteEntity;

/**
 * clase factory para el cliente.
 */
public final class ClienteFactory {

    /**
     * constructor vacio.
     */
    private ClienteFactory() {
    }

    /**
     * Cambiar estado a clienteEntity.
     *
     * @param clienteEntity entidad del cliente a editar
     * @param estadoNuevo nuevo estado a editar
     * @return entidad del cliente
     */
    public static ClienteEntity editarEstadoCliente(final ClienteEntity clienteEntity, final Boolean estadoNuevo) {
        clienteEntity.setEstado(estadoNuevo);
        return clienteEntity;
    }
}
